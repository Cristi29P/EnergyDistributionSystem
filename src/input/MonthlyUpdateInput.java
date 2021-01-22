package input;

import java.util.ArrayList;

public final class MonthlyUpdateInput {
    private final ArrayList<ConsumerInput> newConsumers;
    private final ArrayList<DistributorChanges> distributorChanges;
    private final ArrayList<ProducerChange> producerChanges;

    public MonthlyUpdateInput() {
        this.newConsumers = null;
        this.distributorChanges = null;
        this.producerChanges = null;
    }

    public ArrayList<ConsumerInput> getNewConsumers() {
        return newConsumers;
    }

    public ArrayList<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public ArrayList<ProducerChange> getProducerChanges() {
        return producerChanges;
    }
}
