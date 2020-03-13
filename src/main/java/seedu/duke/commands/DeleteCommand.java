package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private int index;

    public static final String COMMAND_WORD = "DEL";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an item in the list."
            + System.lineSeparator() + "|| Parameters: DEL [INDEX]"
            + System.lineSeparator() + "|| Example: DEL 1" + System.lineSeparator();
    public static final String DELETE_MESSAGE = "\nGot it! I have removed the following item:\n";
    public static final String DELETE_MESSAGE_FAILURE = System.lineSeparator()
            + "Please enter a valid index within the bounds";

    /**
     * Constructs the delete command with the user-specified index.
     *
     * @param index Index of the item to be removed.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
        LOGGER.log(Level.INFO,"(Delete command) User entered index " + index);
    }

    @Override
    public CommandResult execute() {
        try {
            index -= 1;
            String feedback = DELETE_MESSAGE + items.getItem(index);
            items.deleteItem(index);
            LOGGER.log(Level.INFO,"(Delete command) Item was removed from index: " + index);
            return new CommandResult(feedback);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING,"(Delete command) Invoked with invalid index: " + this.index);
            return new CommandResult(DELETE_MESSAGE_FAILURE);
        }

    }

}
