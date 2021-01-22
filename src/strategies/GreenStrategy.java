package strategies;

import entities.Producer;

public final class GreenStrategy implements Strategy {
    @Override
    public int compare(Producer o1, Producer o2) {
        if (o1.getEnergyType().isRenewable() && !o2.getEnergyType().isRenewable()) {
            return -1;
        } else if (!o1.getEnergyType().isRenewable() && o2.getEnergyType().isRenewable()) {
            return 1;
        }
    }
}
