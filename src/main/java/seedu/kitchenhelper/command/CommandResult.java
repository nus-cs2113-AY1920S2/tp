package seedu.kitchenhelper.command;

/**
 * Returns message outcomes of commands upon execution.
 */
public class CommandResult {
    
    public final String feedbackToUser;

    /**
     * Constructor for CommandResult.
     *
     * @param feedbackToUser text to be shown to user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
    
}
