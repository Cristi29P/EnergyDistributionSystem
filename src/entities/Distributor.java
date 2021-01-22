package entities;

import common.Constants;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public final class Distributor implements Observer {
    private int id;
    private int contractLength;
    private int budget;
    private int infrastructureCost;
    private int energyNeededKW;
    private final String producerStrategy;
    private boolean isBankrupt;
    private ArrayList<Contract> contracts = new ArrayList<>();
    private int price;

    private boolean hasChanged = false;

    private final ArrayList<Producer> assignedProducers = new ArrayList<>();

    public Distributor(final int id, final int contractLength, final int budget,
                       final int infrastructureCost, final int energyNeededKW,
                       final String producerStrategy) {
        this.id = id;
        this.contractLength = contractLength;
        this.budget = budget;
        this.infrastructureCost = infrastructureCost;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = false;
        this.price = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public ArrayList<Producer> getAssignedProducers() {
        return assignedProducers;
    }

    public boolean isHasChanged() {
        return hasChanged;
    }

    public void setHasChanged(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    /**
     * Calculates total cost of all the producers
     * @return an integer
     */
    public int calculateCost() {
        int cost = 0;
        for (Producer producer : assignedProducers) {
            cost += producer.getPriceKW() * producer.getEnergyPerDistributor();
        }

        return (int) Math.round(Math.floor((float) cost / Constants.DIVISION));
    }

    /**
     * Calculates the contract price of this distributor's instance
     */
    public void calculatePrice() {
        if (contracts.size() == 0) {
            price = infrastructureCost + calculateCost() + (int)
                    Math.round(Math.floor(Constants.FACTOR_PROFIT * calculateCost()));
        } else {
            price = Math.toIntExact(Math.round(Math.floor((float)
                    infrastructureCost / contracts.size()))  + calculateCost()
                    + (int) Math.round(Math.floor(Constants.FACTOR_PROFIT * calculateCost())));
        }
    }

    /**
     * Computes the monthly cost of a distributor
     * @return monthly cost
     */
    public int monthlyPayments() {
        return (infrastructureCost + calculateCost() * contracts.size());
    }

    @Override
    public void update(Observable o, Object arg) {
        hasChanged = true;
    }
}
