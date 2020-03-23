package jikan.command;

import jikan.activity.ActivityList;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

/**
 * Represents a command to delete an activity from the activity list.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor to create a new delete command.
     */
    public DeleteCommand() {
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            Parser.parseDelete(activityList);
        } catch (NoSuchActivityException e) {
            Ui.printDivider("No activity with this name exists!");
        }
    }
}
