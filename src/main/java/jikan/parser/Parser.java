package jikan.parser;

import jikan.exception.EmptyNameException;
import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NoSuchActivityException;
import jikan.ui.Ui;
import jikan.Log;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
    private Set<String> tags = new HashSet<>();
    private Ui ui = new Ui();
    protected String[] tokenizedInputs;
    String instruction;
    Log logger = new Log();
    private ActivityList lastShownList = new ActivityList();

    // flag to check if the current activity is a continued one
    public int continuedIndex = -1;

    /**
     * Parses user commands to relevant functions to carry out the commands.
     * @param scanner scanner object which reads user input
     * @param activityList the list of activities
     */
    public void parseUserCommands(Scanner scanner, ActivityList activityList) throws InvalidTimeFrameException {
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
                //ui.exitFromApp();
                parseBye(activityList, scanner);
                break;
            case "start":
                try {
                    parseStart(activityList, scanner);
                } catch (EmptyNameException | NullPointerException | ArrayIndexOutOfBoundsException e) {
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
                } catch (DateTimeParseException e) {
                    logger.makeInfoLog("Specified time range was not in the valid format");
                    ui.printDivider("Please input your dates as either yyyy-MM-dd or dd/MM/yyyy.");
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
            case "continue":
                parseContinue(activityList);
                break;
            default:
                parseDefault();
                break;
            }
        }
    }

    public void parseBye(ActivityList activityList, Scanner scanner) throws InvalidTimeFrameException {
        if (startTime != null) {
            String line = activityName + " is still running! If you exit now it will be aborted.\n" +
                    "Would you like to end this activity to save it?";
            ui.printDivider(line);
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
                saveActivity(activityList);
            }
        }
        ui.exitFromApp();
    }

    /** Method to parse the start activity command. */
    public void parseStart(ActivityList activityList, Scanner scanner) throws ArrayIndexOutOfBoundsException, EmptyNameException, NullPointerException {
        // check if an activity has already been started
        if (startTime != null) {
            String line = activityName + " is ongoing!";
            logger.makeInfoLog("Could not start activity due to already ongoing activity.");
            ui.printDivider(line);
        } else {
            // tags should be reset
            assert tags.isEmpty();

            String line;
            // check if there exists an activity with this name
            if (activityList.findActivity(tokenizedInputs[1]) != -1) {
                line = "There is already an activity with this name. Would you like to continue it?";
                ui.printDivider(line);
                continueActivity(activityList, scanner);
            } else {
                int delimiter = tokenizedInputs[1].indexOf("/t");
                line = parseActivity(delimiter);
                startTime = LocalDateTime.now();
                logger.makeFineLog("Started: " + activityName);
                ui.printDivider(line);
            }
        }
    }

    private void continueActivity(ActivityList activityList, Scanner scanner) {
        String line;
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
            parseContinue(activityList);
        } else {
            line = "Okay then, what else can I do for you?";
            ui.printDivider(line);
        }
    }

    /**
     * Inserts the tags into the activity object when it has ended.
     * @param delimiter the index of the tag delimiter
     */
    private String parseActivity(int delimiter) throws EmptyNameException {
        if(delimiter == -1) {
            // no tags
            activityName = tokenizedInputs[1].strip();
            if (activityName.isEmpty()) {
                throw new EmptyNameException();
            }
        } else {
            activityName = tokenizedInputs[1].substring(0, delimiter).strip();
            if (activityName.isEmpty()) {
                throw new EmptyNameException();
            }
            String[] tagString = tokenizedInputs[1].substring(delimiter + 3).split(" ");
            tags.addAll(Arrays.asList(tagString));
        }
        return "Started: " + activityName;
    }

    /** Method to parse the end activity command. */
    public void parseEnd(ActivityList activityList) throws NoSuchActivityException, InvalidTimeFrameException {
        if (startTime == null) {
            throw new NoSuchActivityException();
        } else {
            saveActivity(activityList);
        }
        logger.makeFineLog("Ended: " + activityName);
    }


    /**
     * Parse a list command. The user can specify either a single date or a specific time frame.
     *
     * @param activityList The activity list to search for matching activities.
     */
    private void parseList(ActivityList activityList) throws InvalidTimeFrameException, DateTimeParseException {

        // If no time frame is specified, print the entire list
        if (tokenizedInputs.length == 1) {
            lastShownList.activities.clear();
            Ui.printList(activityList);

            // Can't do lastShownList = activityList, otherwise we just copy
            lastShownList.activities.addAll(activityList.activities);
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
            Ui.printList(lastShownList);
            // Both start and end dates are specified
        } else if (tokenizedInputs.length == 3) {
            LocalDate endDate = LocalDate.parse(tokenizedInputs[2], parser);

            if (endDate.isBefore(startDate)) {
                throw new InvalidTimeFrameException();
            }

            for (Activity i : activityList.activities) {
                if (i.isWithinDateFrame(startDate, endDate)) {
                    lastShownList.activities.add(i);
                }
            }
            Ui.printList(lastShownList);
        } else {
            throw new InvalidTimeFrameException();
        }

    }

    /** Method to parse the abort command. */
    public void parseAbort() throws NoSuchActivityException {
        if (startTime == null) {
            throw new NoSuchActivityException();
        } else {
            logger.makeFineLog("Aborted " + activityName);
            resetInfo();
            String line = "You have aborted the current activity!";
            ui.printDivider(line);
        }
    }

    /** Deletes an activity from the activity list. */
    public void parseDelete(ActivityList activityList) {
        int index = Integer.parseInt(tokenizedInputs[1]) - 1;
        String line = "You have deleted " + activityList.get(index).getName();
        ui.printDivider(line);
        activityList.delete(index);
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

    public void parseContinue(ActivityList activityList) {
        int index = activityList.findActivity(tokenizedInputs[1]);
        if (index != -1) {
            // activity is found
            activityName = activityList.get(index).getName();
            startTime = LocalDateTime.now();
            tags = activityList.get(index).getTags();
            continuedIndex = index;
            String line = activityName + " was continued";
            ui.printDivider(line);
        } else {
            // activity not found
            System.out.println("No such activity is found!");
        }
    }

    /** Method to parse user inputs that are not recognised. */
    private void parseDefault() {
        String line = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        logger.makeInfoLog("Invalid command entered");
        ui.printDivider(line);
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

    private void saveActivity(ActivityList activityList) throws InvalidTimeFrameException {
        if (continuedIndex != -1) {
            String line = "Ended: " + activityName;
            ui.printDivider(line);
            endTime = LocalDateTime.now();
            Duration duration = Duration.between(startTime, endTime);
            Duration oldDuration = activityList.get(continuedIndex).getDuration();
            Duration newDuration = duration.plus(oldDuration);
            activityList.updateDuration(newDuration, endTime, continuedIndex);
            continuedIndex = -1;
            resetInfo();
        } else {
            String line = "Ended: " + activityName;
            ui.printDivider(line);
            endTime = LocalDateTime.now();
            Duration duration = Duration.between(startTime, endTime);
            Activity newActivity = new Activity(activityName, startTime, endTime, duration, tags);
            activityList.add(newActivity);
            // reset activity info
            resetInfo();
        }
    }

    public void resetInfo() {
        startTime = null;
        activityName = null;
        tags = new HashSet<>();
    }
}