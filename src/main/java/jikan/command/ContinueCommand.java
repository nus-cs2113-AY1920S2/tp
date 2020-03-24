package jikan.command;

import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

/**
 * Represents a command to continue recording an existing activity.
 */
public class ContinueCommand extends Command {

    /**
     * Constructor to create a new continue command.
     */
    public ContinueCommand() {
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            Parser.parseContinue(activityList);
        } catch (NoSuchActivityException e) {
            Ui.printDivider("No activity with this name exists!");
            Log.makeInfoLog("Continue command failed as there was no such activity saved.");
        }
    }
}

