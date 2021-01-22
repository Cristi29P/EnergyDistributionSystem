package input;

public final class ConsumerInput {
    private final int id;
    private final int initialBudget;
    private final int monthlyIncome;

    public ConsumerInput() {
        this.id = 0;
        this.initialBudget = 0;
        this.monthlyIncome = 0;
    }

    public int getId() {
        return id;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }
}
