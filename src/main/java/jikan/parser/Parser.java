package jikan.parser;

import jikan.exception.EmptyNameException;
import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NoSuchActivityException;
import jikan.ui.Ui;
import jikan.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents the object which parses user input to relevant functions for the execution of commands.
 */
public class Parser {

    protected LocalDateTime startTime = null;
    protected LocalDateTime endTime = null;
    private String activityName = null;
    private Set<String> tags = new HashSet<String>();
    private Ui ui = new Ui();
    protected String[] tokenizedInputs;
    String instruction;
    Log logger = new Log();
    private ActivityList lastShownList = new ActivityList();

    /**
     * Parses user commands to relevant functions to carry out the commands.
     * @param scanner scanner object which reads user input
     * @param activityList the list of activities
     */
    public void parseUserCommands(Scanner scanner, ActivityList activityList) {
        logger.makeInfoLog("Starting to parse inputs.");
        /*lastShownList is initialised here to facilitate subsequent delete and edit commands
        referencing by index of this list.
         */
        lastShownList.activities.addAll(activityList.activities);
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
                } catch (InvalidTimeFrameException e) {
                    logger.makeInfoLog("End date must be before start date");
                    ui.printDivider("End date must be before start date.");
                }
                break;
            case "list":
                try {
                    parseList(activityList);
                } catch (InvalidTimeFrameException e) {
                    logger.makeInfoLog("Specified time range not valid");
                    ui.printDivider("The time range specified is not valid.");
                }
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
            case "find":
                try {
                    parseFind(activityList, lastShownList, tokenizedInputs[1]);
                } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
                    ui.printDivider("No keyword was given.");
                }
                break;
            case "filter":
                try {
                    parseFilter(activityList, lastShownList, tokenizedInputs[1]);
                } catch (ArrayIndexOutOfBoundsException | EmptyQueryException e) {
                    ui.printDivider("No keyword was given.");
                }
                break;
            default:
                parseDefault();
                break;
            }
        }
    }

    /**
     * Shows the user all past activities that has names which match the keyword queried by the user.
     * @param activityList the activity list to search for matching activities
     * @param lastShownList the activity list to populate with matching activities only
     * @param keyword the keyword queried by the user
     */
    private void parseFind(ActivityList activityList, ActivityList lastShownList, String keyword)
            throws EmptyQueryException {
        if (keyword.length() < 1) {
            throw new EmptyQueryException();
        } else {
            lastShownList.activities.clear();
            for (Activity i : activityList.activities) {
                if (i.getName().contains(keyword)) {
                    lastShownList.activities.add(i);
                }
            }
            Ui.printResults(lastShownList);
        }
    }

    /**
     * Shows the user all past activities that has tags which match the keywords queried by the user.
     * @param activityList the activity list to search for matching activities
     * @param lastShownList the activity list to populate with matching activities only
     * @param query the keywords queried by the user
     */
    private void parseFilter(ActivityList activityList, ActivityList lastShownList, String query)
            throws EmptyQueryException {
        if (query.length() < 1) {
            throw new EmptyQueryException();
        } else {
            lastShownList.activities.clear();
            String[] keywords = query.split(" ");

            for (String keyword : keywords) {
                populateLastShownList(activityList, lastShownList, keyword);
            }
            Ui.printResults(lastShownList);
        }
    }

    /**
     * Populates the last shown list with activities that contain tags which match the given keyword.
     * @param activityList the list of activity to search
     * @param lastShownList the last shown list to populate
     * @param keyword the keyword to match
     */
    private void populateLastShownList(ActivityList activityList, ActivityList lastShownList, String keyword) {
        for (Activity i : activityList.activities) {
            if (!lastShownList.activities.contains(i) && i.getTags().contains(keyword)) {
                lastShownList.activities.add(i);
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
            tags = new HashSet<String>();
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
            assert tags.isEmpty();

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
                parseTags(delimiter);
            }
            startTime = LocalDateTime.now();
            ui.printDivider(line);
        }
        logger.makeFineLog("Started: " + activityName);
    }

    /**
     * Inserts the tags into the activity object when it has ended.
     * @param delimiter the index of the tag delimiter
     */
    private void parseTags(int delimiter) {
        String[] tagString = tokenizedInputs[1].substring(delimiter + 3).split(" ");
        for (String i :tagString) {
            tags.add(i);
        }
    }

    /** Method to parse the end activity command. */
    public void parseEnd(ActivityList activityList) throws NoSuchActivityException, InvalidTimeFrameException {
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
            tags = new HashSet<String>();
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

    /**
     * Parse a list command. The user can specify either a single date or a specific time frame.
     *
     * @param activityList The activity list to search for matching activities.
     */
    private void parseList(ActivityList activityList) throws InvalidTimeFrameException {

        // If no time frame is specified, print the entire list
        if (tokenizedInputs.length == 1) {
            lastShownList.activities.clear();
            ui.printList(activityList);
            lastShownList = activityList;
            return;
        }

        lastShownList.activities.clear();

        // Parse either format
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd]");

        LocalDate startDate = LocalDate.parse(tokenizedInputs[1], parser);

        // Only one date is specified; return all entries with start date coinciding with that date
        if (tokenizedInputs.length == 2) {
            for (Activity i : activityList.activities) {
                if (i.getDate().equals(startDate)) {
                    lastShownList.activities.add(i);
                }
            }
            ui.printList(lastShownList);
            // Both start and end dates are specified
        } else if (tokenizedInputs.length == 3) {
            LocalDate endDate = LocalDate.parse(tokenizedInputs[2], parser);
            for (Activity i : activityList.activities) {
                if (i.isWithinDateFrame(startDate, endDate)) {
                    lastShownList.activities.add(i);
                }
            }
            ui.printList(lastShownList);
        } else {
            throw new InvalidTimeFrameException();
        }

    }
}