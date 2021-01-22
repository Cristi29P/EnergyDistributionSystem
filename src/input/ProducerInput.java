package input;

import entities.EnergyType;

public final class ProducerInput {
    private final int id;
    private final EnergyType energyType;
    private final int maxDistributors;
    private final double priceKW;
    private final int energyPerDistributor;

    public ProducerInput() {
        this.id = 0;
        this.energyType = null;
        this.maxDistributors = 0;
        this.priceKW = 0;
        this.energyPerDistributor = 0;
    }

    public int getId() {
        return id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }
}
