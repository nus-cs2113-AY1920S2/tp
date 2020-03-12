package seedu.nuke.parser;

import seedu.nuke.command.Command;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.InvalidCommand;
import seedu.nuke.command.ListCommand;
import seedu.nuke.command.module.AddModuleCommand;
import seedu.nuke.command.module.DeleteModuleCommand;
import seedu.nuke.command.taskCategory.AddCategoryCommand;
import seedu.nuke.command.taskCategory.DeleteCategoryCommand;
import seedu.nuke.exception.InvalidFormatException;

import java.util.Arrays;

import static seedu.nuke.util.ExceptionMessage.*;

/**
 * <h3>Parser</h3>
 * The <b>Parser</b> interprets the user input that is read by the <b>UI</b>.
 * The <b>Parser</b> then converts the input into a <b>Command</b> to be executed by the <b>Nuke</b> program.
 */
public class Parser {
    private static final int MAX_INPUT_LENGTH = 100; // Maximum length of user input accepted
//    private static final String DEADLINE_PREFIX = "/by";
//    private static final String EVENT_PREFIX = "/at";

    /**
     * Parses the input string read by the <b>UI</b> and converts the string into a specific <b>Command</b>, which is
     * to be executed by the <b>Nuke</b> program.
     * <p></p>
     * <b>Note</b>: The user input has to start with a certain keyword (i.e. <i>command word</i>), otherwise an
     * <i>Invalid Command Exception</i> will be thrown.
     *
     * @param input The user input read by the <b>UI.java</b>
     * @return The <b>corresponding</b> command to be executed
     * @throws EmptyInputException If user input is empty
     * @throws InputLengthExceededException If the length of the user input > {@value MAX_INPUT_LENGTH}
     * @throws InvalidCommandException If the user input cannot be recognised as any of the  <b>Command</b>
     * @see seedu.nuke.ui.Ui
     * @see Command
     */
    public Command parseInput(String input) {
//        if (input.isEmpty()) {
//            throw new EmptyInputException();
//        }
//        if (input.length() > MAX_INPUT_LENGTH) {
//            throw new InputLengthExceededException();
//        }

        // Splits user input into command word and rest of parameters (if any)
        String[] separatedInput = input.split("\\s+", 2);

        String commandWord = separatedInput[0].toLowerCase();
        String parameters = (separatedInput.length == 2) ? separatedInput[1].trim() : "";

        switch (commandWord) {

            case AddModuleCommand.COMMAND_WORD:
                return createAddModuleCommand(parameters);

            case DeleteModuleCommand.COMMAND_WORD:
                return createDeleteModuleCommand(parameters);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case AddCategoryCommand.COMMAND_WORD:
                return createAddCategoryCommand(parameters);

            case DeleteCategoryCommand.COMMAND_WORD:
                return createDeleteCategoryCommand(parameters);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new InvalidCommand("");
        }
    }

    private Command createAddCategoryCommand(String parameters) {
        try {
            String[] categoryDetails = extractCategoryDetails(parameters);
            String moduleCode = categoryDetails[1];
            String categoryName = categoryDetails[0];
            Integer priority = Integer.parseInt(categoryDetails[2]);
            return new AddCategoryCommand(moduleCode, categoryName, priority);
        } catch (MissingParameterException | StringIndexOutOfBoundsException e) {
            return new InvalidCommand(MESSAGE_MISSING_PARAMETERS);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

    private Command createDeleteCategoryCommand(String parameters) {
        try {
            String[] categoryDetails = extractCategoryDetails(parameters);
            String moduleCode = categoryDetails[1];
            String categoryName = categoryDetails[0];
            //Integer priority = Integer.parseInt(categoryDetails[2]);
            return new DeleteCategoryCommand(moduleCode, categoryName);
        } catch (MissingParameterException | StringIndexOutOfBoundsException e) {
            return new InvalidCommand(MESSAGE_MISSING_PARAMETERS);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

    private Command createAddModuleCommand(String parameters) {
        try {
            String moduleCode = extractModuleCode(parameters);
            return new AddModuleCommand(moduleCode);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MESSAGE_MISSING_MODULE_CODE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(MESSAGE_EXCESS_PARAMETERS);
        }
    }

    private Command createDeleteModuleCommand(String parameters) {
        try {
            String moduleCode = extractModuleCode(parameters);
            return new DeleteModuleCommand(moduleCode);
        } catch (MissingParameterException e) {
            return new InvalidCommand(MESSAGE_MISSING_MODULE_CODE);
        } catch (ExcessParameterException e) {
            return new InvalidCommand(MESSAGE_EXCESS_PARAMETERS);
        }
    }


    /**
     * Checks if there is more than <b>one</b> parameter in the input
     *
     * @param parameters The parameter(s) provided in the input
     * @return <code>TRUE</code> if there is more than one parameter in the input, and <code>FALSE</code> otherwise
     */
    private boolean hasMultipleParameters(String parameters) {
        return parameters.contains(" ");
    }

    private String extractModuleCode(String parameters) throws MissingParameterException, ExcessParameterException {
        if (parameters.isEmpty()) {
            throw new MissingParameterException();
        }

        if (hasMultipleParameters(parameters)) {
            throw new ExcessParameterException();
        }

        return parameters;
    }

    private String[] extractCategoryDetails(String parameters) throws MissingParameterException {
        String[] categoryDetails = new String[3];
        int moduleCodePrefixIndex = parameters.indexOf("-m");
        int priorityPrefixIndex = parameters.indexOf("-p");

        categoryDetails[0] = parameters.substring(0, moduleCodePrefixIndex).trim();
        categoryDetails[1] = parameters.substring(moduleCodePrefixIndex+2, priorityPrefixIndex).trim();
        categoryDetails[2] = parameters.substring(priorityPrefixIndex+2).trim();

        if (hasEmptyField(categoryDetails)) {
            throw new MissingParameterException();
        }

        return categoryDetails;
    }

    private boolean hasEmptyField(String[] details) {
        return Arrays.asList(details).contains("");
    }

    /** Signals that the user has provided an empty input. */
    public static class EmptyInputException extends InvalidFormatException {}

    /** Signals that the user has provided an input that is longer than {@value MAX_INPUT_LENGTH} characters. */
    public static class InputLengthExceededException extends InvalidFormatException {}

    /** Signals that the user has provided an unrecognised command */
    public static class InvalidCommandException extends InvalidFormatException {}

    /** Signals that the user has not provided a list number. */
    public static class MissingListNumberException extends InvalidFormatException {}

    /** Signals that the user has provided surplus parameters. */
    public static class ExcessParameterException extends InvalidFormatException {}

    /** Signals that the user has not provided sufficient parameters. */
    public static class MissingParameterException extends InvalidFormatException {}
}
