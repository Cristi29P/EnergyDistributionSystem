package entities;

public final class Contract {
    private int consumerId;
    private final int distributeID;
    private int price;
    private int remainedContractMonths;
    private int notPayedPeriod;


    public Contract(final int consumerId, final int distributeID, final int price,
                    final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.distributeID = distributeID;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
        this.notPayedPeriod = 0;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getNotPayedPeriod() {
        return notPayedPeriod;
    }

    public void setNotPayedPeriod(final int notPayedPeriod) {
        this.notPayedPeriod = notPayedPeriod;
    }

    public int getDistributeID() {
        return distributeID;
    }
}
