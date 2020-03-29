package jikan.command;

import jikan.Log;
import jikan.activity.Activity;
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
        super(parameters);
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            if (Parser.startTime != null) {
                String line = Parser.activityName + " is ongoing!";
                Ui.printDivider("Could not continue activity due to already ongoing activity.");
                return;
            }
            //Parser.parseContinue(activityList);
            int index = activityList.findActivity(parameters);
            if (index != -1) {
                // activity is found
                Parser.activityName = activityList.get(index).getName();
                Parser.startTime = LocalDateTime.now();
                Parser.tags = activityList.get(index).getTags();
                Parser.continuedIndex = index;
                String line = Parser.activityName + " was continued";
                Ui.printDivider(line);
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

