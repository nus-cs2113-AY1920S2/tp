package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.ui.UI;

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
     * The last execution result of the command.
     */

    protected String result;

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
     * Returns the last execution result of the command.
     *
     * @return the last execution result of the command.
     */

    public String getResult() {
        return this.result;
    }

    /**
     * Abstract method to save execution result of the command.
     *
     * @param profile the profile that the command is dealing with.
     */

    public abstract void saveResult(Profile profile);

    /**
     * Abstract method to execute the Command.
     *
     * @param profile the object containing user profile information.
     * @param ui      the object containing user interface functions.
     */

    public abstract void execute(Profile profile, UI ui);

}
