package seedu.nuke.parser;

import seedu.nuke.command.Command;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.InvalidCommand;
import seedu.nuke.command.ListCommand;
import seedu.nuke.command.module.AddModuleCommand;
import seedu.nuke.command.module.DeleteModuleCommand;
import seedu.nuke.command.category.AddCategoryCommand;
import seedu.nuke.command.category.DeleteCategoryCommand;
import seedu.nuke.command.task.AddTaskCommand;
import seedu.nuke.command.task.DeleteTaskCommand;
import seedu.nuke.exception.InvalidFormatException;
import seedu.nuke.format.DateTime;
import seedu.nuke.format.DateTimeFormat;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * <h3>Parser</h3>
 * The <b>Parser</b> interprets the user input that is read by the <b>UI</b>.
 * The <b>Parser</b> then converts the input into a <b>Command</b> to be executed by the <b>Nuke</b> program.
 */
public class Parser {
    private final int MAX_INPUT_LENGTH = 100; // Maximum length of user input accepted
    private final String MODULE_CODE_PREFIX = "-m";
    private final String CATEGORY_NAME_PREFIX = "-c";
    private final String TASK_DESCRIPTION_PREFIX = "-t";
    private final String PRIORITY_PREFIX = "-p";
    private final String DEADLINE_PREFIX = "-d";

    private final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<parameters>.*)");
    private final Pattern COMMAND_PARAMETERS_FORMAT = Pattern.compile(
            "(?<identifier>[^-]+)"
            + "(?<moduleCode>(?: " + MODULE_CODE_PREFIX + " [^-]+)?)"
            + "(?<categoryName>(?: " + CATEGORY_NAME_PREFIX + " [^-]+)?)"
            + "(?<taskDescription>(?: " + TASK_DESCRIPTION_PREFIX + " [^-]+)?)"
            + "(?<deadline>(?: " + DEADLINE_PREFIX + " [^-]+)?)"
            + "(?<priority>(?: " + PRIORITY_PREFIX + " [^-]+)?)"
    );

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

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());

        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String commandWord = matcher.group("commandWord").toLowerCase();
        String parameters = matcher.group("parameters").trim();

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

            case AddTaskCommand.COMMAND_WORD:
                return createAddTaskCommand(parameters);

            case DeleteTaskCommand.COMMAND_WORD:
                return createDeleteTaskCommand(parameters);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private Command createAddModuleCommand(String parameters) {
        final Matcher matcher = COMMAND_PARAMETERS_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String moduleCode = matcher.group("identifier").trim();

        // This is a compulsory field
        if (moduleCode.isEmpty()) {
            return new InvalidCommand(MESSAGE_MISSING_PARAMETERS);
        }

        return new AddModuleCommand(moduleCode);
    }

    private Command createDeleteModuleCommand(String parameters) {
        final Matcher matcher = COMMAND_PARAMETERS_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String moduleCode = matcher.group("identifier").trim();

        // This is a compulsory field
        if (moduleCode.isEmpty()) {
            return new InvalidCommand(MESSAGE_MISSING_PARAMETERS);
        }

        return new DeleteModuleCommand(moduleCode);
    }

    private Command createAddCategoryCommand(String parameters) {
        final Matcher matcher = COMMAND_PARAMETERS_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String categoryName = matcher.group("identifier").trim();
        String moduleCode = matcher.group("moduleCode")
                .replace(MODULE_CODE_PREFIX, "").trim();
        String priority = matcher.group("priority")
                .replace(PRIORITY_PREFIX, "").trim();

        // These are compulsory fields
        if (moduleCode.isEmpty() || categoryName.isEmpty()) {
            return new InvalidCommand(MESSAGE_MISSING_PARAMETERS);
        }

        try {
            Integer categoryPriority = (priority.isEmpty()) ? null : Integer.parseInt(priority);
            return new AddCategoryCommand(moduleCode, categoryName, categoryPriority);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

    private Command createDeleteCategoryCommand(String parameters) {
        final Matcher matcher = COMMAND_PARAMETERS_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String categoryName = matcher.group("identifier").trim();
        String moduleCode = matcher.group("moduleCode")
                .replace(MODULE_CODE_PREFIX, "").trim();

        // These are compulsory fields
        if (moduleCode.isEmpty() || categoryName.isEmpty()) {
            return new InvalidCommand(MESSAGE_MISSING_PARAMETERS);
        }

        return new DeleteCategoryCommand(moduleCode, categoryName);
    }

    private Command createAddTaskCommand(String parameters) {
        final Matcher matcher = COMMAND_PARAMETERS_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String taskDescription = matcher.group("identifier").trim();
        String moduleCode = matcher.group("moduleCode")
                .replace(MODULE_CODE_PREFIX, "").trim();
        String categoryName = matcher.group("categoryName")
                .replace(CATEGORY_NAME_PREFIX, "").trim();
        String deadline = matcher.group("deadline")
                .replace(DEADLINE_PREFIX, "").trim();
        String priority = matcher.group("priority")
                .replace(PRIORITY_PREFIX, "").trim();

        // These are compulsory fields
        if (moduleCode.isEmpty() || categoryName.isEmpty() || taskDescription.isEmpty()) {
            return new InvalidCommand(MESSAGE_MISSING_PARAMETERS);
        }

        try {
            DateTime taskDeadline = (deadline.isEmpty()) ? null : DateTimeFormat.stringToDateTime(deadline);
            Integer taskPriority = (priority.isEmpty()) ? null : Integer.parseInt(priority);
            return new AddTaskCommand(moduleCode, categoryName, taskDescription, taskDeadline, taskPriority);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MESSAGE_INVALID_PRIORITY);
        } catch (DateTimeFormat.InvalidDateTimeException | DateTimeFormat.InvalidDateException | DateTimeFormat.InvalidTimeException e) {
            return new InvalidCommand(MESSAGE_INVALID_DEADLINE_FORMAT);
        }
    }

    private Command createDeleteTaskCommand(String parameters) {
        final Matcher matcher = COMMAND_PARAMETERS_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String taskDescription = matcher.group("identifier").trim();
        String moduleCode = matcher.group("moduleCode")
                .replace(MODULE_CODE_PREFIX, "").trim();
        String categoryName = matcher.group("categoryName")
                .replace(CATEGORY_NAME_PREFIX, "").trim();

        // These are compulsory fields
        if (moduleCode.isEmpty() || categoryName.isEmpty() || taskDescription.isEmpty()) {
            return new InvalidCommand(MESSAGE_MISSING_PARAMETERS);
        }

        return new DeleteTaskCommand(moduleCode, categoryName, taskDescription);
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
