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
        if (amount > 5000) {
            this.amount = Math.min(amount, MAX);
        } else {
            this.amount = Math.max(amount, MIN);
        }
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
