package jikan.parser;

import jikan.exception.EmptyNameException;
import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.NoSuchActivityException;
import jikan.ui.Ui;
import jikan.Log;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Represents the object which parses user input to relevant functions for the execution of commands.
 */
public class Parser {

    protected LocalDateTime startTime = null;
    protected LocalDateTime endTime = null;
    private String activityName = null;
    private String[] tags = null;
    private Ui ui = new Ui();
    protected String[] tokenizedInputs;
    String instruction;
    Log logger = new Log();

    /**
     * Parses user commands to relevant functions to carry out the commands.
     * @param scanner scanner object which reads user input
     * @param activityList the list of activities
     */
    public void parseUserCommands(Scanner scanner, ActivityList activityList) {
        logger.makeInfoLog("Starting to parse inputs.");
        while (true) {
            String userInput = scanner.nextLine();
            tokenizedInputs = userInput.split(" ", 2);
            instruction = tokenizedInputs[0];

            switch (instruction) {
            case "bye":
                ui.exitFromApp();
                break;
            case "start":
                try {
                    parseStart();
                } catch (EmptyNameException e) {
                    logger.makeInfoLog("Activity started without task name");
                    ui.printDivider("Task name cannot be empty!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.makeInfoLog("Activity started without task name");
                    ui.printDivider("Task name cannot be empty!");
                } catch (NullPointerException e) {
                    logger.makeInfoLog("Activity started without task name");
                    ui.printDivider("Task name cannot be empty!");
                }
                break;
            case "end":
                try {
                    parseEnd(activityList);
                } catch (NoSuchActivityException e) {
                    logger.makeInfoLog("End command failed as no activity was ongoing");
                    ui.printDivider("You have not started any activity!");
                }
                break;
            case "list":
                ui.printList(activityList);
                break;
            case "abort":
                try {
                    parseAbort();
                } catch (NoSuchActivityException e) {
                    ui.printDivider("You have not started any activity!");
                    logger.makeInfoLog("Abort command failed as no activity was ongoing");
                }
                break;
            case "delete":
                try {
                    parseDelete(activityList);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    ui.printDivider("Invalid index number.");
                }
                break;
            default:
                parseDefault();
                break;
            }
        }
    }

    /** Method to parse user inputs that are not recognised. */
    private void parseDefault() {
        String line = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        logger.makeInfoLog("Invalid command entered");
        ui.printDivider(line);
    }

    /** Method to parse the abort command. */
    public void parseAbort() throws NoSuchActivityException {
        if (startTime == null) {
            throw new NoSuchActivityException();
        } else {
            logger.makeFineLog("Aborted " + activityName);
            startTime = null;
            tags = null;
            activityName = null;
            String line = "You have aborted the current activity!";
            ui.printDivider(line);
        }

    }

    /** Method to parse the start activity command. */
    public void parseStart() throws ArrayIndexOutOfBoundsException, EmptyNameException, NullPointerException {
        // check if an activity has already been started
        if (startTime != null) {
            String line = activityName + " is ongoing!";
            logger.makeInfoLog("Could not start activity due to already ongoing activity.");
            ui.printDivider(line);
        } else {
            // tags should be reset
            assert tags == null;

            String line;
            int delimiter = tokenizedInputs[1].indexOf("/t");
            if (delimiter == -1) {
                activityName = tokenizedInputs[1];
                if (activityName.equals("")) {
                    throw new EmptyNameException();
                }
                line = "Started: " + activityName;
            } else {
                activityName = tokenizedInputs[1].substring(0, delimiter);
                if (activityName.equals("")) {
                    throw new EmptyNameException();
                }
                line = "Started: " + activityName;
                tags = tokenizedInputs[1].substring(delimiter + 3).split(" ");
            }
            startTime = LocalDateTime.now();
            ui.printDivider(line);
        }
        logger.makeFineLog("Started: " + activityName);
    }

    /** Method to parse the end activity command. */
    public void parseEnd(ActivityList activityList) throws NoSuchActivityException {
        if (startTime == null) {
            throw new NoSuchActivityException();
        } else {
            String line = "Ended: " + activityName;
            ui.printDivider(line);
            endTime = LocalDateTime.now();
            Activity newActivity = new Activity(activityName, startTime, endTime, tags);
            activityList.add(newActivity);
            // reset activity info
            startTime = null;
            tags = null;
        }
        logger.makeFineLog("Ended: " + activityName);
    }

    /** Deletes an activity from the activity list. */
    public void parseDelete(ActivityList activityList) {
        int index = Integer.parseInt(tokenizedInputs[1]) - 1;
        String line = "You have deleted " + activityList.get(index).getName();
        ui.printDivider(line);
        activityList.delete(index);
    }
}
