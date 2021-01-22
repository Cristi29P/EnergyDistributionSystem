package entities;

import java.util.ArrayList;
import java.util.Observable;

public final class Producer extends Observable {
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private int numberOfDistributors;
    private final ArrayList<Status> statuses;

    private final ArrayList<Distributor> assignedDistributors = new ArrayList<>();

    public Producer(int id, EnergyType energyType, int maxDistributors,
                    double priceKW, int energyPerDistributor, int numberOfDistributors) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.numberOfDistributors = numberOfDistributors;
        this.statuses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public int getNumberOfDistributors() {
        return numberOfDistributors;
    }

    public void setNumberOfDistributors(int numberOfDistributors) {
        this.numberOfDistributors = numberOfDistributors;
    }

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public ArrayList<Distributor> getAssignedDistributors() {
        return assignedDistributors;
    }

    /**
     * New energy received from the monthly changes
     * @param newEnergyPerDistributor new quantity
     */
    public void update(final int newEnergyPerDistributor) {
        this.energyPerDistributor = newEnergyPerDistributor;
        setChanged();
        notifyObservers();
    }
}
