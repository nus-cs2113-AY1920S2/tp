package command;

/**
 * Result from running the Command to be shown to user.
 */
public class CommandResult {

    public String feedbackToUser;
    protected boolean isExit;

    /**
     * Default constructor to initialise the input to be shown to user.
     * @param feedbackToUser String to be shown to user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = false;
    }

    /**
     * Getter for the boolean value of isExit.
     * @return boolean isExit
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns a CommandResult object with boolean isExit set to true.
     * @return CommandResult object with modified isExit
     */
    public CommandResult setExit() {
        this.isExit = true;
        return this;
    }
}
