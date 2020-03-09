package seedu.nuke.command;

/*
    construct the feedback to user String
 */
public class CommandResult {
    public final String feedbackToUser;

    /**
     * @parameter: Execute feedback to user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        //display result
        /*
        if (feedbackToUser!= null)
            TextUi.showResult(this.feedbackToUser);

        **/
    }
}
