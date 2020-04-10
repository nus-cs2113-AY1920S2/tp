package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.logic.commands.Command;

//@@ NyanWunPaing
/**
 * Contains both the information related to user commands and methods to execute them.
 * Implementation is done on child classes.
 */
public abstract class PatientCommand implements Command {
    /**
     * Contains boolean to check for exit condition.
     */
    protected boolean isExit;

    /**
     * Constructor for Command Class.
     * Set isExit to false as default.
     */
    protected PatientCommand() {
        isExit = false;
    }

    /**
     * Executes the command based on the information provided by the user.
     *
     * @return null
     */
    public String execute()  {
        return null;
    }

}

