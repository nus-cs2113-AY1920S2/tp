package jikan.command;

import jikan.Log;
import jikan.activity.ActivityList;
import jikan.ui.Ui;
import jikan.parser.Parser;

import jikan.exception.InvalidTimeFrameException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to list all activities in the activity list to the user.
 */
public class ListCommand extends Command {

    /**
     * Constructor to create a new list command.
     */
    public ListCommand() {
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            Parser.parseList(activityList);
        } catch (InvalidTimeFrameException e) {
            Log.makeInfoLog("Specified time range not valid");
            Ui.printDivider("The time range specified is not valid.");
        } catch (DateTimeParseException e) {
            Log.makeInfoLog("Specified time range was not in the valid format");
            Ui.printDivider("Please input your dates as either yyyy-MM-dd or dd/MM/yyyy.");
        }
    }
}
