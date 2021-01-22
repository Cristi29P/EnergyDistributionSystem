package factories;

import entities.EnergyType;
import entities.Producer;

public final class ProducerFactory {
    private static ProducerFactory instance;

    private ProducerFactory() {
    }

    /**
     * Returns the only instance of this factory
     * @return a factory instance
     */
    public static ProducerFactory getInstance() {
        if (instance == null) {
            synchronized (DistributorFactory.class) {
                if (instance == null) {
                    instance = new ProducerFactory();
                }
            }
        }
        return instance;
    }

    /**
     * Generates a producer with the specified fields taken from input
     * @param id of new producer
     * @param energyType of new producer
     * @param maxDistributors of new producer
     * @param priceKW of new producer
     * @param energyPerDistributor of new producer
     * @param numberOfDistributor of new producer (computed during the actual round)
     * @return a producer entity
     */
    public Producer createProducer(final int id, final EnergyType energyType,
                                   final int maxDistributors, final double priceKW,
                                   final int energyPerDistributor, final int numberOfDistributor) {
        return new Producer(id, energyType, maxDistributors,
                priceKW, energyPerDistributor, numberOfDistributor);
    }
}
