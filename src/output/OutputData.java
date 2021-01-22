package output;

import java.util.ArrayList;

public final class OutputData {
    private ArrayList<ConsumerOutput> consumers;
    private ArrayList<DistributorOutput> distributors;
    private ArrayList<ProducerOutput> energyProducers;

    public OutputData(ArrayList<ConsumerOutput> consumers, ArrayList<DistributorOutput> distribute,
                      ArrayList<ProducerOutput> energyProducers) {
        this.consumers = consumers;
        this.distributors = distribute;
        this.energyProducers = energyProducers;
    }

    public ArrayList<ConsumerOutput> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<ConsumerOutput> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<DistributorOutput> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<DistributorOutput> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<ProducerOutput> getEnergyProducers() {
        return energyProducers;
    }

    public void setEnergyProducers(ArrayList<ProducerOutput> energyProducers) {
        this.energyProducers = energyProducers;
    }
}
