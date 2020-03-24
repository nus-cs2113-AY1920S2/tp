package jikan.parser;

import jikan.command.AbortCommand;
import jikan.command.ByeCommand;
import jikan.command.Command;
import jikan.command.CleanCommand;
import jikan.command.ContinueCommand;
import jikan.command.DeleteCommand;
import jikan.command.EditCommand;
import jikan.command.EndCommand;
import jikan.command.FindCommand;
import jikan.command.FilterCommand;
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

    public static LocalDateTime startTime = null;
    public static LocalDateTime endTime = null;
    public static String activityName = null;
    public static Set<String> tags = new HashSet<>();
    private static Ui ui = new Ui();
    private static StorageCleaner cleaner;
    public static String[] tokenizedInputs;
    String instruction;
    private static Log logger = new Log();
    // public static ActivityList lastShownList = new ActivityList();
    public static ActivityList lastShownList = new ActivityList();
    // flag to check if the current activity is a continued one
    public static int continuedIndex = -1;

    /**
     * Parses user commands to relevant functions to carry out the commands.
     * @param scanner scanner object which reads user input
     * @param activityList the list of activities
     */
    public Command parseUserCommands(Scanner scanner, ActivityList activityList, StorageCleaner cleaner) {
        Log.makeInfoLog("Starting to parse inputs.");
        Parser.cleaner = cleaner;
        /*lastShownList is initialised here to facilitate subsequent delete and edit commands
        referencing by index of this list.
         */
        lastShownList.activities.addAll(activityList.activities);
        String userInput = scanner.nextLine();
        tokenizedInputs = userInput.split(" ", 2);
        instruction = tokenizedInputs[0];
        Command command = null;

        switch (instruction) {
        case "bye":
            command = new ByeCommand(null);
            break;
        case "start":
            command = new StartCommand(tokenizedInputs[1], scanner);
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
            command = new DeleteCommand(tokenizedInputs[1]);
            break;
        case "find":
            command = new FindCommand(tokenizedInputs[1]);
            break;
        case "filter":
            command = new FilterCommand(tokenizedInputs[1]);
            break;
        case "edit":
            command = new EditCommand(tokenizedInputs[1]);
            break;
        case "clean":
            command = new CleanCommand(tokenizedInputs[1], cleaner);
            break;
        case "continue":
            command = new ContinueCommand(tokenizedInputs[1]);
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

    /** Method to parse user inputs that are not recognised. */
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