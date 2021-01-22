package output;

import java.util.ArrayList;

public final class MonthlyOutput {
    private final int month;
    private final ArrayList<Integer> distributorsIds;

    public MonthlyOutput(int month, ArrayList<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }
}
