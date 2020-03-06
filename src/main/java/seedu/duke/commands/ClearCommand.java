package seedu.duke.commands;

import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "CLEAR";

    public static final String LIST_CLEARED_MESSAGE = "Your shopping list has been cleared.\n";

    @Override
    public CommandResult execute(ShoppingList items, Budget myBudget) {
        items.clearList();
        String feedback = LIST_CLEARED_MESSAGE;
        return new CommandResult(feedback);
    }
}
