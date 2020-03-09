package seedu.nuke.parser;

import seedu.nuke.command.*;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.exception.InvalidFormatException;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_EXCESS_PARAMETERS;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MISSING_MODULE_CODE;

public class Parser {
    public static final String COMMAND_SPLITTER = " ";
    public static final int COMMAND_WORD_INDEX = 0;
    private static final int MAX_INPUT_LENGTH = 100; // Maximum length of user input accepted

    /**
     * Parses user input into command for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     * split the user input, command word and the description
     * commandWord stores the whole user input
     * commandWordFirstPart [0] stores the Command Word
     * description stores additional information
     */
    public Command parseCommand(ModuleManager moduleManager, String userInput) {
        final String commandWord = userInput;
        final String[] commandWordFirstPart = commandWord.split(COMMAND_SPLITTER);
        /* further split the user input, get the secondary part, the description */
        final String commandWordDescription = commandWord.substring(commandWordFirstPart[COMMAND_WORD_INDEX].length());
        //operates according to different command word
        return getCommand(commandWordFirstPart);
    }

    /**
     * @param commandWordFirstPart, commandWordDescription the input String spited parts
     * @return parsed command
     */
    private Command getCommand(String[] commandWordFirstPart) {
        switch (commandWordFirstPart[COMMAND_WORD_INDEX]){
        //exit
        case ExitCommand.COMMAND_WORD:
        default:
            return new ExitCommand();
        }
    }

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
    public Command parseInput(String input)
            throws EmptyInputException, InputLengthExceededException, InvalidCommandException {
        if (input.isEmpty()) {
            throw new EmptyInputException();
        }
        if (input.length() > MAX_INPUT_LENGTH) {
            throw new InputLengthExceededException();
        }

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

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                throw new InvalidCommandException();
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

    /** Signals that the user has provided an empty input. */
    public static class EmptyInputException extends InvalidFormatException {}

    /** Signals that the user has provided an input that is longer than {@value MAX_INPUT_LENGTH} characters. */
    public static class InputLengthExceededException extends InvalidFormatException {}

    /** Signals that the user has provided an unrecognised command */
    public static class InvalidCommandException extends InvalidFormatException {}

    /** Signals that the user has provided surplus parameters. */
    public static class ExcessParameterException extends InvalidFormatException {}

    /** Signals that the user has not provided sufficient parameters. */
    public static class MissingParameterException extends InvalidFormatException {}
}
