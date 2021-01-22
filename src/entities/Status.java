package entities;

import java.util.ArrayList;

public final class Status {
    private final int numberMonth;
    private final ArrayList<Integer> idDistributors;

    public Status(int numberMonth, ArrayList<Integer> idDistributors) {
        this.numberMonth = numberMonth;
        this.idDistributors = idDistributors;
    }

    public int getNumberMonth() {
        return numberMonth;
    }

    public ArrayList<Integer> getIdDistributors() {
        return idDistributors;
    }
}
