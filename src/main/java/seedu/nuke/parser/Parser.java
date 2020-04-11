package seedu.nuke.parser;

import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.HelpCommand;
import seedu.nuke.command.IncorrectCommand;
import seedu.nuke.command.addcommand.AddCategoryCommand;
import seedu.nuke.command.addcommand.AddFileCommand;
import seedu.nuke.command.addcommand.AddModuleCommand;
import seedu.nuke.command.addcommand.AddTagCommand;
import seedu.nuke.command.addcommand.AddTaskCommand;
import seedu.nuke.command.editcommand.EditCategoryCommand;
import seedu.nuke.command.editcommand.EditFileCommand;
import seedu.nuke.command.editcommand.EditModuleCommand;
import seedu.nuke.command.editcommand.EditTaskCommand;
import seedu.nuke.command.editcommand.MarkAsDoneCommand;
import seedu.nuke.command.filtercommand.FilterCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteCategoryCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteFileCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteModuleCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteTagCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteTaskCommand;
import seedu.nuke.command.filtercommand.listcommand.DueCommand;
import seedu.nuke.command.filtercommand.listcommand.ListCategoryCommand;
import seedu.nuke.command.filtercommand.listcommand.ListFileCommand;
import seedu.nuke.command.filtercommand.listcommand.ListModuleCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTagCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskSortedCommand;
import seedu.nuke.command.misc.ChangeDirectoryCommand;
import seedu.nuke.command.misc.ClearCommand;
import seedu.nuke.command.misc.InfoCommand;
import seedu.nuke.command.misc.OpenFileCommand;
import seedu.nuke.command.misc.RedoCommand;
import seedu.nuke.command.misc.UndoCommand;
import seedu.nuke.command.promptcommand.ConfirmationStatus;
import seedu.nuke.command.promptcommand.DeleteConfirmationPrompt;
import seedu.nuke.command.promptcommand.ListNumberPrompt;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.exception.InvalidFormatException;
import seedu.nuke.util.DateTime;
import seedu.nuke.util.DateTimeFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_PREFIX_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INVALID_DATETIME_FORMAT;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INVALID_PRIORITY;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MISSING_DIRECTORY_NAME;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MISSING_PARAMETERS;
import static seedu.nuke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.nuke.util.Message.MESSAGE_DEADLINE_OR_PRIORITY;
import static seedu.nuke.util.Message.MESSAGE_EMPTY_INPUT;
import static seedu.nuke.util.Message.MESSAGE_EXTRA_PARAMETERS;
import static seedu.nuke.util.Message.MESSAGE_INCORRECT_DIRECTORY_LEVEL_GENERIC;
import static seedu.nuke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nuke.util.Message.MESSAGE_INVALID_DELETE_INDICES;
import static seedu.nuke.util.Message.MESSAGE_MISSING_CATEGORY_NAME;
import static seedu.nuke.util.Message.MESSAGE_MISSING_DIRECTORY_TO_DELETE;
import static seedu.nuke.util.Message.MESSAGE_MISSING_MODULE_CODE;
import static seedu.nuke.util.Message.MESSAGE_MISSING_TAG_NAME;
import static seedu.nuke.util.Message.MESSAGE_MISSING_TASK_DESCRIPTION;
import static seedu.nuke.util.Message.MESSAGE_NO_DIRECTORY_TO_DELETE;
import static seedu.nuke.util.Message.MESSAGE_NO_EDIT_CATEGORY;
import static seedu.nuke.util.Message.MESSAGE_NO_EDIT_FILE;
import static seedu.nuke.util.Message.MESSAGE_NO_EDIT_MODULE;
import static seedu.nuke.util.Message.MESSAGE_NO_EDIT_TASK;
import static seedu.nuke.util.Message.MESSAGE_NO_PREFIX_ALLOWED;
import static seedu.nuke.util.Message.MESSAGE_UNKNOWN_COMMAND_WORD;


public class Parser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<parameters>.*)");
    private static final String WHITESPACES = "\\s+";
    private static final String NONE = "";

    private static final String GENERIC_LIST_COMMAND = "ls";
    private static final String GENERIC_ADD_COMMAND = "mkdir";
    private static final String GENERIC_DELETE_COMMAND = "rm";

    public static final String MODULE_PREFIX = "-m";
    public static final String CATEGORY_PREFIX = "-c";
    public static final String TASK_PREFIX = "-t";
    public static final String FILE_PREFIX = "-f";
    public static final String PRIORITY_PREFIX = "-p";
    public static final String DEADLINE_PREFIX = "-d";
    public static final String ALL_FLAG = "-a";
    public static final String EXACT_FLAG = "-e";

    private static final String COMMAND_WORD_GROUP = "commandWord";
    private static final String PARAMETERS_GROUP = "parameters";
    private static final String IDENTIFIER_GROUP = "identifier";
    private static final String MODULE_GROUP = "moduleCode";
    private static final String CATEGORY_GROUP = "categoryName";
    private static final String TASK_GROUP = "taskDescription";
    private static final String FILE_GROUP = "fileInfo";
    private static final String DEADLINE_GROUP = "deadline";
    private static final String PRIORITY_GROUP = "priority";
    private static final String PRIORITY_GROUP_SECOND = "prioritySecond";
    private static final String EXACT_GROUP = "exact";
    private static final String ALL_GROUP = "all";
    private static final String ALL_GROUP_SECOND = "allSecond";
    private static final String INVALID_GROUP = "invalid";

    /**
     * Parses the input string read by the <b>UI</b> and converts the string into a specific <b>Command</b>, which is
     * to be executed by the <b>Nuke</b> program.
     * <p></p>
     * <b>Note</b>: The user input has to start with a certain keyword (i.e. <i>command word</i>), otherwise an
     * <i>Invalid Command Exception</i> will be thrown.
     *
     * @param input The user input read by the <b>UI</b>
     * @return The <b>corresponding</b> command to be executed
     * @see seedu.nuke.ui.Ui
     * @see Command
     */
    public Command parseCommand(String input) {
        if (input.isBlank()) {
            return new IncorrectCommand(MESSAGE_EMPTY_INPUT);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + HelpCommand.MESSAGE_USAGE);
        }
        String commandWord = matcher.group(COMMAND_WORD_GROUP).toLowerCase().trim();
        String parameters = matcher.group(PARAMETERS_GROUP);

        try {
            switch (commandWord) {

            case GENERIC_ADD_COMMAND:
                return prepareGenericAddCommand(parameters);
            case GENERIC_LIST_COMMAND:
                return prepareGenericListCommand(parameters);
            case GENERIC_DELETE_COMMAND:
                return prepareGenericDeleteCommand(parameters);

            case AddModuleCommand.COMMAND_WORD:
                return prepareAddModuleCommand(parameters);
            case AddCategoryCommand.COMMAND_WORD:
                return prepareAddCategoryCommand(parameters);
            case AddTaskCommand.COMMAND_WORD:
                return prepareAddTaskCommand(parameters);
            case AddFileCommand.COMMAND_WORD:
                return prepareAddFileCommand(parameters);
            case AddTagCommand.COMMAND_WORD:
                return prepareAddTagCommand(parameters);

            case DeleteModuleCommand.COMMAND_WORD:
                return prepareDeleteAndListModuleCommand(parameters, true);
            case DeleteCategoryCommand.COMMAND_WORD:
                return prepareDeleteAndListCategoryCommand(parameters, true);
            case DeleteTaskCommand.COMMAND_WORD:
                return prepareDeleteAndListTaskCommand(parameters, true);
            case DeleteFileCommand.COMMAND_WORD:
                return prepareDeleteAndListFileCommand(parameters, true);
            case DeleteTagCommand.COMMAND_WORD:
                return prepareDeleteAndListTagCommand(parameters, true);

            case ListModuleCommand.COMMAND_WORD:
                return prepareDeleteAndListModuleCommand(parameters, false);
            case ListCategoryCommand.COMMAND_WORD:
                return prepareDeleteAndListCategoryCommand(parameters, false);
            case ListTaskCommand.COMMAND_WORD:
                return prepareDeleteAndListTaskCommand(parameters, false);
            case ListFileCommand.COMMAND_WORD:
                return prepareDeleteAndListFileCommand(parameters, false);
            case ListTaskSortedCommand.COMMAND_WORD:
                return prepareListTaskSortedCommand(parameters);
            case ListTagCommand.COMMAND_WORD:
                return prepareDeleteAndListTagCommand(parameters, false);
            case DueCommand.COMMAND_WORD:
                return prepareDueCommand(parameters);

            case EditModuleCommand.COMMAND_WORD:
                return prepareEditModuleCommand(parameters);
            case EditCategoryCommand.COMMAND_WORD:
                return prepareEditCategoryCommand(parameters);
            case EditTaskCommand.COMMAND_WORD:
                return prepareEditTaskCommand(parameters);
            case EditFileCommand.COMMAND_WORD:
                return prepareEditFileCommand(parameters);
            case MarkAsDoneCommand.COMMAND_WORD:
                return prepareMarkAsDoneCommand(parameters);

            case ChangeDirectoryCommand.COMMAND_WORD:
                return prepareChangeDirectoryCommand(parameters);

            case OpenFileCommand.COMMAND_WORD:
                return prepareOpenFileCommand(parameters);

            case InfoCommand.COMMAND_WORD:
                return prepareCommandWithoutParameters(new InfoCommand(), parameters);

            case UndoCommand.COMMAND_WORD:
                return prepareCommandWithoutParameters(new UndoCommand(), parameters);

            case RedoCommand.COMMAND_WORD:
                return prepareCommandWithoutParameters(new RedoCommand(), parameters);

            case HelpCommand.COMMAND_WORD:
                return prepareCommandWithoutParameters(new HelpCommand(), parameters);

            case ClearCommand.COMMAND_WORD:
                return prepareClearCommand(parameters);

            case ExitCommand.COMMAND_WORD:
                return prepareCommandWithoutParameters(new ExitCommand(), parameters);

            default:
                return new IncorrectCommand(MESSAGE_UNKNOWN_COMMAND_WORD);
            }
        } catch (InvalidParameterException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        } catch (DuplicatePrefixException e) {
            return new IncorrectCommand(String.format("%s%s\n", MESSAGE_DUPLICATE_PREFIX_FOUND, e.getMessage()));
        }
    }

    /**
     * Prepares the command that has no parameters.
     *
     * @param command
     *  The command to be executed later if it passes the check
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command
     */
    private Command prepareCommandWithoutParameters(Command command, String parameters) {
        if (!parameters.isEmpty()) {
            return new IncorrectCommand(MESSAGE_EXTRA_PARAMETERS);
        } else {
            return command;
        }
    }

    /* Prepare Generic Commands */

    /**
     * Prepare the command to add the content based on the directory of the user is currently in.
     * @param parameters The parameters given by the user
     * @return The command to change the current directory
     * @throws InvalidParameterException exception is thrown when parameter is invalid.
     * @throws DuplicatePrefixException exception is thrown when duplicated prefix is provided.
     */
    private Command prepareGenericAddCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        if (containsPrefix(parameters)) {
            return new IncorrectCommand(MESSAGE_NO_PREFIX_ALLOWED);
        }
        switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
        case ROOT:
            return prepareAddModuleCommand(parameters);
        case MODULE:
            return prepareAddCategoryCommand(parameters);
        case CATEGORY:
            return prepareAddTaskCommand(parameters);
        case TASK:
            return prepareAddFileCommand(parameters);
        default:
            return new IncorrectCommand(MESSAGE_INCORRECT_DIRECTORY_LEVEL_GENERIC);
        }
    }

    /**
     * Prepare the command to list the content based on the directory of the user is currently in.
     * @param parameters The parameters given by the user
     * @return The command to change the current directory
     * @throws InvalidParameterException exception is thrown when parameter is invalid.
     * @throws DuplicatePrefixException exception is thrown when duplicated prefix is provided.
     */
    private Command prepareGenericListCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        if (containsPrefix(parameters)) {
            return new IncorrectCommand(MESSAGE_NO_PREFIX_ALLOWED);
        }
        switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
        case ROOT:
            if (parameters.isEmpty()) {
                return prepareDeleteAndListModuleCommand(parameters, false);
            } else {
                String listCategoriesString = String.format(" %s %s", MODULE_PREFIX, parameters);
                return prepareDeleteAndListCategoryCommand(listCategoriesString, false);
            }
        case MODULE:
            if (parameters.isEmpty()) {
                return prepareDeleteAndListCategoryCommand(parameters, false);
            } else {
                String listTasksString = String.format(" %s %s", CATEGORY_PREFIX, parameters);
                return prepareDeleteAndListTaskCommand(listTasksString, false);
            }
        case CATEGORY:
            if (parameters.isEmpty()) {
                return prepareDeleteAndListTaskCommand(parameters, false);
            } else {
                String listFilesString = String.format(" %s %s", TASK_PREFIX, parameters);
                return prepareDeleteAndListFileCommand(listFilesString, false);
            }
        case TASK:
            return prepareDeleteAndListFileCommand(parameters, false);
        default:
            return new IncorrectCommand(MESSAGE_INCORRECT_DIRECTORY_LEVEL_GENERIC);
        }
    }

    /**
     * Prepare the command to delete the content based on the directory of the user is currently in.
     * @param parameters The parameters given by the user
     * @return The command to change the current directory
     * @throws InvalidParameterException exception is thrown when parameter is invalid.
     * @throws DuplicatePrefixException exception is thrown when duplicated prefix is provided.
     */
    private Command prepareGenericDeleteCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        if (containsPrefix(parameters)) {
            return new IncorrectCommand(MESSAGE_NO_PREFIX_ALLOWED);
        }
        if (parameters.isEmpty()) {
            return new IncorrectCommand(MESSAGE_MISSING_DIRECTORY_TO_DELETE);
        }

        final String deleteString = String.format(" %s -e", parameters);

        switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
        case ROOT:
            return prepareDeleteAndListModuleCommand(deleteString, true);
        case MODULE:
            return prepareDeleteAndListCategoryCommand(deleteString, true);
        case CATEGORY:
            return prepareDeleteAndListTaskCommand(deleteString, true);
        case TASK:
            return prepareDeleteAndListFileCommand(deleteString, true);
        default:
            return new IncorrectCommand(MESSAGE_NO_DIRECTORY_TO_DELETE);
        }
    }

    /* Prepare Add Commands */

    /**
     * Prepares the command to add a module.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a module
     */
    private Command prepareAddModuleCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = AddModuleCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher);

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, AddModuleCommand.FORMAT));
        }

        String moduleCode = matcher.group(IDENTIFIER_GROUP).trim();
        if (moduleCode.isEmpty()) {
            return new IncorrectCommand(MESSAGE_MISSING_MODULE_CODE);
        }

        return new AddModuleCommand(moduleCode);
    }

    /**
     * Prepares the command to add a category.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a category
     */
    private Command prepareAddCategoryCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = AddCategoryCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, PRIORITY_PREFIX);

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, AddCategoryCommand.FORMAT));
        }

        String categoryName = matcher.group(IDENTIFIER_GROUP).trim();
        if (categoryName.isEmpty()) {
            return new IncorrectCommand(MESSAGE_MISSING_CATEGORY_NAME);
        }

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String priority = matcher.group(PRIORITY_GROUP).replace(PRIORITY_PREFIX, NONE).trim();

        if (priority.isEmpty()) {
            return new AddCategoryCommand(moduleCode, categoryName);
        }

        try {
            return new AddCategoryCommand(moduleCode, categoryName, parsePriority(priority));
        } catch (NumberFormatException | InvalidPriorityException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

    /**
     * Prepares the command to add a task.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a task
     */
    private Command prepareAddTaskCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = AddTaskCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX,
                DEADLINE_PREFIX, PRIORITY_PREFIX);

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, AddTaskCommand.FORMAT));
        }

        String taskDescription = matcher.group(IDENTIFIER_GROUP).trim();
        if (taskDescription.isEmpty()) {
            return new IncorrectCommand(MESSAGE_MISSING_TASK_DESCRIPTION);
        }

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String deadline = matcher.group(DEADLINE_GROUP).replace(DEADLINE_PREFIX, NONE).trim();
        String priority = getOptionalAttribute(matcher, PRIORITY_GROUP, PRIORITY_GROUP_SECOND)
                .replace(PRIORITY_PREFIX, NONE).trim();

        DateTime deadlineToSet;
        try {
            deadlineToSet = DateTimeFormat.stringToDateTime(deadline);
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            return new IncorrectCommand(MESSAGE_INVALID_DATETIME_FORMAT);
        }

        if (priority.isEmpty()) {
            return new AddTaskCommand(moduleCode, categoryName, taskDescription, deadlineToSet);
        }

        try {
            return new AddTaskCommand(moduleCode, categoryName, taskDescription, deadlineToSet,
                    parsePriority(priority));
        } catch (NumberFormatException | InvalidPriorityException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

    /**
     * Prepares the command to add a file.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a file
     */
    private Command prepareAddFileCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = AddFileCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, TASK_PREFIX, FILE_PREFIX);

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, AddFileCommand.FORMAT));
        }

        String fileName = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String taskDescription = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();
        String filePath = matcher.group(FILE_GROUP).replace(FILE_PREFIX, NONE).trim();


        return new AddFileCommand(moduleCode, categoryName, taskDescription, fileName, filePath);
    }

    private Command prepareAddTagCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = AddTagCommand.REGEX_FORMATS.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, TASK_PREFIX);

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, AddTagCommand.FORMAT));
        }

        String tagName = matcher.group(IDENTIFIER_GROUP).trim();
        if (tagName.isEmpty()) {
            return new IncorrectCommand(MESSAGE_MISSING_TAG_NAME);
        }

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String taskDescription = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();


        return new AddTagCommand(tagName, moduleCode, categoryName, taskDescription);
    }

    /* Prepare Delete and List Commands */

    /**
     * Prepares the command to delete modules or show filtered modules.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to delete modules or show filtered modules
     */
    private Command prepareDeleteAndListModuleCommand(String parameters, boolean isDelete)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = FilterCommand.MODULE_REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, EXACT_FLAG, ALL_FLAG);

        String moduleKeyword = matcher.group(IDENTIFIER_GROUP).trim();
        String exactFlag = matcher.group(EXACT_GROUP).trim();
        boolean isExact = !exactFlag.isEmpty();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return isDelete
                    ? new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, DeleteModuleCommand.FORMAT)) :
                    new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, ListModuleCommand.FORMAT));
        }

        if (isDelete) {
            return new DeleteModuleCommand(moduleKeyword, isExact);
        } else {
            return new ListModuleCommand(moduleKeyword, isExact);
        }
    }

    /**
     * Prepares the command to delete categories or show filtered categories.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to delete categories or show filtered categories
     */
    private Command prepareDeleteAndListCategoryCommand(String parameters, boolean isDelete)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = FilterCommand.CATEGORY_REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, EXACT_FLAG, ALL_FLAG);

        String categoryKeyword = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleKeyword = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String exactFlag = matcher.group(EXACT_GROUP).trim();
        String allFlag = getOptionalAttribute(matcher, ALL_GROUP, ALL_GROUP_SECOND);
        boolean isAll = !allFlag.isEmpty();
        boolean isExact = !exactFlag.isEmpty();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return isDelete
                    ? new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, DeleteCategoryCommand.FORMAT)) :
                    new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, ListCategoryCommand.FORMAT));
        }

        if (isDelete) {
            return new DeleteCategoryCommand(moduleKeyword, categoryKeyword, isExact, isAll);
        } else {
            return new ListCategoryCommand(moduleKeyword, categoryKeyword, isExact, isAll);
        }
    }

    /**
     * Prepares the command to delete tasks or show filtered tasks.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to delete tasks or show filtered tasks
     */
    private Command prepareDeleteAndListTaskCommand(String parameters, boolean isDelete)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = FilterCommand.TASK_REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, EXACT_FLAG, ALL_FLAG);

        String taskKeyword = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleKeyword = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryKeyword = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String exactFlag = matcher.group(EXACT_GROUP).trim();
        String allFlag = getOptionalAttribute(matcher, ALL_GROUP, ALL_GROUP_SECOND);
        boolean isAll = !allFlag.isEmpty();
        boolean isExact = !exactFlag.isEmpty();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return isDelete
                    ? new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, DeleteTaskCommand.FORMAT)) :
                    new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, ListTaskCommand.FORMAT));
        }

        if (isDelete) {
            return new DeleteTaskCommand(moduleKeyword, categoryKeyword, taskKeyword, isExact, isAll);
        } else {
            return new ListTaskCommand(moduleKeyword, categoryKeyword, taskKeyword, isExact, isAll);
        }
    }

    /**
     * Prepares the command to delete files or show filtered files.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to delete files or show filtered files
     */
    private Command prepareDeleteAndListFileCommand(String parameters, boolean isDelete)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = FilterCommand.FILE_REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, TASK_PREFIX, EXACT_FLAG, ALL_FLAG);

        String fileKeyword = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleKeyword = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryKeyword = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String taskKeyword = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();
        String exactFlag = matcher.group(EXACT_GROUP).trim();
        String allFlag = getOptionalAttribute(matcher, ALL_GROUP, ALL_GROUP_SECOND);
        boolean isAll = !allFlag.isEmpty();
        boolean isExact = !exactFlag.isEmpty();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return isDelete
                    ? new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, DeleteFileCommand.FORMAT)) :
                    new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, ListFileCommand.FORMAT));
        }

        if (isDelete) {
            return new DeleteFileCommand(moduleKeyword, categoryKeyword, taskKeyword, fileKeyword, isExact, isAll);
        } else {
            return new ListFileCommand(moduleKeyword, categoryKeyword, taskKeyword, fileKeyword, isExact, isAll);
        }
    }

    private Command prepareDeleteAndListTagCommand(String parameters, boolean isDelete)
            throws DuplicatePrefixException, InvalidParameterException {
        Matcher matcher = FilterCommand.TAG_REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, TASK_PREFIX, EXACT_FLAG, ALL_FLAG);

        String tagKeyword = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleKeyword = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryKeyword = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String taskKeyword = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();
        String exactFlag = matcher.group(EXACT_GROUP).trim();
        String allFlag = getOptionalAttribute(matcher, ALL_GROUP, ALL_GROUP_SECOND);
        boolean isAll = !allFlag.isEmpty();
        boolean isExact = !exactFlag.isEmpty();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return isDelete
                    ? new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                    MESSAGE_CHECK_COMMAND_FORMAT, DeleteTagCommand.FORMAT)) :
                    new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, invalid,
                            MESSAGE_CHECK_COMMAND_FORMAT, ListTagCommand.FORMAT));
        }

        if (isDelete) {
            return new DeleteTagCommand(moduleKeyword, categoryKeyword, taskKeyword, tagKeyword, isExact, isAll);
        } else {
            return new ListTagCommand(moduleKeyword, categoryKeyword, taskKeyword, tagKeyword, isExact, isAll);
        }
    }

    /**
     * Prepares the command to show a list of undone tasks sorted by deadline or priority.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to show a list of undone tasks sorted by deadline or priority
     */
    private Command prepareListTaskSortedCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = ListTaskSortedCommand.TASK_SORTED_REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, DEADLINE_PREFIX, PRIORITY_PREFIX);

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String category = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String deadlineFlag = matcher.group(DEADLINE_GROUP).trim();
        String priorityFlag = getOptionalAttribute(matcher, PRIORITY_GROUP, PRIORITY_GROUP_SECOND);

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT,
                    invalid, MESSAGE_CHECK_COMMAND_FORMAT, ListTaskSortedCommand.FORMAT));
        }

        // Contains both deadline and priority prefixes
        if (!deadlineFlag.isEmpty() && !priorityFlag.isEmpty()) {
            return new IncorrectCommand(MESSAGE_DEADLINE_OR_PRIORITY);
        }

        boolean isByPriority = !priorityFlag.isEmpty();

        return new ListTaskSortedCommand(moduleCode, category, isByPriority);
    }

    /**
     * Prepares the command to show filtered tasks by a time period.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to show tasks due on a certain time period
     */
    private Command prepareDueCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        if (parameters.isBlank()) {
            return new IncorrectCommand(MESSAGE_MISSING_PARAMETERS);
        }
        Matcher matcher = DueCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, ALL_FLAG);

        String dateFilter = matcher.group(IDENTIFIER_GROUP).trim().toLowerCase();
        String allFlag = matcher.group(ALL_GROUP).trim();
        boolean isAll = !allFlag.isEmpty();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, DueCommand.FORMAT));
        }

        if (dateFilter.equals("over")) {
            return new DueCommand(dateFilter, isAll);
        }

        String[] dateFilterData = dateFilter.trim().split("\\s+");
        try {
            if (dateFilterData[0].equals("over")) {
                return new IncorrectCommand(MESSAGE_EXTRA_PARAMETERS);
            }
            if (dateFilterData.length == 1) {
                return new DueCommand(DateTimeFormat.stringToDate(dateFilterData[0]), null, isAll);
            } else if (dateFilterData.length == 2) {
                return new DueCommand(DateTimeFormat.stringToDate(dateFilterData[1]), dateFilterData[0], isAll);
            } else {
                return new IncorrectCommand(MESSAGE_EXTRA_PARAMETERS);
            }
        } catch (DateTimeFormat.InvalidDateException e) {
            return new IncorrectCommand(MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /* Prepare Edit Commands */

    /**
     * Prepares the command to edit a module.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to edit a module
     */
    private Command prepareEditModuleCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = EditModuleCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX);

        String oldModuleCode = matcher.group(IDENTIFIER_GROUP).trim();
        String newModuleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, EditModuleCommand.FORMAT));
        }

        if (isNothingToEdit(newModuleCode)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_MODULE);
        }

        return new EditModuleCommand(oldModuleCode, newModuleCode);
    }

    /**
     * Prepares the command to edit a category.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to edit a category
     */
    private Command prepareEditCategoryCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = EditCategoryCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, PRIORITY_PREFIX);

        String oldCategoryName = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String newCategoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String newPriority = matcher.group(PRIORITY_GROUP).replace(PRIORITY_PREFIX, NONE).trim();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, EditCategoryCommand.FORMAT));
        }

        if (isNothingToEdit(newCategoryName, newPriority)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_CATEGORY);
        }

        if (newPriority.isEmpty()) {
            return new EditCategoryCommand(oldCategoryName, moduleCode, newCategoryName);
        }

        try {
            return new EditCategoryCommand(oldCategoryName, moduleCode, newCategoryName, parsePriority(newPriority));
        } catch (NumberFormatException | InvalidPriorityException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

    /**
     * Prepares the command to edit a task.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to edit a task
     */
    private Command prepareEditTaskCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = EditTaskCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, TASK_PREFIX,
                DEADLINE_PREFIX, PRIORITY_PREFIX);

        String oldTaskDescription = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String newTaskDescription = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();
        String newDeadline = matcher.group(DEADLINE_GROUP).replace(DEADLINE_PREFIX, NONE).trim();
        String newPriority = getOptionalAttribute(matcher, PRIORITY_GROUP, PRIORITY_GROUP_SECOND)
                .replace(PRIORITY_PREFIX, NONE).trim();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, EditTaskCommand.FORMAT));
        }

        if (isNothingToEdit(newTaskDescription, newDeadline, newPriority)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_TASK);
        }

        DateTime newDeadlineToSet;
        try {
            newDeadlineToSet = DateTimeFormat.stringToDateTime(newDeadline);
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            return new IncorrectCommand(MESSAGE_INVALID_DATETIME_FORMAT);
        }

        if (newPriority.isEmpty()) {
            return new EditTaskCommand(oldTaskDescription, moduleCode, categoryName, newTaskDescription,
                    newDeadlineToSet);
        }

        try {
            return new EditTaskCommand(oldTaskDescription, moduleCode, categoryName, newTaskDescription,
                    newDeadlineToSet, parsePriority(newPriority));
        } catch (NumberFormatException | InvalidPriorityException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PRIORITY);
        }
    }

    /**
     * Prepares the command to edit a file.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to edit a file
     */
    private Command prepareEditFileCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = EditFileCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, TASK_PREFIX, FILE_PREFIX);

        String oldFileName = matcher.group(IDENTIFIER_GROUP).trim();

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String taskDescription = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();
        String newFileName = matcher.group(FILE_GROUP).replace(FILE_PREFIX, NONE).trim();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, EditFileCommand.FORMAT));
        }

        if (isNothingToEdit(newFileName)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_FILE);
        }

        return new EditFileCommand(oldFileName, moduleCode, categoryName, taskDescription, newFileName);
    }

    private Command prepareMarkAsDoneCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = MarkAsDoneCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX);

        String taskDescription = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, MarkAsDoneCommand.FORMAT));
        }

        return new MarkAsDoneCommand(moduleCode, categoryName, taskDescription);
    }

    /* Miscellaneous Commands */

    /**
     * Prepares the command to change the current directory.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to change the current directory
     */
    private Command prepareChangeDirectoryCommand(String parameters) {
        if (parameters.isBlank()) {
            return new IncorrectCommand(MESSAGE_MISSING_DIRECTORY_NAME);
        } else if (parameters.trim().equals("..")) {
            return new ChangeDirectoryCommand();
        } else {
            return new ChangeDirectoryCommand(parameters.trim());
        }
    }

    /**
     * Prepares the command to open file(s).
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to open file(s)
     */
    private Command prepareOpenFileCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = OpenFileCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX, CATEGORY_PREFIX, TASK_PREFIX);

        String fileName = matcher.group(IDENTIFIER_GROUP).trim();
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String taskDescription = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, OpenFileCommand.FORMAT));
        }

        return new OpenFileCommand(moduleCode, categoryName, taskDescription, fileName);
    }

    /**
     * Prepares the command to clear the GUI Console Screen.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to clear the GUI Console Screen
     */
    private Command prepareClearCommand(String parameters) {
        // This is exclusive for Gui only
        if (!Executor.isGui()) {
            return new IncorrectCommand(MESSAGE_UNKNOWN_COMMAND_WORD);
        }
        if (!parameters.isEmpty()) {
            return new IncorrectCommand(MESSAGE_EXTRA_PARAMETERS);
        } else {
            return new ClearCommand();
        }
    }

    /**
     * Checks if the priority is between 0 and 100 inclusive.
     *
     * @param priority
     *  The priority given by the user
     * @return
     *  <code>TRUE</code> if the priority is within range, or <code>FALSE</code> otherwise
     */
    private boolean isPriorityWithinRange(int priority) {
        return priority >= 0 && priority <= 20;
    }

    /**
     * Parses a string as a priority.
     *
     * @param priorityString
     *  The string representing the priority
     * @return
     *  The numeric priority of the string
     * @throws InvalidPriorityException
     *  If the priority is invalid as it is not within the expected range
     */
    private int parsePriority(String priorityString) throws InvalidPriorityException {
        int priorityToSet = Integer.parseInt(priorityString);
        if (!isPriorityWithinRange(priorityToSet)) {
            throw new InvalidPriorityException();
        }
        return priorityToSet;
    }

    /**
     * Validate the parameters given by the user for the respective command.
     *
     * @param parameters
     *  The parameters given by the user
     * @param matcher
     *  The matcher to match for attributes and check for validity
     * @param parameterPrefixes
     *  The prefixes used for the respective command
     * @throws InvalidParameterException
     *  If an invalid parameter is found in the parameters or the parameters do not match the expected format
     * @throws DuplicatePrefixException
     *  If a duplicate prefix is found in the parameters
     */
    private void validateParameters(String parameters, Matcher matcher, String... parameterPrefixes)
            throws InvalidParameterException, DuplicatePrefixException {
        ArrayList<String> duplicatedPrefixes = new ArrayList<>();
        for (String prefix : parameterPrefixes) {
            if (countPrefixOccurrences(parameters, prefix) > 1) {
                duplicatedPrefixes.add(prefix);
            }
        }

        if (!duplicatedPrefixes.isEmpty()) {
            String allDuplicatedPrefixes = "Duplicated prefix: " + String.join(", ", duplicatedPrefixes);
            throw new DuplicatePrefixException(allDuplicatedPrefixes);
        }

        if (!matcher.matches()) {
            throw new InvalidParameterException();
        }
    }

    /**
     * A simple method to count the number of prefix occurrences within a string.
     *
     * @param fullString
     *  The main string to count the number of substring
     * @param prefix
     *  The prefix to find in the main string
     */
    private int countPrefixOccurrences(String fullString, String prefix) {
        // Smaller than prefix length
        if (fullString.length() < prefix.length() + 1) {
            return 0;
        }

        int count = 0;
        int index = 0;
        // Count number of prefixes in the centre of string
        String prefixWithSpaces = String.format(" %s ", prefix);
        while ((index = fullString.indexOf(prefixWithSpaces, index)) >= 0) {
            ++count;
            ++index;
        }
        // Check if there is a prefix at the back of the string
        String prefixWithFrontSpace = String.format(" %s", prefix);
        int indexOfLastPrefix = fullString.length() - prefixWithFrontSpace.length();
        if (fullString.substring(indexOfLastPrefix).equals(prefixWithFrontSpace)) {
            ++count;
        }

        return count;
    }

    /**
     * Checks if the parameters contains any prefixes.
     *
     * @param parameters
     *  The parameters to check
     * @return
     *  <code>TRUE</code> if a prefix is found, and <code>FALSE</code> otherwise
     */
    private boolean containsPrefix(String parameters) {
        Pattern prefixRegex = Pattern.compile(".*?\\s+-.*");
        return prefixRegex.matcher(parameters).matches();
    }

    /**
     * Returns the correctly parsed optional attribute from a pair of duplicate attribute groups.
     *
     * @param matcher
     *  The matcher to find the optional attribute
     * @param firstGroup
     *  The name of the first group
     * @param secondGroup
     *  The name of the second group
     * @return
     *  The correct optional attribute
     */
    private String getOptionalAttribute(Matcher matcher, String firstGroup, String secondGroup) {

        String first = matcher.group(firstGroup).trim();
        String second = matcher.group(secondGroup).trim();

        return !first.isEmpty() ? first : second;
    }

    /**
     * Checks if there is anything to edit from the input given by the user.
     *
     * @param attributes
     *  The attributes to be edited
     * @return
     *  <code>TRUE</code> if there is nothing to edit, or <code>FALSE</code> otherwise
     */
    private boolean isNothingToEdit(String... attributes) {
        for (String attribute : attributes) {
            if (!attribute.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Parses the input as a confirmation status to a confirmation prompt for the user, and prepares the
     * corresponding command.
     *
     * @param userInput
     *  The input given by the user
     * @return
     *  The command to be executed
     */
    public Command parseConfirmation(String userInput) {
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

    /**
     * Parses the input as indices to prepare a command to utilise the parsed indices.
     *
     * @param input
     *  The input string given by the user
     * @return
     *  The command to be executed
     */
    public Command parseIndices(String input) {
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
        IntStream.range(0, indices.size())
                .forEach(currentValue -> indices.set(currentValue, indices.get(currentValue) - 1));

        return new ListNumberPrompt(indices);
    }

    public static class InvalidParameterException extends InvalidFormatException {
    }

    public static class DuplicatePrefixException extends InvalidFormatException {
        private String message;

        public DuplicatePrefixException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    public static class InvalidPriorityException extends InvalidFormatException {
    }
}