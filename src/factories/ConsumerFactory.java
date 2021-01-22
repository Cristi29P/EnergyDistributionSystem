package factories;

import entities.Consumer;

public final class ConsumerFactory {
    private static ConsumerFactory instance;

    private ConsumerFactory() {
    }

    /**
     * Returns the single instance of the factory
     * @return a factory instance
     */
    public static ConsumerFactory getInstance() {
        if (instance == null) {
            synchronized (ConsumerFactory.class) {
                if (instance == null) {
                    instance = new ConsumerFactory();
                }
            }
        }
        return instance;
    }

    /**
     * Generates a consumer with the specified fields based on the input data
     * @param id of a consumer
     * @param initialBudget of a consumer
     * @param monthlyIncome of a consumer
     * @return a consumer object
     */
    public Consumer createConsumer(final int id, final int initialBudget,
                                   final int monthlyIncome) {
        return new Consumer(id, initialBudget, monthlyIncome);
    }


}
