package jikan.command;

import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.InvalidTimeFrameException;


/**
 * Represents an executable command.
 */
public class Command {
    protected String parameters;

    /**
     * Constructor to create a new command.
     */
    public Command(String parameters) {
        this.parameters = parameters;
    }

    /**
     * Executes the command and returns the result.
     */
    public void executeCommand(ActivityList activityList) throws EmptyNameException, InvalidTimeFrameException {
    }


}

