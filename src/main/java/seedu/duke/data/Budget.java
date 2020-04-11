package seedu.duke.data;

public class Budget {
    private static final double MAX_BUDGET = 5000;
    private static final double MIN_BUDGET = 0;
    private double amount;

    /**
     * Creates a new budget object with the smallest allowable amount.
     */
    public Budget() {
        amount = MIN_BUDGET;
    }

    /**
     * Sets the budget based on user input.
     *
     * @param amount The user supplied amount.
     * @return The correct budget amount in case the user-specified amount is too high or negative.
     */
    public double setBudget(double amount) {
        amount = Math.min(amount, MAX_BUDGET);
        assert amount <= MAX_BUDGET : "Amount should not be greater than 5000";

        amount = Math.max(amount, MIN_BUDGET);
        assert amount >= MIN_BUDGET : "Amount should not be negative";

        this.amount = amount;
        return amount;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Calculates the remaining budget based on the total cost of items.
     *
     * @param totalCost Total cost of items.
     * @return The remaining budget.
     */
    public double getRemainingBudget(double totalCost) {
        return amount - totalCost;
    }

    public void resetBudget() {
        amount = MIN_BUDGET;
    }
}
