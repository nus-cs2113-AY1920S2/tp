package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;

/**
 * Super class for all the types of command sub-classes.
 */

public abstract class Command {

    public Command() throws InvalidFormatException {

    }

    /**
     * Method to return false in default for exit.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execution method that will be overriden its child classes (the different command classes).
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     */
    public abstract void execute(Ui ui, Storage storage) throws IOException, ParseException;
}
