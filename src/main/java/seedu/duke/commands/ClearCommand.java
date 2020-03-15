package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clears the shopping list.
 */
public class ClearCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static final String COMMAND_WORD = "CLEAR";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the list."
            + System.lineSeparator() + "|| Parameters: CLEAR"
            + System.lineSeparator() + "|| Example: CLEAR" + System.lineSeparator();
    public static final String MESSAGE_SUCCESS = System.lineSeparator() + "Your shopping list has been cleared.\n";

    @Override
    public CommandResult execute() {

        items.clearList();
        assert  items.getList().size() == 0 : "Size of list should be zero.";

        LOGGER.log(Level.INFO,"(Clear command) List of items is now empty.");

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
