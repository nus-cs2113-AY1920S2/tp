package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidCommandException;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.commands.Command;
import seedu.dietmanager.logic.commands.foodrecord.CalculateCaloriesCommand;
import seedu.dietmanager.logic.commands.foodrecord.CheckRecordCommand;
import seedu.dietmanager.logic.commands.foodrecord.RecordMealCommand;
import seedu.dietmanager.logic.commands.nutritionrecord.AddFoodCommand;
import seedu.dietmanager.logic.commands.nutritionrecord.DeleteFoodCommand;
import seedu.dietmanager.logic.commands.nutritionrecord.ListFoodDatabaseCommand;
import seedu.dietmanager.logic.commands.profile.CheckRequiredCaloriesCommand;
import seedu.dietmanager.logic.commands.profile.CheckWeightRecordCommand;
import seedu.dietmanager.logic.commands.profile.DeleteWeightCommand;
import seedu.dietmanager.logic.commands.profile.ProfileCommand;
import seedu.dietmanager.logic.commands.profile.SetAgeCommand;
import seedu.dietmanager.logic.commands.profile.SetGenderCommand;
import seedu.dietmanager.logic.commands.profile.SetHeightCommand;
import seedu.dietmanager.logic.commands.profile.SetNameCommand;
import seedu.dietmanager.logic.commands.profile.SetProfileCommand;
import seedu.dietmanager.logic.commands.profile.SetWeightCommand;
import seedu.dietmanager.logic.commands.profile.SetWeightGoalCommand;
import seedu.dietmanager.logic.commands.recipe.BuildNewRecipeCommand;
import seedu.dietmanager.logic.commands.recipe.ShowRecipeCommand;
import seedu.dietmanager.logic.commands.utility.ExitCommand;
import seedu.dietmanager.logic.commands.utility.HelpCommand;

/**
 * Parser is the public class responsible for parsing user input and generating the relevant commands.
 */

public class CommandParser {

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
     *
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
     *
     * @param description       the command description.
     * @param argumentsRequired the number of arguments required by the command.
     * @throws InvalidFormatException if user input has the wrong format.
     */

    public static String[] parseDescription(String description, int argumentsRequired) throws InvalidFormatException,
            NullPointerException {
        String[] descriptionArray = description.trim().split("\\s+", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        return descriptionArray;
    }

    /**
     * Analyses the user input and generates the relevant command.
     *
     * @param input the user input.
     * @return the command generated from the user input.
     * @throws InvalidCommandException if command is not supported by application.
     * @throws InvalidFormatException  if format for command is wrong.
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
        case "set-name":
            command = new SetNameCommand(commandPrompt, description);
            break;
        case "set-age":
            command = new SetAgeCommand(commandPrompt, description);
            break;
        case "set-gender":
            command = new SetGenderCommand(commandPrompt, description);
            break;
        case "set-height":
            command = new SetHeightCommand(commandPrompt, description);
            break;
        case "set-weight":
            command = new SetWeightCommand(commandPrompt, description);
            break;
        case "set-weight-goal":
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
        case "check-weight-progress":
            command = new CheckWeightRecordCommand(commandPrompt, description);
            break;
        case "check-required-cal":
            command = new CheckRequiredCaloriesCommand(commandPrompt, description);
            break;
        case "list-food":
            command = new ListFoodDatabaseCommand(commandPrompt);
            break;
        case "calculate":
            command = new CalculateCaloriesCommand(commandPrompt, description);
            break;
        case "delete-weight":
            command = new DeleteWeightCommand(commandPrompt, description);
            break;
        case "addf":
            command = new AddFoodCommand(commandPrompt, description);
            break;
        case "delf":
            command = new DeleteFoodCommand(commandPrompt, description);
            break;
        case "new-recipe":
            command = new BuildNewRecipeCommand(commandPrompt, description);
            break;
        case "show-recipe":
            command = new ShowRecipeCommand(commandPrompt);
            break;
        case "help":
            command = new HelpCommand(commandPrompt);
            break;
        default:
            description = null;
            throw new InvalidCommandException();
        }
        description = null;
        return command;
    }

}