package strategies;

import entities.Producer;

public final class QuantityStrategy implements Strategy {
    @Override
    public int compare(Producer o1, Producer o2) {
        if ((o1.getEnergyPerDistributor() - o2.getEnergyPerDistributor()) != 0) {
            return Integer.compare(o2.getEnergyPerDistributor(), o1.getEnergyPerDistributor());
        } else {
            return Integer.compare(o1.getId(), o2.getId());
        }
    }
}
