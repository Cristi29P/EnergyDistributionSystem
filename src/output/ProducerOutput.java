package output;

import entities.EnergyType;

import java.util.ArrayList;

public final class ProducerOutput {
    private final int id;
    private final int maxDistributors;
    private final double priceKW;
    private final EnergyType energyType;
    private final int energyPerDistributor;
    private final ArrayList<MonthlyOutput> monthlyStats;

    public ProducerOutput(int id, int maxDistributors, double priceKW, EnergyType energyType,
                          int energyPerDistributor, ArrayList<MonthlyOutput> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public int getId() {
        return id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public ArrayList<MonthlyOutput> getMonthlyStats() {
        return monthlyStats;
    }
}
