package seedu.duke.commands;

import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

public class Command {
    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute(ShoppingList items, Budget myBudget) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };
}
