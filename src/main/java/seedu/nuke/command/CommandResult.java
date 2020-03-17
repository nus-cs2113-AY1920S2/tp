package seedu.nuke.command;

import seedu.nuke.common.DataType;

import java.util.ArrayList;

/**
 *construct the feedback to user String.
 */
public class CommandResult {
    private final String feedbackToUser;
    private final DataType dataType;
    private final ArrayList<?> shownList;

    /**
     * Constructs a command result that contains both feedback message and list to show.
     *
     * @param feedbackToUser
     *  The feedback message to be shown to the user
     * @param dataType
     *  The data type of the list
     * @param listToShow
     *  The list to be shown to the user
     */
    public CommandResult(String feedbackToUser, DataType dataType, ArrayList<?> listToShow) {
        this.feedbackToUser = feedbackToUser;
        this.dataType = dataType;
        this.shownList = listToShow;
    }

    /**
     * Constructs a command result that contains the feedback message only.
     * @param feedbackToUser
     *  The feedback message to be shown to the user
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, DataType.NONE, null);
    }

    /**
     * Returns the feedback message to be shown to the user.
     *
     * @return
     *  The feedback message to be shown to the user
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Returns the data type of the list to be shown to the user.
     *
     * @return
     *  The data type of the list
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * Returns the list to be shown to the user
     *
     * @return
     *  The list to be shown to the user
     */
    public ArrayList<?> getShownList() {
        return shownList;
    }
}
