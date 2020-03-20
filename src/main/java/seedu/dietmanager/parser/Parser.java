package seedu.dietmanager.parser;

import seedu.dietmanager.commands.*;
import seedu.dietmanager.commands.Command;
import seedu.dietmanager.commands.ExitCommand;
import seedu.dietmanager.commands.ProfileCommand;
import seedu.dietmanager.commands.SetAgeCommand;
import seedu.dietmanager.commands.SetGenderCommand;
import seedu.dietmanager.commands.SetHeightCommand;
import seedu.dietmanager.commands.SetNameCommand;
import seedu.dietmanager.commands.SetProfileCommand;
import seedu.dietmanager.commands.SetWeightCommand;
import seedu.dietmanager.commands.SetWeightGoalCommand;
import seedu.dietmanager.commands.RecordMealCommand;
import seedu.dietmanager.commands.CheckRecordCommand;
import seedu.dietmanager.exceptions.InvalidCommandException;
import seedu.dietmanager.exceptions.InvalidFormatException;

/**
 * Parser is the public class responsible for parsing user input and generating the relevant commands.
 */

public class Parser {

    /**
     * The command prompt entered by the user.
     */

    private static String commandPrompt;

    /**
     * The description of the command entered by the user.
     */

    private static String description;

    /**
     * Parses the user input and prepares it to be analysed and used to generate commands.
     * @param input the user input.
     * @throws InvalidCommandException if user input has too few arguments.
     */

    public static void prepareInput(String input) throws InvalidCommandException {
        String[] inputArray = input.trim().split(" ", 2);
        int numArguments = inputArray.length;
        switch (numArguments) {
        case 1:
            commandPrompt = inputArray[0].trim().toLowerCase();
            break;
        case 2:
            commandPrompt = inputArray[0].trim().toLowerCase();
            description = inputArray[1].trim();
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the user input and prepares it to be analysed and used to generate commands.
     * @param description the command description.
     * @param argumentsRequired the number of arguments required by the command.
     * @throws InvalidFormatException if user input has the wrong format.
     */

    public static String[] parseDescription(String description, int argumentsRequired) throws InvalidFormatException {
        String[] descriptionArray = description.trim().split(" ", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        return descriptionArray;
    }

    /**
     * Analyses the user input and generates the relevant command.
     * @param input the user input.
     * @return the command generated from the user input.
     * @throws InvalidCommandException  if command is not supported by application.
     * @throws InvalidFormatException   if format for command is wrong.
     */

    public static Command parseInput(String input) throws InvalidCommandException, InvalidFormatException {
        prepareInput(input);
        Command command;
        switch (commandPrompt) {
        case "profile":
            command = new ProfileCommand(commandPrompt);
            break;
        case "set-profile":
            command = new SetProfileCommand(commandPrompt, description);
            break;
        case"set-name":
            command = new SetNameCommand(commandPrompt, description);
            break;
        case"set-age":
            command = new SetAgeCommand(commandPrompt, description);
            break;
        case"set-gender":
            command = new SetGenderCommand(commandPrompt, description);
            break;
        case"set-height":
            command = new SetHeightCommand(commandPrompt, description);
            break;
        case"set-weight":
            command = new SetWeightCommand(commandPrompt, description);
            break;
        case"set-weight-goal":
            command = new SetWeightGoalCommand(commandPrompt, description);
            break;
        case "record-meal":
            command = new RecordMealCommand(commandPrompt, description);
            break;
        case "check-meal":
            command = new CheckRecordCommand(commandPrompt, description);
            break;
        case "exit":
            command = new ExitCommand(commandPrompt);
            break;
        case "update-weight":
            command = new WeightUpdateCommand(commandPrompt, description);
            break;
        case "check-weight-progress":
            command = new CheckWeightProgressCommand(commandPrompt, description);
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }

}