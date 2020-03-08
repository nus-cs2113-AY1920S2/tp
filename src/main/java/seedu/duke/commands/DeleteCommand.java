package seedu.duke.commands;

import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

public class DeleteCommand extends Command {

    private static int index;

    public static final String COMMAND_WORD = "DEL";

    public static final String DELETE_MESSAGE = "Got it! I have removed the following item:\n";

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        index -= 1;
        String feedback = DELETE_MESSAGE + items.getItem(index);
        items.deleteItem(index);
        return new CommandResult(feedback);
    }

}
