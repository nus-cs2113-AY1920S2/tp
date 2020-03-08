package seedu.duke.data;

public class Budget {
    private double amount;

    public Budget(double amount) {
        if (amount > 5000) {
            this.amount = 5000;
        } else if (amount < 0) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }
    }

    public void setBudget(double amount) {
        if (amount > 5000) {
            this.amount = 5000;
        } else if (amount < 0) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }
    }

    public double getAmount() {
        return amount;
    }
}
