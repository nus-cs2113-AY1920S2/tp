package jikan.command;

import jikan.log.Log;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command to continue recording an existing activity.
 */
public class ContinueCommand extends Command {

    /**
     * Constructor to create a new continue command.
     */
    public ContinueCommand(String parameters) {
        super(parameters.strip());
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            if (Parser.startTime != null) {
                Ui.printDivider(Parser.activityName + " is ongoing!");
                Log.makeInfoLog("Could not continue activity due to ongoing activity.");
                return;
            }
            //Parser.parseContinue(activityList);
            int index = activityList.findActivity(parameters);
            if (index != -1) {
                // activity is found
                Parser.activityName = activityList.get(index).getName();
                Parser.tags = activityList.get(index).getTags();
                Parser.startTime = LocalDateTime.now();
                Parser.continuedIndex = index;
                Ui.printDivider(Parser.activityName + " was continued.");
                Log.makeInfoLog(Parser.activityName + " was continued.");
            } else {
                if (parameters.isEmpty()) {
                    throw new EmptyNameException();
                } else {
                    throw new NoSuchActivityException();
                }
            }
        } catch (NoSuchActivityException e) {
            Ui.printDivider("No activity with this name exists!");
            Log.makeInfoLog("Continue command failed as there was no such activity saved.");
        } catch (EmptyNameException e) {
            Ui.printDivider("Activity name cannot be empty!");
            Log.makeInfoLog("Continue command failed as there was no activity name provided.");
        }
    }
}

