package input;

public final class ProducerChange {
    private final int id;
    private final int energyPerDistributor;

    public ProducerChange() {
        this.id = 0;
        this.energyPerDistributor = 0;
    }


    public int getId() {
        return id;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }
}
