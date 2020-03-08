package seedu.duke.data;

public class Budget {
    final static double MAX = 5000;
    final static double MIN = 0;
    private double amount;

    public Budget() {
        this.amount = 0;
    }

    public void setBudget(double amount){
        this.amount = Math.min(amount,MAX);
        this.amount = Math.max(amount,MIN);
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
