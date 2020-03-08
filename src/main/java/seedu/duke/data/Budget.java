package seedu.duke.data;

public class Budget {
    private static double budget;

    public Budget(double i) {
        this.budget = i;
    }

    public static double getBudget() {
        return budget;
    }

    public static void resetBudget() {
        budget = 0.0;
    }
}
