package factories;

import entities.Distributor;

public final class DistributorFactory {
    private static DistributorFactory instance;

    private DistributorFactory() {
    }

    /**
     * Returns the single instance of the factory
     * @return a factory instance
     */
    public static DistributorFactory getInstance() {
        if (instance == null) {
            synchronized (DistributorFactory.class) {
                if (instance == null) {
                    instance = new DistributorFactory();
                }
            }
        }
        return instance;
    }

    /**
     * Creates a new distributor entity based on data taken from input
     * @param id of new distributor
     * @param contractLength of new distributor
     * @param initialBudget of new distributor
     * @param infrastructureCost of new distributor (computed separately)
     * @param energyNeededKW of new distributor
     * @param strategy of new distributor (computed during the round)
     * @return distributor entity
     */
    public Distributor createDistributor(final int id, final int contractLength,
                                          final int initialBudget, final int infrastructureCost,
                                          final int energyNeededKW, final String strategy) {
        return new Distributor(id, contractLength, initialBudget, infrastructureCost,
                energyNeededKW, strategy);
    }
}
