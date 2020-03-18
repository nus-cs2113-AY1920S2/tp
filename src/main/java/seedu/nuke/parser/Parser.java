package seedu.nuke.parser;

import seedu.nuke.Executor;
import seedu.nuke.command.*;
import seedu.nuke.command.addcommand.AddCategoryCommand;
import seedu.nuke.command.addcommand.AddTagCommand;
import seedu.nuke.command.deletecommand.DeleteCategoryCommand;
import seedu.nuke.command.deletecommand.DeleteTaskCommand;
import seedu.nuke.command.listcommand.*;
import seedu.nuke.command.editcommand.EditDeadlineCommand;
import seedu.nuke.command.addcommand.AddModuleCommand;
import seedu.nuke.command.addcommand.AddTaskCommand;
import seedu.nuke.command.deletecommand.DeleteModuleCommand;
import seedu.nuke.command.promptCommand.ConfirmationStatus;
import seedu.nuke.command.promptCommand.DeleteConfirmationPrompt;
import seedu.nuke.command.promptCommand.ListNumberPrompt;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.util.DateTime;
import seedu.nuke.util.DateTimeFormat;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.Message.*;
import static seedu.nuke.util.Message.MESSAGE_MISSING_PARAMETERS;


public class Parser {
    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<parameters>.*)");
    public static final String WHITESPACES = "\\s+";
    public static final String PARAMETER_SPLITTER = " ";
    public static final int COMMAND_PARAMETER_MAXIMUM_LIMIT = 2;
    public static final int COMMAND_WORD_INDEX = 0;
    public static final int PARAMETER_WORD_INDEX = 1;

    public static final String MODULE_CODE_PREFIX = "-m";
    public static final String CATEGORY_NAME_PREFIX = "-c";
    public static final String TASK_DESCRIPTION_PREFIX = "-t";
    public static final String PRIORITY_PREFIX = "-p";
    public static final String DEADLINE_PREFIX = "-d";
    public static final String ALL_FLAG = "-a";
    public static final String EXACT_FLAG = "-e";

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
    public Command parseCommand(String input) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + HelpCommand.MESSAGE_USAGE);
        }
        String commandWord = matcher.group("commandWord").toLowerCase();
        String parameters = matcher.group("parameters").trim();

        switch (commandWord) {
            case AddModuleCommand.COMMAND_WORD:
                return prepareAddModuleCommand(parameters);

            case AddCategoryCommand.COMMAND_WORD:
                return prepareAddCategoryCommand(parameters);

            case AddTaskCommand.COMMAND_WORD:
                return prepareAddTaskCommand(parameters);

            // todo check if in module
            case AddTagCommand.COMMAND_WORD:
                return new AddTagCommand(parameters);

            case DeleteModuleCommand.COMMAND_WORD:
                return prepareDeleteModuleCommand(parameters);

            case DeleteCategoryCommand.COMMAND_WORD:
                return prepareDeleteCategoryCommand(parameters);

            case DeleteTaskCommand.COMMAND_WORD:
                return prepareDeleteTaskCommand(parameters);

    //        case DeleteCommand.COMMAND_WORD:
    //            return prepareDeleteCommand(parameters);

            case ListModuleCommand.COMMAND_WORD:
                return prepareListModuleCommand(parameters);

            case ListCategoryCommand.COMMAND_WORD:
                return prepareListCategoryCommand(parameters);

            case ListTaskCommand.COMMAND_WORD:
                return prepareListTaskCommand(parameters);

            case ListAllTasksDeadlineCommand.COMMAND_WORD:
                return new ListAllTasksDeadlineCommand();

    //        case ListCommand.COMMAND_WORD:
    //            return prepareListCommand(parameters);

            case EditDeadlineCommand.COMMAND_WORD:
                return prepareEditDeadlineCommand(parameters);

    //        case ChangeDirectoryCommand.COMMAND_WORD:
    //            return prepareChangeDirectoryCommand(parameters, moduleManager);

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + HelpCommand.MESSAGE_USAGE);
        }
    }

    private Command prepareEditDeadlineCommand(String parameters) {
        Task taskToEdit;
        DateTime deadline;
        String[] temp = parameters.split("-d");
        Module dir = (Module) Command.getCurrentDirectory();
        String moduleCode = dir.getModuleCode();
        taskToEdit = new Task(ModuleManager.getModuleWithCode(moduleCode), temp[0].trim(), moduleCode);
        try {
            deadline = DateTimeFormat.stringToDateTime(temp[1].trim());
            return new EditDeadlineCommand(taskToEdit, deadline);
        } catch (DateTimeFormat.InvalidDateException | DateTimeFormat.InvalidTimeException
                | DateTimeFormat.InvalidDateTimeException e) {
            return new IncorrectCommand("Invalid datetime format!\n");
        }

    }

//    private Command prepareDeleteCommand(String parameters) {
//        if (Command.getCurrentDirectory() instanceof Root) {
//            if (ModuleManager.contains(parameters)) {
//                return new DeleteModuleCommand(parameters);
//            } else {
//                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
//            }
//        } else if (Command.getCurrentDirectory() instanceof Module) {
//            if (((Module) Command.getCurrentDirectory()).getTaskManager().contains(parameters)) {
//                return new DeleteTaskCommand(parameters);
//            }
//        }
//        //should never reach
//        return null;
//    }

    private Command prepareChangeDirectoryCommand(String parameters, ModuleManager moduleManager) {
        if (parameters.equals("..")) {
            return new ChangeDirectoryCommand((ModuleManager.getRoot()));
        }
        if (moduleManager.getModuleWithCode(parameters) != null) {
            return new ChangeDirectoryCommand(moduleManager.getModuleWithCode(parameters));
        }
        return new IncorrectCommand(MESSAGE_MODULE_NOT_FOUND);
    }

    /**
     * Splits user input into command word and rest of parameters (if any).
     *
     * @param input the input from the user
     * @return array of String contains command and parameter
     */
    private String[] getCommandAndParameter(String input) {
        String[] separatedInput = input.split(WHITESPACES, COMMAND_PARAMETER_MAXIMUM_LIMIT);
        String commandWord = separatedInput[COMMAND_WORD_INDEX].toLowerCase();
        String parameters = (separatedInput.length == COMMAND_PARAMETER_MAXIMUM_LIMIT)
                ? separatedInput[PARAMETER_WORD_INDEX].trim() : "";
        return new String[]{commandWord, parameters};
    }

    /* Prepare Add Commands */

    /**
     * Prepares the command to add a module
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a module
     */
    private Command prepareAddModuleCommand(String parameters) {
        final Pattern[] addModuleFormat = AddModuleCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = addModuleFormat.length - 1;
        Matcher[] matchers = new Matcher[addModuleFormat.length];

        if (isMissingCompulsoryParameters(addModuleFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String moduleCode = matchers[0].group("identifier").trim();

        return new AddModuleCommand(moduleCode);
    }

    /**
     * Prepares the command to add a category
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a category
     */
    private Command prepareAddCategoryCommand(String parameters) {
        final Pattern[] addCategoryFormat = AddCategoryCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = addCategoryFormat.length - 1;
        Matcher[] matchers = new Matcher[addCategoryFormat.length];

        if (isMissingCompulsoryParameters(addCategoryFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String categoryName = matchers[0].group("identifier").trim();
        String moduleCode = matchers[1].group("moduleCode").trim();
        String priority = matchers[2].group("priority").trim();

        if (priority.isEmpty()) {
            return new AddCategoryCommand(moduleCode, categoryName);
        }

        try {
            int priorityToSet = Integer.parseInt(priority);
            if (!isPriorityWithinRange(priorityToSet)) {
                return new IncorrectCommand(MESSAGE_PRIORITY_NOT_IN_RANGE);
            }
            return new AddCategoryCommand(moduleCode, categoryName, priorityToSet);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

    /**
     * Prepares the command to add a task
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a task
     */
    private Command prepareAddTaskCommand(String parameters) {
        final Pattern[] addTaskFormat = AddTaskCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = addTaskFormat.length - 1;
        Matcher[] matchers = new Matcher[addTaskFormat.length];

        if (isMissingCompulsoryParameters(addTaskFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String taskDescription = matchers[0].group("identifier").trim();
        String moduleCode = matchers[1].group("moduleCode").trim();
        String categoryName = matchers[2].group("categoryName").trim();
        String deadline = matchers[3].group("deadline").trim();
        String priority = matchers[4].group("priority").trim();

        DateTime deadlineToSet;
        try {
             deadlineToSet = DateTimeFormat.stringToDateTime(deadline);
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            return new IncorrectCommand(MESSAGE_INVALID_DEADLINE_FORMAT);
        }

        if (priority.isEmpty()) {
            return new AddTaskCommand(moduleCode, categoryName, taskDescription, deadlineToSet);
        }

        try {
            int priorityToSet = Integer.parseInt(priority);
            if (!isPriorityWithinRange(priorityToSet)) {
                return new IncorrectCommand(MESSAGE_PRIORITY_NOT_IN_RANGE);
            }
            return new AddTaskCommand(moduleCode, categoryName, taskDescription, deadlineToSet, priorityToSet);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

//    private Command prepareAddTaskCommand(String parameters) {
//        //todo
//        //add a very simple task (for testing)
//        Module module = (Module) Command.getCurrentDirectory();
//        if (module != null) {
//            String moduleCode = module.getModuleCode();
//            return new AddTaskCommand(new Task(ModuleManager.getModuleWithCode(moduleCode), parameters, moduleCode));
//        } else {
//            return new IncorrectCommand(MESSAGE_GO_INTO_MODULE);
//        }
//    }

    /**
     * Prepares the command to delete module(s)
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to delete module(s)
     */
    private Command prepareDeleteModuleCommand(String parameters) {
        final Pattern[] deleteModuleFormat = DeleteModuleCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = deleteModuleFormat.length - 1;
        Matcher[] matchers = new Matcher[deleteModuleFormat.length];

        if (isMissingCompulsoryParameters(deleteModuleFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String moduleCode = matchers[0].group("identifier").trim();
        String exactFlag = matchers[1].group("exact").trim();
        boolean isExact = !exactFlag.isEmpty();

        return new DeleteModuleCommand(moduleCode, isExact);
    }


    /**
     * Prepares the command to delete category/categories
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to delete category/categories
     */
    private Command prepareDeleteCategoryCommand(String parameters) {
        final Pattern[] deleteCategoryFormat = DeleteCategoryCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = deleteCategoryFormat.length - 1;
        Matcher[] matchers = new Matcher[deleteCategoryFormat.length];

        if (isMissingCompulsoryParameters(deleteCategoryFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String categoryName = matchers[0].group("identifier").trim();
        String moduleCode = matchers[1].group("moduleCode").trim();
        String exactFlag = matchers[2].group("exact").trim();
        boolean isExact = !exactFlag.isEmpty();

        return new DeleteCategoryCommand(moduleCode, categoryName, isExact);
    }

    /**
     * Prepares the command to delete task(s)
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to delete task(s)
     */
    private Command prepareDeleteTaskCommand(String parameters) {
        final Pattern[] deleteTaskFormat = DeleteTaskCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = deleteTaskFormat.length - 1;
        Matcher[] matchers = new Matcher[deleteTaskFormat.length];

        if (isMissingCompulsoryParameters(deleteTaskFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String taskDescription = matchers[0].group("identifier").trim();
        String moduleCode = matchers[1].group("moduleCode").trim();
        String categoryName = matchers[1].group("categoryName").trim();
        String exactFlag = matchers[2].group("exact").trim();
        boolean isExact = !exactFlag.isEmpty();

        return new DeleteTaskCommand(moduleCode, categoryName, taskDescription, isExact);
    }

//    private Command prepareListCommand(String parameters) {
//        if (parameters.trim().matches(ALL_FLAG)) {
//            return new ListAllTasksDeadlineCommand();
//        } else if (Command.getCurrentDirectory() instanceof Root) {
//            return prepareListModuleCommand(parameters);
//        } else if (Command.getCurrentDirectory() instanceof Module) {
//            return new ListModuleTasksDeadlineCommand();
//        }
//        return new ListAllTasksDeadlineCommand();
//    }

    /**
     * Prepares the command to show filtered modules
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to show filtered modules
     */
    private Command prepareListModuleCommand(String parameters) {
        final Pattern[] listModuleFormat = ListModuleCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = listModuleFormat.length - 1;
        Matcher[] matchers = new Matcher[listModuleFormat.length];

        if (isMissingCompulsoryParameters(listModuleFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String moduleKeyword = matchers[0].group("identifier").trim();
        String exactFlag = matchers[1].group("exact").trim();
        // String allFlag = matchers[2].group("all").trim();
        boolean isExact = !exactFlag.isEmpty();

        return new ListModuleCommand(moduleKeyword, isExact);
    }

    /**
     * Prepares the command to show filtered categories
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to show filtered categories
     */
    private Command prepareListCategoryCommand(String parameters) {
        final Pattern[] listCategoryFormat = ListCategoryCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = listCategoryFormat.length - 1;
        Matcher[] matchers = new Matcher[listCategoryFormat.length];

        if (isMissingCompulsoryParameters(listCategoryFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String categoryKeyword = matchers[0].group("identifier").trim();
        String moduleKeyword = matchers[1].group("moduleCode").trim();
        String exactFlag = matchers[2].group("exact").trim();
        boolean isExact = !exactFlag.isEmpty();

        return new ListCategoryCommand(moduleKeyword, categoryKeyword, isExact);
    }

    /**
     * Prepares the command to show filtered tasks
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to show filtered tasks
     */
    private Command prepareListTaskCommand(String parameters) {
        final Pattern[] listTaskFormat = ListTaskCommand.REGEX_FORMATS;
        final int invalidParameterFormatsIndex = listTaskFormat.length - 1;
        Matcher[] matchers = new Matcher[listTaskFormat.length];

        if (isMissingCompulsoryParameters(listTaskFormat, matchers, parameters)) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }

        if (matchers[invalidParameterFormatsIndex].find()) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }

        String taskKeyword = matchers[0].group("identifier").trim();
        String moduleKeyword = matchers[1].group("moduleCode").trim();
        String categoryKeyword = matchers[1].group("categoryName").trim();
        String exactFlag = matchers[2].group("exact").trim();
        boolean isExact = !exactFlag.isEmpty();

        return new ListTaskCommand(moduleKeyword, categoryKeyword, taskKeyword, isExact);
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

    private boolean isMissingCompulsoryParameters(Pattern[] formats, Matcher[] matchers, String parameters) {
        // Match patterns
        for (int i = 0; i < formats.length; ++i) {
            matchers[i] = formats[i].matcher(parameters);
        }

        // Check if matches with each pattern except last pattern which checks for invalid parameters
        for (int i = 0; i < formats.length - 1; ++i) {
            if (!matchers[i].find()) {
                return true;
            }
        }
        return false;
    }

    private boolean isPriorityWithinRange(int priority) {
        return priority >= 0 && priority <= 100;
    }

    public Command parseInputAsConfirmation(String userInput) {
        switch (userInput) {
            case "yes":
            case "y":
                return new DeleteConfirmationPrompt(ConfirmationStatus.CONFIRM);

            case "no":
            case "n":
                return new DeleteConfirmationPrompt(ConfirmationStatus.ABORT);

            default:
                return new DeleteConfirmationPrompt(ConfirmationStatus.WAIT);
        }
    }

    public Command parseInputAsIndices(String input) {
        final Matcher matcher = ListNumberPrompt.INDICES_FORMAT.matcher(input.trim());

        if (!matcher.matches()) {
            Executor.terminatePrompt();
            return new IncorrectCommand(MESSAGE_INVALID_DELETE_INDICES);
        }

        String indicesString = matcher.group("indices");
        String[] separatedIndicesString = indicesString.split(WHITESPACES);

        // Convert String array to Integer ArrayList and removing duplicates
        ArrayList<Integer> indices = Stream.of(separatedIndicesString)
                .map(Integer::parseInt).distinct()
                .collect(Collectors.toCollection(ArrayList::new));

        // Decrement each index by 1 due to 0-based indexing
        for (int i = 0; i < indices.size(); i++) {
            indices.set(i, indices.get(i)-1);
        }

        return new ListNumberPrompt(indices);
    }
}
