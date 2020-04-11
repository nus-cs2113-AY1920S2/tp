package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidCommandException;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.commands.AddFoodCommand;
import seedu.dietmanager.logic.commands.BuildNewRecipeCommand;
import seedu.dietmanager.logic.commands.CalculateCaloriesCommand;
import seedu.dietmanager.logic.commands.CheckBmiCommand;
import seedu.dietmanager.logic.commands.CheckRecordCommand;
import seedu.dietmanager.logic.commands.CheckRequiredCaloriesCommand;
import seedu.dietmanager.logic.commands.CheckWeightRecordCommand;
import seedu.dietmanager.logic.commands.ClearFoodRecordCommand;
import seedu.dietmanager.logic.commands.Command;
import seedu.dietmanager.logic.commands.DeleteFoodCommand;
import seedu.dietmanager.logic.commands.DeleteWeightCommand;
import seedu.dietmanager.logic.commands.ExitCommand;
import seedu.dietmanager.logic.commands.HelpCommand;
import seedu.dietmanager.logic.commands.ListFoodDatabaseCommand;
import seedu.dietmanager.logic.commands.ProfileCommand;
import seedu.dietmanager.logic.commands.RecordMealCommand;
import seedu.dietmanager.logic.commands.SetAgeCommand;
import seedu.dietmanager.logic.commands.SetGenderCommand;
import seedu.dietmanager.logic.commands.SetHeightCommand;
import seedu.dietmanager.logic.commands.SetNameCommand;
import seedu.dietmanager.logic.commands.SetProfileCommand;
import seedu.dietmanager.logic.commands.SetWeightCommand;
import seedu.dietmanager.logic.commands.SetWeightGoalCommand;
import seedu.dietmanager.logic.commands.ShowRecipeCommand;

import java.util.Optional;

/**
 * CommandParser is the public class responsible for validating the user input and
 * parsing it into a valid Command.
 */

public class CommandParser {

    /**
     * The command prompt parsed from user input.
     */

    private static Optional<String> commandPrompt;

    /**
     * The description parsed from user input.
     */

    private static Optional<String> description;

    /**
     * The number of arguments in valid Command.
     */

    private static final int ARGUMENTS_REQUIRED = 2;

    /**
     * Parses the user input and prepares it to be analysed.
     *
     * @param input the user input.
     * @throws InvalidCommandException if user input has too few arguments.
     */

    private static void prepareInput(String input) throws InvalidCommandException {
        commandPrompt = Optional.empty();
        description = Optional.empty();

        String[] inputArray = input.trim().split(" ", ARGUMENTS_REQUIRED);
        int numArguments = inputArray.length;
        switch (numArguments) {
        case 1:
            commandPrompt = Optional.ofNullable(inputArray[0].trim().toLowerCase());
            break;
        case 2:
            commandPrompt = Optional.ofNullable(inputArray[0].trim().toLowerCase());
            description = Optional.ofNullable(inputArray[1].trim());
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Validate the user input and parsing it into a valid Command.
     *
     * @param input User input.
     * @return Command generated from the user input.
     * @throws InvalidCommandException if command is not supported by application.
     * @throws InvalidFormatException  if command format is invalid.
     */

    public static Optional<Command> parseInput(String input) throws InvalidCommandException, InvalidFormatException {
        prepareInput(input);
        Optional<Command> command;

        if (commandPrompt.isPresent() && description.isEmpty()) {
            switch (commandPrompt.get()) {
            case "profile":
                command = Optional.of(new ProfileCommand(commandPrompt.get()));
                break;
            case "check-weight-progress":
                command = Optional.of(new CheckWeightRecordCommand(commandPrompt.get()));
                break;
            case "check-bmi":
                command = Optional.of(new CheckBmiCommand(commandPrompt.get()));
                break;
            case "list-food":
                command = Optional.of(new ListFoodDatabaseCommand(commandPrompt.get()));
                break;
            case "show-recipe":
                command = Optional.of(new ShowRecipeCommand(commandPrompt.get()));
                break;
            case "clear-records":
                command = Optional.of(new ClearFoodRecordCommand(commandPrompt.get()));
                break;
            case "help":
                command = Optional.of(new HelpCommand(commandPrompt.get()));
                break;
            case "exit":
                command = Optional.of(new ExitCommand(commandPrompt.get()));
                break;
            default:
                throw new InvalidCommandException();
            }
        } else if (commandPrompt.isPresent() && description.isPresent()) {
            switch (commandPrompt.get()) {
            case "set-profile":
                command = Optional.of(new SetProfileCommand(commandPrompt.get(), description.get()));
                break;
            case "set-name":
                command = Optional.of(new SetNameCommand(commandPrompt.get(), description.get()));
                break;
            case "set-age":
                command = Optional.of(new SetAgeCommand(commandPrompt.get(), description.get()));
                break;
            case "set-gender":
                command = Optional.of(new SetGenderCommand(commandPrompt.get(), description.get()));
                break;
            case "set-height":
                command = Optional.of(new SetHeightCommand(commandPrompt.get(), description.get()));
                break;
            case "set-weight":
                command = Optional.of(new SetWeightCommand(commandPrompt.get(), description.get()));
                break;
            case "set-weight-goal":
                command = Optional.of(new SetWeightGoalCommand(commandPrompt.get(), description.get()));
                break;
            case "record-meal":
                command = Optional.of(new RecordMealCommand(commandPrompt.get(), description.get()));
                break;
            case "check-meal":
                command = Optional.of(new CheckRecordCommand(commandPrompt.get(), description.get()));
                break;
            case "check-required-cal":
                command = Optional.of(new CheckRequiredCaloriesCommand(commandPrompt.get(), description.get()));
                break;
            case "calculate":
                command = Optional.of(new CalculateCaloriesCommand(commandPrompt.get(), description.get()));
                break;
            case "delete-weight":
                command = Optional.of(new DeleteWeightCommand(commandPrompt.get(), description.get()));
                break;
            case "addf":
                command = Optional.of(new AddFoodCommand(commandPrompt.get(), description.get()));
                break;
            case "delf":
                command = Optional.of(new DeleteFoodCommand(commandPrompt.get(), description.get()));
                break;
            case "new-recipe":
                command = Optional.of(new BuildNewRecipeCommand(commandPrompt.get(), description.get()));
                break;
            default:
                throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }

}