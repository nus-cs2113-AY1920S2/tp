package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author thanhduc2000
/**
 * Terminate the program when the user's input is "exit".
 *
 * @version 1.0
 * @since 2020-03-14
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Type to exit the program.\n"
            + "Example: " + COMMAND_WORD;

    public ExitCommand() throws InvalidFormatException {

    }

    /**
     * Set the termination of the program to be true.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Method to print the bye message for users.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException when there is error in the index's input
     * @see IOException
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException {
        Ui.showByeMessage();
    }
}
