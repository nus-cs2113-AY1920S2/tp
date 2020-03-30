package seedu.happypills.controller.commands.patientcommands;

import seedu.happypills.controller.commands.Command;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;

/**
 * Used for execution of commands.
 * Contains both the information related to user commands and methods to execute them.
 * Implementation is done on child classes.
 */
public abstract class PatientCommand implements Command {
    /**
     * Contain boolean to check for exit condition.
     */
    protected boolean isExit;

    /**
     * Constructor for Command Class.
     * Set isExit to false as default
     */
    protected PatientCommand() {
        isExit = false;
    }

    /**
     * Executes the command based on the information provided by the user.
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @return null
     * @throws HappyPillsException If issues are found during execution of command.
     */
    public String execute(PatientMap patients) throws HappyPillsException {
        return null;
    }

    /**
     * Returns exit condition.
     * @return isExit Contains information that allow the program to exit.
     */
    public boolean isExit() {
        return isExit;
    }
}

