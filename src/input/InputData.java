package input;

import java.util.ArrayList;

public final class InputData {
    private final int numberOfTurns;
    private final InitialInputData initialData;
    private final ArrayList<MonthlyUpdateInput> monthlyUpdates;

    public InputData() {
        this.numberOfTurns = 0;
        this.initialData = null;
        this.monthlyUpdates = null;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public InitialInputData getInitialData() {
        return initialData;
    }

    public ArrayList<MonthlyUpdateInput> getMonthlyUpdates() {
        return monthlyUpdates;
    }
}
