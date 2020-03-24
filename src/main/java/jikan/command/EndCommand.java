package jikan.command;

import jikan.Log;
import jikan.activity.ActivityList;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.util.Scanner;

/**
 * Represents a command to end an activity.
 */
public class EndCommand extends Command {


    private Scanner scanner;

    /**
     * Constructor to create a new end command.
     */
    public EndCommand(String parameters) {
        super(parameters);
    }

    /** Method to parse the end activity command. */
    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            // Parser.parseEnd(activityList);
            if (Parser.startTime == null) {
                throw new NoSuchActivityException();
            } else {
                activityList.saveActivity(activityList);
            }
        } catch (NoSuchActivityException e) {
            Log.makeInfoLog("End command failed as no activity was ongoing");
            Ui.printDivider("You have not started any activity!");
        } catch (InvalidTimeFrameException e) {
            Log.makeInfoLog("End date must be before start date");
            Ui.printDivider("End date must be before start date.");
        }
    }
}
