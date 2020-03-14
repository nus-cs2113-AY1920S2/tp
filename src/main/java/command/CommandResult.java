package command;

/**
 * Result from running the Command to be shown to user.
 */
public class CommandResult {

    public String feedbackToUser;

    /**
     * Default constructor to initialise the input to be shown to user.
     * @param feedbackToUser String to be shown to user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
}
