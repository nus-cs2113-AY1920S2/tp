package seedu.duke.commands;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "DISPLAY";

    public static final String LIST_MESSAGE = "Here is your shopping list:\n";

    public static final String TOTAL_COST_MESSAGE = "Total cost of items: %.2f\n";

    public static final String BUDGET_MESSAGE = "Your remaining budget / total budget: %.2f / %.2f\n";

    @Override
    public CommandResult execute() {

        double totalCost = items.getTotalCost();

        String feedback = LIST_MESSAGE
                + items.createStringOfItems()
                + String.format(TOTAL_COST_MESSAGE, totalCost)
                + String.format(BUDGET_MESSAGE, myBudget.getRemainingBudget(totalCost), myBudget.getAmount());

        return new CommandResult(feedback);
    }
}
