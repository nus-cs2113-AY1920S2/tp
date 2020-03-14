package seedu.duke.commands;

import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

public class Command {

    protected ShoppingList items;
    protected Budget myBudget;
    public boolean isExit = false;

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public void setData(ShoppingList items,Budget myBudget) {
        this.items = items;
        this.myBudget = myBudget;
    }

}
