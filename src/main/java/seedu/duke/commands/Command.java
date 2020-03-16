package seedu.duke.commands;

import seedu.duke.Profile;
import seedu.duke.ui.UI;

/**
 * Command is a public abstract class providing a skeletal implementation for
 * different commands and is responsible for storing the base information
 * required in a command, allowing other more specific events to draw onto its
 * components.
 */

public abstract class Command {

    /**
     * The command prompt entered by the user.
     */

    protected String command;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public Command(String command) {
        this.command = command;
    }

    /**
     * Returns the command prompt entered by the user.
     *
     * @return the command prompt entered by the user.
     */

    public String getCommand() {
        return this.command;
    }

    /**
     * Abstract method to execute the Command.
     *
     * @param profile the object containing user profile information.
     * @param ui      the object containing user interface functions.
     */

    public abstract void execute(Profile profile, UI ui);

}
