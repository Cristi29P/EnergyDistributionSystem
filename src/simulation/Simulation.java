package simulation;

import common.Constants;
import entities.Consumer;
import entities.Contract;
import entities.Distributor;
import entities.Producer;
import entities.Status;
import factories.ConsumerFactory;
import input.ConsumerInput;
import input.DistributorChanges;
import input.MonthlyUpdateInput;
import input.ProducerChange;
import strategies.GreenStrategy;
import strategies.PriceStrategy;
import strategies.QuantityStrategy;
import strategies.Strategy;

import java.util.ArrayList;
import java.util.Collections;

public final class Simulation {
    public Simulation() {
    }

    /**
     * Computes the distributor having the lowest monthly price
     */
    private Distributor bestDistributor(final ArrayList<Distributor> distributors) {
        int minimumPrice = Integer.MAX_VALUE;
        int id = -1;
        Distributor best = null;
        for (Distributor aux: distributors) {
            if (!aux.isBankrupt()) {
                if (aux.getPrice() < minimumPrice) {
                    minimumPrice = aux.getPrice();
                    id = aux.getId();
                }
            }
        }

        for (Distributor aux : distributors) {
            if (id == aux.getId()) {
                best = aux;
                break;
            }
        }
        return best;
    }

    /**
     * Subtracts the monthly cost of each distributor and check if they go bankrupt
     */
    private void monthlySubtract(final ArrayList<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            if (!distributor.isBankrupt()) {
                distributor.setBudget(distributor.getBudget()
                        - distributor.monthlyPayments());
                if (distributor.getBudget() < 0) {
                    distributor.setBankrupt(true);
                    if (!distributor.getContracts().isEmpty()) {
                        distributor.getContracts().clear();
                    }
                }
            }
        }
    }

    /**
     * Voids a consumer's contract if they become bankrupt at the end of a round
     */
    private void bankruptcyChecker(final ArrayList<Consumer> consumers,
                                   final ArrayList<Distributor> distributors) {
        for (Consumer consumer :consumers) {
            if (consumer.isBankrupt()) {
                Contract contract = consumer.getContract();
                if (contract != null) {
                    distributors.get(contract.getDistributeID()).getContracts().remove(contract);
                    consumer.setContract(null);
                }
            }
        }
    }

    /**
     * Checks if a consumer's contract came to an end and sets him free of his old contract
     */
    private void contractChecker(final ArrayList<Consumer> consumers,
                                 final ArrayList<Distributor> distributors) {
        for (Consumer consumer: consumers) {
            if (!consumer.isBankrupt()) {
                Contract contract = consumer.getContract();
                if (contract != null) {
                    if (contract.getRemainedContractMonths() == 0) {
                        distributors.get(contract.getDistributeID()).getContracts().
                                remove(contract);
                        consumer.setContract(null);
                    }
                }
            }
        }
    }

    /**
     * Checks if a consumer has a contract and assigns him one otherwise
     */
    private void contractSetter(final Distributor best, final ArrayList<Consumer> consumers) {
        for (Consumer consumer : consumers) {
            if (!consumer.isBankrupt()) {
                consumer.setBudget(consumer.getBudget() + consumer.getMonthlyIncome());
                if (consumer.getContract() == null) {
                    Contract contract = new Contract(consumer.getId(), best.getId(),
                            best.getPrice(), best.getContractLength());
                    best.getContracts().add(contract);
                    consumer.setContract(contract);
                }
            }
        }
    }

    /**
     * Subtracts the sum the consumers have to pay each month based on the contract they signed
     */
    private void consumerPayer(final ArrayList<Consumer> consumers,
                               final ArrayList<Distributor> distributors) {
        for (Consumer consumer : consumers) {
            if (!consumer.isBankrupt()) {
                Contract contract = consumer.getContract();
                if (contract.getNotPayedPeriod() == 0 && consumer.getPenalty() == 0) {
                    if (consumer.getBudget() >= contract.getPrice()) {
                        consumer.setBudget(consumer.getBudget() - contract.getPrice());
                        distributors.get(contract.getDistributeID()).setBudget(distributors.
                                get(contract.getDistributeID()).getBudget()
                                + contract.getPrice());
                    } else {
                        consumer.setPenalty((int) Math.round(Math.floor(Constants.FACTOR_DEBT
                                * contract.getPrice())));
                        consumer.setIdPenalty(contract.getDistributeID());
                        contract.setNotPayedPeriod(contract.getNotPayedPeriod() + 1);
                    }
                    contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
                } else {
                    int totalPayment = contract.getPrice() + consumer.getPenalty();
                    if (consumer.getBudget() >= totalPayment) {
                        consumer.setBudget(consumer.getBudget() - totalPayment);
                        distributors.get(consumer.getIdPenalty()).setBudget(distributors.
                                get(contract.getDistributeID()).getBudget()
                                        + consumer.getPenalty());
                        consumer.setIdPenalty(-1);
                        consumer.setPenalty(0);
                        distributors.get(contract.getDistributeID()).setBudget(distributors.
                                get(contract.getDistributeID()).getBudget()
                                + contract.getPrice());
                        contract.setRemainedContractMonths(contract.getRemainedContractMonths()
                                - 1);
                    } else {
                        consumer.setBankrupt(true);
                    }
                }
            }
        }
    }

    /**
     * Applies the monthly changes
     */
    private void applyChanges(final ArrayList<MonthlyUpdateInput> changes,
                              final ArrayList<Consumer> consumers,
                              final ArrayList<Distributor> distributors, final int turnNumber,
                              final ConsumerFactory factory) {
        if (!changes.get(turnNumber).getNewConsumers().isEmpty()) {
            for (ConsumerInput aux : changes.get(turnNumber).getNewConsumers()) {
                consumers.add(factory.createConsumer(aux.getId(), aux.getInitialBudget(),
                        aux.getMonthlyIncome()));
            }
        }

        if (!changes.get(turnNumber).getDistributorChanges().isEmpty()) {
            for (DistributorChanges aux : changes.get(turnNumber).getDistributorChanges()) {
                distributors.get(aux.getId()).setInfrastructureCost(aux.
                        getInfrastructureCost());
            }
        }
    }

    private Strategy strategyPicker(final Distributor distributor) {
        if (distributor.getProducerStrategy().equals("GREEN")) {
            return new GreenStrategy();
        } else if (distributor.getProducerStrategy().equals("PRICE")) {
            return new PriceStrategy();
        } else {
            return new QuantityStrategy();
        }
    }

    private void producersPicker(ArrayList<Producer> producersAux, Distributor distributor) {
        int energyAdded = 0;
        for (Producer aux : producersAux) {
            if (energyAdded < distributor.getEnergyNeededKW()
                    && aux.getNumberOfDistributors() < aux.getMaxDistributors()) {

                aux.addObserver(distributor);
                distributor.getAssignedProducers().add(aux);
                aux.getAssignedDistributors().add(distributor);
                energyAdded += aux.getEnergyPerDistributor();
                aux.setNumberOfDistributors(aux.getNumberOfDistributors() + 1);
            }
        }
    }
    /**
     * Runs the initial phase of the simulation a.k.a round zero
     * @param consumersDB list of consumers
     * @param distributorsDB list of distributors
     */
    public void preSimulation(final ArrayList<Consumer> consumersDB,
                              final ArrayList<Distributor> distributorsDB,
                              final ArrayList<Producer> producersDB) {
        ArrayList<Producer> producersAux = new ArrayList<>(producersDB);

        for (Distributor distributor: distributorsDB) {
            producersAux.sort(strategyPicker(distributor));
            producersPicker(producersAux, distributor);
        }

        for (Distributor distributor : distributorsDB) {
            distributor.calculatePrice();
        }
        Distributor lowestDistributor = bestDistributor(distributorsDB);

        contractSetter(lowestDistributor, consumersDB);
        consumerPayer(consumersDB, distributorsDB);
        monthlySubtract(distributorsDB);
        bankruptcyChecker(consumersDB, distributorsDB);
    }

    /**
     * Simulates a single run based on the current state of the databases
     * @param turnNumber current round/month
     * @param consumersDB list of consumers
     * @param distributorsDB list of distributors
     * @param changes of this current month
     * @param factory for creating new users
     */
    public void runSimulation(final int turnNumber, final ArrayList<Consumer> consumersDB,
                              final ArrayList<Distributor> distributorsDB,
                              final ArrayList<Producer> producersDB,
                              final ArrayList<MonthlyUpdateInput> changes,
                              final ConsumerFactory factory) {
        applyChanges(changes, consumersDB, distributorsDB, turnNumber, factory);

        for (Distributor distributor : distributorsDB) {
            if (!distributor.isBankrupt()) {
                distributor.calculatePrice();
            }
        }

        Distributor lowestDistributor = bestDistributor(distributorsDB);

        contractChecker(consumersDB, distributorsDB);
        contractSetter(lowestDistributor, consumersDB);
        consumerPayer(consumersDB, distributorsDB);
        monthlySubtract(distributorsDB);
        bankruptcyChecker(consumersDB, distributorsDB);

        if (!changes.get(turnNumber).getProducerChanges().isEmpty()) {
            for (ProducerChange aux : changes.get(turnNumber).getProducerChanges()) {
                 producersDB.get(aux.getId()).update(aux.getEnergyPerDistributor());
            }
        }

        ArrayList<Producer> producersAux = new ArrayList<>(producersDB);

        for (Distributor distributor : distributorsDB) {
            if (distributor.isHasChanged()) {
                for (Producer producer : distributor.getAssignedProducers()) {
                    producer.setNumberOfDistributors(producer.getNumberOfDistributors() - 1);
                    producer.getAssignedDistributors().remove(distributor);
                    producer.deleteObserver(distributor);
                }

                producersAux.sort(strategyPicker(distributor));
                distributor.getAssignedProducers().clear();

                producersPicker(producersAux, distributor);
                distributor.setHasChanged(false);
            }
        }

        statusCreator(turnNumber, producersDB);
    }

    private void statusCreator(int turnNumber, ArrayList<Producer> producersDB) {
        for (Producer producer: producersDB) {
            ArrayList<Integer> ids = new ArrayList<>();
            for (Distributor distributor : producer.getAssignedDistributors()) {
                ids.add(distributor.getId());
            }
            Collections.sort(ids);
            producer.getStatuses().add(new Status(turnNumber + 1, ids));
        }
    }
}
