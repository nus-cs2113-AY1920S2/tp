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
import jikan.activity.ActivityList;
import jikan.storage.StorageCleaner;
import jikan.ui.Ui;
import jikan.Log;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static jikan.Log.makeInfoLog;

/**
 * Represents the object which parses user input to relevant functions for the execution of commands.
 */
public class Parser {

    public static LocalDateTime startTime = null;
    public static LocalDateTime endTime = null;
    public static String activityName = null;
    public static Set<String> tags = new HashSet<>();
    private static StorageCleaner cleaner;
    public static String[] tokenizedInputs;
    String instruction;
    private static Log logger = new Log();

    // flag to check if the current activity is a continued one
    public static int continuedIndex = -1;

    /**
     * Parses user commands to relevant functions to carry out the commands.
     *
     * @param scanner      scanner object which reads user input
     * @param activityList the list of activities
     */
    public Command parseUserCommands(Scanner scanner, ActivityList activityList, StorageCleaner cleaner) throws
            EmptyNameException, NullPointerException, ArrayIndexOutOfBoundsException {
        makeInfoLog("Starting to parse inputs.");
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
                makeInfoLog("Activity started without activity name");
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
            if (tokenizedInputs.length == 1) {
                command = new ListCommand(null);
            } else {
                command = new ListCommand(tokenizedInputs[1]);
            }
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
                makeInfoLog("Edit command failed as there was no existing activity name provided.");
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
                makeInfoLog("Continue command failed as there was no activity name provided.");
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
     * Method to parse user inputs that are not recognised.
     */
    private void parseDefault() {
        String line = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        makeInfoLog("Invalid command entered");
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