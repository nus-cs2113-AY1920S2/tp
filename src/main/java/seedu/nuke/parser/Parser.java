package seedu.nuke.parser;

import seedu.nuke.command.*;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.exception.ModuleNotFoundException;
import seedu.nuke.exception.InvalidIndexException;
import seedu.nuke.format.DateTime;
import seedu.nuke.format.DateTimeFormat;
import seedu.nuke.task.Task;

import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.nuke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class Parser {
    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final String COMMAND_PARAMETER_SPLITTER = "\\s+";
    public static final String PARAMETER_SPLITTER = " ";
    public static final int COMMAND_PARAMETER_MAXIMUM_LIMIT = 2;
    public static final int COMMAND_WORD_INDEX = 0;
    public static final int PARAMETER_WORD_INDEX = 1;
    private static final int MAX_INPUT_LENGTH = 100; // Maximum length of user input accepted


    /**
     * Parses the input string read by the <b>UI</b> and converts the string into a specific <b>Command</b>, which is
     * to be executed by the <b>Nuke</b> program.
     * <p></p>
     * <b>Note</b>: The user input has to start with a certain keyword (i.e. <i>command word</i>), otherwise an
     * <i>Invalid Command Exception</i> will be thrown.
     *
     * @param input The user input read by the <b>UI.java</b>
     * @return The <b>corresponding</b> command to be executed
     * @see seedu.nuke.ui.Ui
     * @see Command
     */
    public Command parseCommand(String input, ModuleManager moduleManager) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        String commandWord = getCommandAndParameter(input)[COMMAND_WORD_INDEX];
        String parameters = getCommandAndParameter(input)[PARAMETER_WORD_INDEX];

        switch (commandWord) {

        case EditDeadlineCommand.COMMAND_WORD:
            return prepareEditDeadlineCommand(parameters);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case ChangeModuleCommand.COMMAND_WORD:
            return prepareChangeModuleCommand(parameters, moduleManager);

        case AddModuleCommand.COMMAND_WORD:
            return prepareAddModuleCommand(parameters);

        case DeleteModuleCommand.COMMAND_WORD:
            return prepareDeleteModuleCommand(parameters);

        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case CheckAllTasksDeadlineCommand.COMMAND_WORD:
            return new CheckAllTasksDeadlineCommand();

        case CheckModuleTasksDeadlineCommand.COMMAND_WORD:
            return prepareCheckModuleTasksDeadlineCommand(parameters);

        case AddTaskCommand.COMMAND_WORD:
            return prepareAddTaskCommand(parameters);

        case DeleteTaskCommand.COMMAND_WORD:
            return prepareDeleteTaskCommand(parameters);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    private Command prepareEditDeadlineCommand(String parameters) {
        Task taskToEdit;
        DateTime deadline = null;
        String [] temp = parameters.split("-d");
        taskToEdit = new Task(temp[0], Command.getCurrentModule().getModuleCode());
        try {
            deadline = DateTimeFormat.stringToDateTime(temp[1]);
        } catch (DateTimeFormat.InvalidDateException e ){

        } catch (DateTimeFormat.InvalidDateTimeException e) {

        } catch (DateTimeFormat.InvalidTimeException e ) {

        }
        return new EditDeadlineCommand(taskToEdit, deadline);
    }

    private Command prepareCheckModuleTasksDeadlineCommand(String parameters) {
        try {
            return new CheckModuleTasksDeadlineCommand(Integer.parseInt(parameters));
        } catch (ModuleNotFoundException e) {
            return new IncorrectCommand("Module cannot be found");
        }
    }

    private Command prepareDeleteTaskCommand(String parameters) {
        //
        return null;
    }

    private Command prepareChangeModuleCommand(String parameters, ModuleManager moduleManager) {
        if (moduleManager.getModuleWithCode(parameters) != null) {
            return new ChangeModuleCommand(moduleManager.getModuleWithCode(parameters));
        }
        return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    private Command prepareAddTaskCommand(String parameters) {
        //todo
        //add a very simple task (for testing)
        if (Command.getCurrentModule() != null) {
            return new AddTaskCommand(new Task(parameters, Command.getCurrentModule().getModuleCode()));
        } else {
            return new IncorrectCommand("please go inside a Module!");
        }
    }

    /**
     * Splits user input into command word and rest of parameters (if any).
     *
     * @param input the input from the user
     * @return array of String contains command and parameter
     */
    private String[] getCommandAndParameter(String input) {
        String[] separatedInput = input.split(COMMAND_PARAMETER_SPLITTER, COMMAND_PARAMETER_MAXIMUM_LIMIT);
        String commandWord = separatedInput[COMMAND_WORD_INDEX].toLowerCase();
        String parameters = (separatedInput.length == COMMAND_PARAMETER_MAXIMUM_LIMIT)
                ? separatedInput[PARAMETER_WORD_INDEX].trim() : "";
        return new String[]{commandWord, parameters};
    }

    private Command prepareAddModuleCommand(String parameters) {
        String moduleCode = parameters;
        if (parameters.isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }
        if (hasMultipleParameters(parameters)) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }
        return new AddModuleCommand(moduleCode);
    }

    private Command prepareDeleteModuleCommand(String parameters) {
        String moduleCode = parameters;
        if (parameters.isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteModuleCommand.MESSAGE_USAGE));
        }
        if (hasMultipleParameters(parameters)) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteModuleCommand.MESSAGE_USAGE));
        }
        return new DeleteModuleCommand(moduleCode);
    }

    /**
     * Checks if there is more than <b>one</b> parameter in the input.
     *
     * @param parameters The parameter(s) provided in the input
     * @return <code>TRUE</code> if there is more than one parameter in the input, and <code>FALSE</code> otherwise
     */
    private boolean hasMultipleParameters(String parameters) {
        return parameters.contains(PARAMETER_SPLITTER);
    }
}
