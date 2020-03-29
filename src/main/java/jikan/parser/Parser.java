package jikan.parser;

import jikan.command.Command;
import jikan.command.AbortCommand;
import jikan.command.ByeCommand;
import jikan.command.CleanCommand;
import jikan.command.ContinueCommand;
import jikan.command.DeleteCommand;
import jikan.command.EditCommand;
import jikan.command.EndCommand;
import jikan.command.FilterCommand;
import jikan.command.FindCommand;
import jikan.command.GraphCommand;
import jikan.command.ListCommand;
import jikan.command.StartCommand;

import jikan.exception.EmptyNameException;
import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyQueryException;
import jikan.exception.InvalidTimeFrameException;
import jikan.exception.NoSuchActivityException;
import jikan.storage.StorageCleaner;
import jikan.ui.Ui;
import jikan.Log;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static jikan.Jikan.lastShownList;

/**
 * Represents the object which parses user input to relevant functions for the execution of commands.
 */
public class Parser {

    public static LocalDateTime startTime = null;
    public static LocalDateTime endTime = null;
    public static String activityName = null;
    public static Set<String> tags = new HashSet<>();
    private static Ui ui = new Ui();
    private static StorageCleaner cleaner;
    public static String[] tokenizedInputs;
    String instruction;
    private static Log logger = new Log();
    public static ActivityList lastShownList = new ActivityList();
    // flag to check if the current activity is a continued one
    public static int continuedIndex = -1;

    /**
     * Parses user commands to relevant functions to carry out the commands.
     *
     * @param scanner      scanner object which reads user input
     * @param activityList the list of activities
     */
    public Command parseUserCommands(Scanner scanner, ActivityList activityList, StorageCleaner cleaner) throws EmptyNameException, NullPointerException,
            ArrayIndexOutOfBoundsException {
        Log.makeInfoLog("Starting to parse inputs.");
        Parser.cleaner = cleaner;
        /*lastShownList is initialised here to facilitate subsequent delete and edit commands
        referencing by index of this list.
         */
        // lastShownList.activities.addAll(activityList.activities);
        String userInput = scanner.nextLine();
        tokenizedInputs = userInput.split(" ", 2);
        instruction = tokenizedInputs[0];
        Command command = null;

        switch (instruction) {
        case "bye":
            command = new ByeCommand(null);
            break;
        case "start":
            try {
                command = new StartCommand(tokenizedInputs[1], scanner);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                Log.makeInfoLog("Activity started without activity name");
                Ui.printDivider("Activity name cannot be empty!");
            }
            break;
        case "end":
            command = new EndCommand(null);
            break;
        case "abort":
            command = new AbortCommand(null);
            break;
        case "list":
            command = new ListCommand(null);
            break;
        case "delete":
            try {
                command = new DeleteCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("Activity name cannot be empty!");
            }
            break;
        case "find":
            try {
                command = new FindCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("No keyword was given.");
            }
            break;
        case "filter":
            try {
                command = new FilterCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("No keyword was given.");
            }
            break;
        case "edit":
            try {
                command = new EditCommand(tokenizedInputs[1]);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("Activity name cannot be empty!");
                Log.makeInfoLog("Edit command failed as there was no existing activity name provided.");
            }
            break;
        case "clean":
            try {
                command = new CleanCommand(tokenizedInputs[1], cleaner);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("No keyword was given.");
            }
            break;
        case "continue":
            try {
                command = new ContinueCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("Activity name cannot be empty!");
                Log.makeInfoLog("Continue command failed as there was no activity name provided.");
            }
            break;
        case "graph":
            try {
                command = new GraphCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                Ui.printDivider("Please input an integer for the time interval.\n"
                        + "If you'd like to graph by tags, enter the command <graph tags>.");
            }
            break;
        default:
            parseDefault();
            break;
        }
        return command;
    }

    /**
     * Parse a list command. The user can specify either a single date or a specific time frame.
     *
     * @param activityList The activity list to search for matching activities.
     */
    public static void parseList(ActivityList activityList) throws InvalidTimeFrameException, DateTimeParseException {

        // If no time frame is specified, print the entire list
        if (tokenizedInputs.length == 1) {
            lastShownList.activities.clear();
            Ui.printList(activityList);

            // Can't do lastShownList = activityList, otherwise we just copy
            lastShownList.activities.addAll(activityList.activities);
            return;
        }

        String[] listInputs;
        listInputs = tokenizedInputs[1].split(" ", 2);

        lastShownList.activities.clear();

        LocalDate startDate = null;
        LocalDate endDate = null;

        // Parse either format
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd]");

        // Check if the user has given a verbal input
        // (User can either say day or daily and get the same output)
        switch (listInputs[0]) {
        case "day":
            // Fallthrough
        case "daily":
            startDate = LocalDate.now();
            break;
        case "week":
            // Fallthrough
        case "weekly":
            // If user has input a specific date to obtain the week from, use that;
            // (eg. the input is list week 2020-05-20)
            // Otherwise get current date
            if (listInputs.length == 2) {
                startDate = LocalDate.parse(listInputs[1], parser);
            } else {
                startDate = LocalDate.now();
            }

            // Set current Monday and Sunday as time range
            startDate = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            endDate = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            break;
        case "month":
            // Fallthrough
        case "monthly":
            // If user has input a specific date to obtain the month from, use that;
            // Otherwise get current date
            if (listInputs.length == 2) {
                startDate = LocalDate.parse(listInputs[1], parser);
            } else {
                startDate = LocalDate.now();
            }

            // Set first and last day of month as time range
            startDate = startDate.withDayOfMonth(1);
            endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
            break;
        default:
            startDate = LocalDate.parse(listInputs[0], parser);
            if (listInputs.length == 2) {
                endDate = LocalDate.parse(listInputs[1], parser);
            }
            break;
        }

        // Only one date is specified; return all entries with start date coinciding with that date
        if (endDate == null) {
            for (Activity i : activityList.activities) {
                if (i.getDate().equals(startDate)) {
                    lastShownList.activities.add(i);
                }
            }
            Ui.printList(lastShownList);
            // Both start and end dates are specified
        } else {

            if (endDate.isBefore(startDate)) {
                throw new InvalidTimeFrameException();
            }

            for (Activity i : activityList.activities) {
                if (i.isWithinDateFrame(startDate, endDate)) {
                    lastShownList.activities.add(i);
                }
            }
            Ui.printList(lastShownList);
        }
    }

    /**
     * Method to parse user inputs that are not recognised.
     */
    private void parseDefault() {
        String line = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        Log.makeInfoLog("Invalid command entered");
        Ui.printDivider(line);
    }


    /**
     * Resets parameters, called when an activity is ended or aborted.
     */
    public static void resetInfo() {
        startTime = null;
        activityName = null;
        tags = new HashSet<>();
    }
}