package seedu.duke.commands;

import seedu.duke.data.ShoppingList;

import java.util.List;

public class Command {
    /**
     * Executes the command and returns the result.
     */
    protected ShoppingList items;

    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(ShoppingList items) {
        this.items = items;
    }
}
