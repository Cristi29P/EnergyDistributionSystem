package output;

import entities.Consumer;
import entities.Contract;
import entities.Distributor;
import entities.Producer;
import entities.Status;

import java.util.ArrayList;

public final class OutputGenerator {
    public OutputGenerator() {
    }

    /**
     * Generates the output object of this program based the current state of the databases
     * @param consumersDB list of consumers
     * @param distributorsDB list of distributors
     * @return output object
     */
    public OutputData generateOutput(final ArrayList<Consumer> consumersDB,
                                     final ArrayList<Distributor> distributorsDB,
                                     final ArrayList<Producer> producersDB) {
        ArrayList<ConsumerOutput> outputConsumers = new ArrayList<>();
        for (Consumer consumer : consumersDB) {
            ConsumerOutput consumerAux = new ConsumerOutput(consumer.getId(),
                    consumer.isBankrupt(), consumer.getBudget());
            outputConsumers.add(consumerAux);
        }

        ArrayList<DistributorOutput> outputDistributors = new ArrayList<>();
        for (Distributor distributor : distributorsDB) {
            ArrayList<ContractOutput> outputContracts = new ArrayList<>();

            for (Contract contract : distributor.getContracts()) {
                ContractOutput contractAux = new ContractOutput(contract.getConsumerId(),
                        contract.getPrice(),
                        contract.getRemainedContractMonths());
                outputContracts.add(contractAux);
            }


            DistributorOutput distributorAux = new DistributorOutput(distributor.getId(),
                    distributor.getEnergyNeededKW(),
                    distributor.getPrice(),
                    distributor.getBudget(),
                    distributor.getProducerStrategy(),
                    distributor.isBankrupt(),
                    outputContracts);
            outputDistributors.add(distributorAux);
        }

        ArrayList<ProducerOutput> outputProducers = new ArrayList<>();
        for (Producer producer: producersDB) {
            ArrayList<MonthlyOutput> outputStats = new ArrayList<>();
            for (Status monthlyStatus : producer.getStatuses()) {
                MonthlyOutput outputStatus = new MonthlyOutput(monthlyStatus.getNumberMonth(),
                        monthlyStatus.getIdDistributors());
                outputStats.add(outputStatus);
            }

            ProducerOutput outputProducer = new ProducerOutput(producer.getId(),
                    producer.getMaxDistributors(), producer.getPriceKW(), producer.getEnergyType(),
                    producer.getEnergyPerDistributor(), outputStats);
            outputProducers.add(outputProducer);
        }


        return new OutputData(outputConsumers, outputDistributors, outputProducers);
    }
}
