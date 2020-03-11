package seedu.duke.commands;

import seedu.duke.data.ShoppingList;

public class DeleteCommand extends Command {

    private int index;

    public static final String COMMAND_WORD = "DEL";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an item in the list."
            + System.lineSeparator() + "|| Parameters: DEL [INDEX]"
            + System.lineSeparator() + "|| Example: DEL 1" + System.lineSeparator();
    public static final String DELETE_MESSAGE = "Got it! I have removed the following item:\n";

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            index -= 1;
            String feedback = DELETE_MESSAGE + items.getItem(index);
            items.deleteItem(index);
            return new CommandResult(feedback);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(System.lineSeparator()
                    + "Please enter a valid index within the bounds");
        }

    }

}
