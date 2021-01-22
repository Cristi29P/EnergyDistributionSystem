package input;

public final class DistributorInput {
    private final int id;
    private final int contractLength;
    private final int initialBudget;
    private final int initialInfrastructureCost;
    private final int energyNeededKW;
    private final String producerStrategy;

    public DistributorInput() {
        this.id = 0;
        this.contractLength = 0;
        this.initialBudget = 0;
        this.initialInfrastructureCost = 0;
        this.energyNeededKW = 0;
        this.producerStrategy = null;
    }

    public int getId() {
        return id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }
}
