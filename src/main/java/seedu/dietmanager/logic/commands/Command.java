package seedu.dietmanager.logic.commands;

import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.Profile;
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

    protected String resultString;

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
     * Abstract method to save execution result of the command.
     *
     * @param profile the profile that the command is dealing with.
     * @return
     */

    public abstract Result getResult(Profile profile);

    /**
     * Abstract method to execute the Command.
     *
     * @param profile the object containing user profile information.
     * @param ui      the object containing user interface functions.
     * @return
     */

    public abstract Result execute(Profile profile, UI ui);

}
