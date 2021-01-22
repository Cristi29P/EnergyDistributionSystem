package input;

import java.util.ArrayList;

public final class InitialInputData {
    private final ArrayList<ConsumerInput> consumers;
    private final ArrayList<DistributorInput> distributors;
    private final ArrayList<ProducerInput> producers;

    public InitialInputData() {
        this.consumers = null;
        this.distributors = null;
        this.producers = null;
    }

    public ArrayList<ConsumerInput> getConsumers() {
        return consumers;
    }

    public ArrayList<DistributorInput> getDistributors() {
        return distributors;
    }

    public ArrayList<ProducerInput> getProducers() {
        return producers;
    }
}
