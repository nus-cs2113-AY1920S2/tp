package seedu.nuke.command;

import java.util.ArrayList;

/*
    construct the feedback to user String
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean isShowTasks;
    private final ArrayList<String> shownList;

    /**
     * @parameter: Execute feedback to user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isShowTasks = false;
        shownList = null;
    }

    public CommandResult(String feedbackToUser, boolean isShowTasks, ArrayList<String> shownList) {
        this.feedbackToUser = feedbackToUser;
        this.isShowTasks = isShowTasks;
        this.shownList = shownList;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowTasks() {
        return isShowTasks;
    }

    public ArrayList<String> getShownList() {return shownList;}
}
