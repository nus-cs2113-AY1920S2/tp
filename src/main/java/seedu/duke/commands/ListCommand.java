package seedu.duke.commands;

import seedu.duke.data.Item;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "DISPLAY";

    public static final String LIST_MESSAGE = System.lineSeparator() + "Here is your shopping list:\n";

    public static final String TOTAL_COST_MESSAGE = "Total cost of items: %.2f\n";

    public static final String BUDGET_MESSAGE = "Your remaining budget / total budget: %.2f / %.2f\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all items in the list."
            + System.lineSeparator() + "|| Parameters: DISPLAY"
            + System.lineSeparator() + "|| Example: DISPLAY" + System.lineSeparator();

    @Override
    public CommandResult execute() {

        System.out.println(LIST_MESSAGE);
        items.showTableOfItems();

        double totalCost = items.getTotalCost();

        String feedback = String.format(TOTAL_COST_MESSAGE, totalCost)
                + String.format(BUDGET_MESSAGE, myBudget.getRemainingBudget(totalCost), myBudget.getAmount());

        return new CommandResult(feedback);
    }
}
