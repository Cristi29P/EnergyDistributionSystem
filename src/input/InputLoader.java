package input;


import entities.Consumer;
import entities.Distributor;
import entities.Producer;
import factories.ConsumerFactory;
import factories.DistributorFactory;
import factories.ProducerFactory;

import java.util.ArrayList;

public final class InputLoader {
    public InputLoader() {
    }

    /**
     * Stores the input consumers into an arraylist
     * @param input initial input
     * @param consumers list of input consumers
     * @param factory consumer creator
     */
    public void loadConsumers(final InputData input, final ArrayList<Consumer> consumers,
                              final ConsumerFactory factory) {
        for (ConsumerInput aux : input.getInitialData().getConsumers()) {
            consumers.add(factory.createConsumer(aux.getId(), aux.getInitialBudget(),
                    aux.getMonthlyIncome()));
        }
    }

    /**
     * Stores the input distributors into an arraylist
     * @param input initial input
     * @param distributors list of input distributors
     * @param factory distributor creator
     */
    public void loadDistributors(final InputData input, final ArrayList<Distributor> distributors,
                                 final DistributorFactory factory) {
        for (DistributorInput aux : input.getInitialData().getDistributors()) {
            distributors.add(factory.createDistributor(aux.getId(), aux.getContractLength(),
                    aux.getInitialBudget(), aux.getInitialInfrastructureCost(),
                    aux.getEnergyNeededKW(), aux.getProducerStrategy()));
        }
    }

    /**
     * Stores the input producers into an arraylist
     * @param input initial input
     * @param producers list of input producers
     * @param factory producer factory
     */
    public void loadProducers(final InputData input, final ArrayList<Producer> producers,
                              final ProducerFactory factory) {
        for (ProducerInput aux : input.getInitialData().getProducers()) {
            producers.add(factory.createProducer(aux.getId(), aux.getEnergyType(),
                    aux.getMaxDistributors(), aux.getPriceKW(), aux.getEnergyPerDistributor(), 0));
        }
    }
}
