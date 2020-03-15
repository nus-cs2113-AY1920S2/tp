package seedu.nuke.command;

import java.util.ArrayList;

/**
 *construct the feedback to user String
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean isShowTasks;
    private final ArrayList<String> shownList;

    /**
     * construct a command result that only have one feedback
     * @param feedbackToUser execution feedback that should be shown to the user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isShowTasks = false;
        shownList = null;
    }

    /**
     * construct a command result that have feedback and lists of information that needs to be printed out
     * @param feedbackToUser
     * @param isShowTasks
     * @param shownList
     */
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

    public ArrayList<String> getShownList() {
        return shownList;
    }
}
