package seedu.nuke.command;

/*
    construct the feedback to user String
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean isShowTasks;

    /**
     * @parameter: Execute feedback to user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isShowTasks = false;
    }

    public CommandResult(String feedbackToUser, boolean isShowTasks) {
        this.feedbackToUser = feedbackToUser;
        this.isShowTasks = isShowTasks;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowTasks() {
        return isShowTasks;
    }
}
