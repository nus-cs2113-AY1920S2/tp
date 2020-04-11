package jikan.parser;

import jikan.exception.ExtraParametersException;
import jikan.exception.MultipleDelimitersException;
import jikan.log.Log;
import jikan.cleaner.StorageCleaner;
import jikan.storage.Storage;
import jikan.ui.Ui;

import jikan.command.AbortCommand;
import jikan.command.ByeCommand;
import jikan.command.CleanCommand;
import jikan.command.Command;
import jikan.command.ContinueCommand;
import jikan.command.DeleteCommand;
import jikan.command.EditCommand;
import jikan.command.EndCommand;
import jikan.command.FilterCommand;
import jikan.command.FindCommand;
import jikan.command.GoalCommand;
import jikan.command.GraphCommand;
import jikan.command.ListCommand;
import jikan.command.StartCommand;
import jikan.command.ViewGoalsCommand;

import jikan.cleaner.LogCleaner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static jikan.log.Log.makeInfoLog;


/**
 * Represents the object which parses user input to relevant functions for the execution of commands.
 */
public class Parser {

    private static final String ABORT = "abort";
    private static final String BYE = "bye";
    private static final String CLEAN = "clean";
    private static final String CONTINUE = "continue";
    private static final String DELETE = "delete";
    private static final String EDIT = "edit";
    private static final String END = "end";
    private static final String FILTER = "filter";
    private static final String FIND = "find";
    private static final String GOAL = "goal";
    private static final String GRAPH = "graph";
    private static final String LIST = "list";
    private static final String START = "start";


    public static LocalDateTime startTime = null;
    public static LocalDateTime endTime = null;
    public static String activityName = null;
    public static Duration allocatedTime = Duration.parse("PT0S");
    public static Set<String> tags = new HashSet<>();
    public StorageCleaner cleaner;
    public LogCleaner logcleaner;
    public Storage tagStorage;
    public static String[] tokenizedInputs;
    String instruction;
    private static Log logger = new Log();

    // flag to check if the current activity is a continued one
    public static int continuedIndex = -1;

    /**
     * Parses user commands to relevant functions to carry out the commands.
     *
     * @param scanner      scanner object which reads user input
     */
    public Command parseUserCommands(Scanner scanner) throws NullPointerException,
            ArrayIndexOutOfBoundsException {
        makeInfoLog("Starting to parse inputs.");

        String userInput = scanner.nextLine();
        tokenizedInputs = userInput.split(" ", 2);
        instruction = tokenizedInputs[0];
        Command command = null;

        switch (instruction) {
        case BYE:
            if (tokenizedInputs.length > 1 && !tokenizedInputs[1].isBlank()) {
                Ui.printDivider("Extra parameters detected.");
                break;
            }
            command = new ByeCommand(null);
            break;
        case START:
            try {
                command = new StartCommand(tokenizedInputs[1], scanner);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                makeInfoLog("Activity started without activity name");
                Ui.printDivider("Activity name cannot be empty.");
            }
            break;
        case END:
            if (tokenizedInputs.length > 1 && !tokenizedInputs[1].isBlank()) {
                Ui.printDivider("Extra parameters detected.");
                break;
            }
            command = new EndCommand(null);
            break;
        case ABORT:
            if (tokenizedInputs.length > 1 && !tokenizedInputs[1].isBlank()) {
                Ui.printDivider("Extra parameters detected.");
                break;
            }
            command = new AbortCommand(null);
            break;
        case LIST:
            if (tokenizedInputs.length == 1) {
                command = new ListCommand(null);
            } else {
                command = new ListCommand(tokenizedInputs[1]);
            }
            break;
        case DELETE:
            try {
                command = new DeleteCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("Activity name cannot be empty.");
            }
            break;
        case FIND:
            try {
                command = new FindCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("No keyword was given.");
            } catch (MultipleDelimitersException e) {
                Ui.printDivider("Please only use one ';' between each command.");
            }
            break;
        case FILTER:
            try {
                command = new FilterCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("No keyword was given.");
            } catch (MultipleDelimitersException e) {
                Ui.printDivider("Please only use one ';' between each command.");
            }
            break;
        case EDIT:
            try {
                command = new EditCommand(tokenizedInputs[1]);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("Activity name cannot be empty.");
                makeInfoLog("Edit command failed as there was no existing activity name provided.");
            }
            break;
        case CLEAN:
            try {
                command = new CleanCommand(tokenizedInputs[1], this.cleaner, this.logcleaner);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("No keyword was given.");
            }
            break;
        case CONTINUE:
            try {
                command = new ContinueCommand(tokenizedInputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("Activity name cannot be empty.");
                makeInfoLog("Continue command failed as there was no activity name provided.");
            }
            break;
        case GRAPH:
            try {
                command = new GraphCommand(tokenizedInputs[1]);
            } catch (NumberFormatException e) {
                Ui.printDivider("Please input an integer for the time interval.");
            } catch (ExtraParametersException e) {
                Ui.printDivider("Extra parameters or invalid format detected.");
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printDivider("Please specify whether you want to graph activities / tags / allocations.");
            }
            break;
        case GOAL:
            try {
                if (tokenizedInputs.length == 1 || tokenizedInputs[1].isBlank() || tokenizedInputs[1] == null) {
                    command = new ViewGoalsCommand(null, this.tagStorage);
                } else {
                    command = new GoalCommand(tokenizedInputs[1], scanner, this.tagStorage);
                }
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printDivider("Tag name cannot be empty.");
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
        String line = "OOPS!!! I'm sorry, but I don't know what that means :-(";
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
        allocatedTime = Duration.parse("PT0S");
    }
}