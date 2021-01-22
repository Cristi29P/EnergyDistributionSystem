package entities;

public final class Consumer {
    private int id;
    private int budget;
    private int monthlyIncome;
    private boolean isBankrupt;
    private int penalty;
    private int idPenalty;
    private Contract contract;

    public Consumer(final int id, final int budget, final int monthlyIncome) {
        this.id = id;
        this.budget = budget;
        this.monthlyIncome = monthlyIncome;
        this.isBankrupt = false;
        this.contract = null;
        this.penalty = 0;
        this.idPenalty = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        this.isBankrupt = bankrupt;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(final Contract contract) {
        this.contract = contract;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(final int penalty) {
        this.penalty = penalty;
    }

    public void setIdPenalty(final int idPenalty) {
        this.idPenalty = idPenalty;
    }

    public int getIdPenalty() {
        return idPenalty;
    }
}
