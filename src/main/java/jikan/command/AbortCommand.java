package jikan.command;

import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;
import jikan.Log;

/**
 * Represents a command to abort a currently running activity.
 */
public class AbortCommand extends Command {

    /**
     * Constructor to create a new abort command.
     */
    public AbortCommand() {
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            Parser.parseAbort();
        } catch (NoSuchActivityException e) {
            Ui.printDivider("You have not started any activity!");
            Log.makeInfoLog("Abort command failed as no activity was ongoing");
        }
    }
}
