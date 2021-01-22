package strategies;

import entities.Producer;

public final class PriceStrategy implements Strategy {
    @Override
    public int compare(Producer o1, Producer o2) {
        if ((o1.getPriceKW() - o2.getPriceKW() != 0)) {
            return Double.compare(o1.getPriceKW(), o2.getPriceKW());
        } else {
            return new QuantityStrategy().compare(o1, o2);
        }
    }
}
