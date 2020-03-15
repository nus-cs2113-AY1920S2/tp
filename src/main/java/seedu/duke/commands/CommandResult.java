package seedu.duke.commands;

import seedu.duke.data.Item;

import java.util.ArrayList;

/**
 * Gets a feedback message from a command and shows it to the user.
 */
public class CommandResult {

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
