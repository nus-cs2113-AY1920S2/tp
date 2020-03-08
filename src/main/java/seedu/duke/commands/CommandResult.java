package seedu.duke.commands;


import seedu.duke.data.Item;

import java.util.ArrayList;

public class CommandResult {
    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final String feedbackToUser;
    public final ArrayList<Item> items;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.items = null;
    }
    public CommandResult(String feedbackToUser, ArrayList<Item> items) {
        this.feedbackToUser = feedbackToUser;
        this.items = items;
    }
}
