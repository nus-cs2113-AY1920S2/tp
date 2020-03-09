package seedu.duke.data;

public class Budget {
    private static final double MAX = 5000;
    private static final double MIN = 0;
    private double amount;

    public Budget() {
        this.amount = 0;
    }

    /**
     * Sets the budget based on input amount.
     */
    public void setBudget(double amount) {
        amount = Math.min(amount,MAX);
        amount = Math.max(amount,0);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public double getRemainingBudget(double totalCost) {
        return amount - totalCost;
    }

    public void resetBudget() {
        this.amount = 0.0;
    }
}
