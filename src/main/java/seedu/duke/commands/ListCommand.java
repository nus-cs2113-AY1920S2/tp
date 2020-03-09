package seedu.duke.commands;

import seedu.duke.data.Item;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "DISPLAY";

    public static final String LIST_MESSAGE = "Here is your shopping list:\n";

    public static final String TOTAL_COST_MESSAGE = "Total cost of items: %.2f\n";

    public static final String BUDGET_MESSAGE = "Your remaining budget / total budget: %.2f / %.2f\n";

    @Override
    public CommandResult execute() {

        String feedback = LIST_MESSAGE;
        feedback = items.compileList(feedback);

        double totalCost = 0.0;
        for (Item item : items.getList()) {
            totalCost += item.getPrice();
        }

        feedback = feedback + String.format(TOTAL_COST_MESSAGE, totalCost);

        feedback = feedback
                + String.format(BUDGET_MESSAGE, myBudget.getRemainingBudget(totalCost), myBudget.getAmount());

        return new CommandResult(feedback);
    }
}
