package seedu.duke.commands;

import seedu.duke.data.ShoppingList;

public class DeleteCommand extends Command {

    private int index;

    public static final String COMMAND_WORD = "DEL";

    public static final String DELETE_MESSAGE = "Got it! I have removed the following item:\n";

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            index -= 1;
            String feedback = DELETE_MESSAGE + ShoppingList.getItem(index);
            ShoppingList.deleteItem(index);
            return new CommandResult(feedback);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(System.lineSeparator()
                    + "Please enter a valid index within the bounds");
        }

    }

}
