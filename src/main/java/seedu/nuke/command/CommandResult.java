package seedu.nuke.command;

import seedu.nuke.data.task.Task;

import java.util.ArrayList;

/*
    construct the feedback to user String
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean isShowList;
    private final ArrayList<?> list;


    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isShowList = false;
        this.list = null;
    }

    public CommandResult(String feedbackToUser, boolean isShowList, ArrayList<?> listToShow) {
        this.feedbackToUser = feedbackToUser;
        this.isShowList = isShowList;
        this.list = listToShow;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowList() {
        return isShowList;
    }

    public ArrayList<?> getList() {
        return list;
    }
}
