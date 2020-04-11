package jikan.command;

import jikan.log.Log;
import jikan.activity.ActivityList;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

/**
 * Represents a command to abort a currently running activity.
 */
public class AbortCommand extends Command {

    /**
     * Constructor to create a new abort command.
     */
    public AbortCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            if (Parser.startTime == null) {
                throw new NoSuchActivityException();
            } else {
                Parser.resetInfo();
                String line = "You have aborted the current activity.";
                Ui.printDivider(line);
            }
        } catch (NoSuchActivityException e) {
            Ui.printDivider("You have not started any activity.");
            Log.makeInfoLog("Abort command failed as no activity was ongoing");
        }
    }
}
