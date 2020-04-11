package seedu.nuke.gui.io;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import seedu.nuke.Executor;
import seedu.nuke.command.filtercommand.listcommand.DueCommand;
import seedu.nuke.command.filtercommand.listcommand.ListCategoryCommand;
import seedu.nuke.command.filtercommand.listcommand.ListFileCommand;
import seedu.nuke.command.filtercommand.listcommand.ListModuleCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskSortedCommand;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.HelpCommand;
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
import seedu.nuke.command.filtercommand.deletecommand.DeleteCategoryCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteFileCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteModuleCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteTaskCommand;
import seedu.nuke.command.misc.ChangeDirectoryCommand;
import seedu.nuke.command.misc.ClearCommand;
import seedu.nuke.command.misc.InfoCommand;
import seedu.nuke.command.misc.OpenFileCommand;
import seedu.nuke.command.misc.RedoCommand;
import seedu.nuke.command.misc.UndoCommand;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.directory.TaskFile;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.exception.ParseFailureException;
import seedu.nuke.gui.component.AutoCompleteTextField;
import seedu.nuke.gui.component.SyntaxConsole;
import seedu.nuke.util.DateTimeFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static seedu.nuke.directory.DirectoryTraverser.getBaseCategory;
import static seedu.nuke.directory.DirectoryTraverser.getBaseModule;
import static seedu.nuke.directory.DirectoryTraverser.getBaseTask;
import static seedu.nuke.directory.DirectoryTraverser.getCurrentDirectory;
import static seedu.nuke.directory.DirectoryTraverser.getCurrentDirectoryLevel;
import static seedu.nuke.gui.io.GuiCommandPattern.ADD_CATEGORY_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.ADD_FILE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.ADD_MODULE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.ADD_TASK_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.BASIC_COMMAND_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DELETE_AND_LIST_CATEGORY_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DELETE_AND_LIST_FILE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DELETE_AND_LIST_MODULE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DELETE_AND_LIST_TASK_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DONE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DUE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.EDIT_CATEGORY_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.EDIT_FILE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.EDIT_MODULE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.EDIT_TASK_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.LIST_TASK_SORTED_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.OPEN_FILE_FORMAT;
import static seedu.nuke.gui.util.TextUtil.createText;

public class GuiParser {
    private static final String NONE = "";

    private static final String GENERIC_ADD_COMMAND = "mkdir";
    private static final String GENERIC_DELETE_COMMAND = "rm";
    private static final String GENERIC_LIST_COMMAND = "ls";

    public static final String MODULE_PREFIX = "-m";
    public static final String CATEGORY_PREFIX = "-c";
    public static final String TASK_PREFIX = "-t";
    public static final String FILE_PREFIX = "-f";
    public static final String PRIORITY_PREFIX = "-p";
    public static final String DEADLINE_PREFIX = "-d";
    public static final String ALL_FLAG = "-a";
    public static final String EXACT_FLAG = "-e";
    private static final int PREFIX_LENGTH = 2;

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

    private AutoCompleteTextField console;
    private SyntaxConsole syntaxConsole;

    public GuiParser(AutoCompleteTextField console, SyntaxConsole syntaxConsole) {
        this.console = console;
        this.syntaxConsole = syntaxConsole;
    }

    private static final ArrayList<String> COMMAND_WORDS = new ArrayList<>(Arrays.asList(
            AddModuleCommand.COMMAND_WORD, AddCategoryCommand.COMMAND_WORD, AddTaskCommand.COMMAND_WORD,
            AddFileCommand.COMMAND_WORD,
            DeleteModuleCommand.COMMAND_WORD, DeleteCategoryCommand.COMMAND_WORD, DeleteTaskCommand.COMMAND_WORD,
            DeleteFileCommand.COMMAND_WORD,
            ListModuleCommand.COMMAND_WORD, ListCategoryCommand.COMMAND_WORD, ListTaskCommand.COMMAND_WORD,
            ListFileCommand.COMMAND_WORD, ListTaskSortedCommand.COMMAND_WORD, DueCommand.COMMAND_WORD,
            EditModuleCommand.COMMAND_WORD, EditCategoryCommand.COMMAND_WORD, EditTaskCommand.COMMAND_WORD,
            EditFileCommand.COMMAND_WORD, MarkAsDoneCommand.COMMAND_WORD,
            ChangeDirectoryCommand.COMMAND_WORD, OpenFileCommand.COMMAND_WORD, InfoCommand.COMMAND_WORD,
            UndoCommand.COMMAND_WORD, RedoCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            AddTagCommand.COMMAND_WORD, GENERIC_ADD_COMMAND, GENERIC_DELETE_COMMAND, GENERIC_LIST_COMMAND
    ));

    /**
     * Parses the input given by the user, checks the syntax and format, and highlights the input with various colors.
     *
     * @param input
     *  The input being typed by the user
     */
    public void smartParse(String input) {
        console.getEntriesPopup().hide();
        try {
            switch (Executor.getPromptType()) {
            case INDICES:
                smartParseIndices(input);
                break;

            case CONFIRMATION:
                smartParseConfirmation(input);
                break;

            default:
                Matcher matcher = smartParseCommandWord(input);
                smartParseParameters(matcher);
            }
        } catch (ParseFailureException e) {
            // Exit
        }
    }

    /**
     * Parses the command word in the input string from the console.
     *
     * @param input
     *  The input being typed by the user
     */
    private Matcher smartParseCommandWord(String input) throws ParseFailureException {
        final Matcher matcher = matchPattern(input, BASIC_COMMAND_FORMAT);

        String rawCommandWord = matcher.group(COMMAND_WORD_GROUP);
        String commandWord = rawCommandWord.trim().toLowerCase();
        String parameters = matcher.group(PARAMETERS_GROUP);

        int startIndexOfCommandWord = matcher.start(COMMAND_WORD_GROUP);
        int endIndexOfCommandWord = matcher.end(COMMAND_WORD_GROUP);

        populateSuggestions(commandWord, COMMAND_WORDS, startIndexOfCommandWord, endIndexOfCommandWord, NONE);

        if (isNotTypingAttribute(startIndexOfCommandWord, endIndexOfCommandWord)) {
            console.getEntriesPopup().hide();
        }

        highlightInput(commandWord, rawCommandWord, parameters, endIndexOfCommandWord, COMMAND_WORDS, true);

        return matcher;
    }

    private void smartParseParameters(Matcher matcher) throws ParseFailureException {
        String commandWord = matcher.group(COMMAND_WORD_GROUP).trim().toLowerCase();
        String parameters = matcher.group(PARAMETERS_GROUP);
        final int startIndexOfParameters = matcher.start(PARAMETERS_GROUP);

        switch (commandWord) {

        case GENERIC_ADD_COMMAND:
            smartParseGenericAddCommand(parameters);
            break;
        case GENERIC_DELETE_COMMAND:
        case GENERIC_LIST_COMMAND:
            smartParseGenericDeleteAndListCommand(parameters, startIndexOfParameters);
            break;

        case AddTagCommand.COMMAND_WORD:
            addText(new Pair<>(parameters, Color.BLUE));
            break;

        case AddModuleCommand.COMMAND_WORD:
            smartParseAddModuleCommand(parameters, startIndexOfParameters);
            break;
        case AddCategoryCommand.COMMAND_WORD:
            smartParseAddCategoryCommand(parameters, startIndexOfParameters);
            break;
        case AddTaskCommand.COMMAND_WORD:
            smartParseAddTaskCommand(parameters, startIndexOfParameters);
            break;
        case AddFileCommand.COMMAND_WORD:
            smartParseAddFileCommand(parameters, startIndexOfParameters);
            break;

        case DeleteModuleCommand.COMMAND_WORD:
        case ListModuleCommand.COMMAND_WORD:
            smartParseDeleteAndListModuleCommand(parameters, startIndexOfParameters);
            break;
        case DeleteCategoryCommand.COMMAND_WORD:
        case ListCategoryCommand.COMMAND_WORD:
            smartParseDeleteAndListCategoryCommand(parameters, startIndexOfParameters);
            break;
        case DeleteTaskCommand.COMMAND_WORD:
        case ListTaskCommand.COMMAND_WORD:
            smartParseDeleteAndListTaskCommand(parameters, startIndexOfParameters);
            break;
        case DeleteFileCommand.COMMAND_WORD:
        case ListFileCommand.COMMAND_WORD:
            smartParseDeleteAndListFileCommand(parameters, startIndexOfParameters);
            break;
        case ListTaskSortedCommand.COMMAND_WORD:
            smartParseListTaskSortedCommand(parameters, startIndexOfParameters);
            break;
        case DueCommand.COMMAND_WORD:
            smartParseDueCommand(parameters, startIndexOfParameters);
            break;

        case EditModuleCommand.COMMAND_WORD:
            smartParseEditModuleCommand(parameters, startIndexOfParameters);
            break;
        case EditCategoryCommand.COMMAND_WORD:
            smartParseEditCategoryCommand(parameters, startIndexOfParameters);
            break;
        case EditTaskCommand.COMMAND_WORD:
            smartParseEditTaskCommand(parameters, startIndexOfParameters);
            break;
        case EditFileCommand.COMMAND_WORD:
            smartParseEditFileCommand(parameters, startIndexOfParameters);
            break;
        case MarkAsDoneCommand.COMMAND_WORD:
            smartParseMarkAsDoneCommand(parameters, startIndexOfParameters);
            break;

        case ChangeDirectoryCommand.COMMAND_WORD:
            smartParseChangeDirectoryCommand(parameters, startIndexOfParameters);
            break;

        case OpenFileCommand.COMMAND_WORD:
            smartParseOpenFileCommand(parameters, startIndexOfParameters);
            break;

        case InfoCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
            smartParseEmptyParameterCommand(parameters);
            break;

        default:
            addText(new Pair<>(parameters, Color.CRIMSON));
            break;
        }
    }

    private void smartParseEmptyParameterCommand(String parameters) {
        addText(new Pair<>(parameters, Color.CRIMSON));
    }

    private void smartParseChangeDirectoryCommand(String parameters, int startIndex) {
        int endIndex = startIndex + parameters.length();
        String nextDirectory = parameters.trim();
        // Change to upper case for module code parameter
        if (DirectoryTraverser.getCurrentDirectoryLevel() == DirectoryLevel.ROOT) {
            nextDirectory = nextDirectory.toUpperCase();
        }

        if (nextDirectory.equals("..")) {
            addText(new Pair<>(parameters, Color.GREEN));
        } else {
            ArrayList<String> suggestedDirectories = generateSuggestedDirectories();
            populateSuggestions(nextDirectory, suggestedDirectories, startIndex, endIndex, NONE);

            if (isMatchingWord(nextDirectory, suggestedDirectories)) {
                addText(new Pair<>(parameters, Color.GREEN));
            } else if (isPartOfWord(nextDirectory, suggestedDirectories)) {
                addText(new Pair<>(parameters, Color.ORANGE));
            } else {
                addText(new Pair<>(parameters, Color.CRIMSON));
            }
        }
    }

    private void smartParseGenericAddCommand(String parameters) {
        addText(new Pair<>(parameters, Color.BLUE));
    }

    private void smartParseGenericDeleteAndListCommand(String parameters, int startIndex) throws ParseFailureException {
        final String noKeyword = "";
        int endIndex = parameters.length() + startIndex;

        ArrayList<String> suggestedDirectories = new ArrayList<>();
        try {
            switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
            case ROOT:
                suggestedDirectories = generateSuggestedModules();
                break;

            case MODULE:
                suggestedDirectories = generateSuggestedCategories(noKeyword, true);
                break;

            case CATEGORY:
                suggestedDirectories = generateSuggestedTasks(noKeyword, noKeyword, true);
                break;

            case TASK:
                suggestedDirectories = generateSuggestedFiles(noKeyword, noKeyword, noKeyword, true);
                break;

            default:
                break;
            }
        } catch (IncorrectDirectoryLevelException e) {
            // Ignore error; popup will have no entries
        }

        highlightInput(parameters.trim(), parameters, NONE, endIndex, suggestedDirectories, false);
        populateSuggestions(parameters.trim(), suggestedDirectories, startIndex, endIndex, NONE);
    }

    private void smartParseAddModuleCommand(String parameters, int startIndex)
            throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, ADD_MODULE_FORMAT);

        String moduleCode = matcher.group(IDENTIFIER_GROUP);
        int startIndexOfModule = matcher.start(IDENTIFIER_GROUP) + startIndex;
        int endIndexOfModule = matcher.end(IDENTIFIER_GROUP) + startIndex;

        ArrayList<String> suggestedModules = generateSuggestedNewModules();
        populateSuggestions(moduleCode.trim(), suggestedModules, startIndexOfModule, endIndexOfModule, NONE);

        if (console.getCaretPosition() > endIndexOfModule) {
            console.getEntriesPopup().hide();
        }

        if (!moduleCode.isEmpty()) {
            String parametersAfter = parameters.substring(matcher.end(IDENTIFIER_GROUP));
            highlightInput(moduleCode.trim().toUpperCase(), moduleCode, parametersAfter, endIndexOfModule,
                    suggestedModules, true);
        }

        checkInvalid(matcher);
    }

    void smartParseAddCategoryCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, ADD_CATEGORY_FORMAT);

        String categoryName = matcher.group(IDENTIFIER_GROUP);
        addText(new Pair<>(categoryName, Color.BLUE));

        smartParseModule(matcher, parameters, startIndex, true);
        smartParsePriority(matcher, parameters, PRIORITY_GROUP);

        checkInvalid(matcher);

    }

    void smartParseAddTaskCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, ADD_TASK_FORMAT);

        String taskDescription = matcher.group(IDENTIFIER_GROUP);

        addText(new Pair<>(taskDescription, Color.BLUE));

        smartParseModule(matcher, parameters, startIndex, true);
        smartParseCategory(matcher, parameters, startIndex, true);

        boolean hasPriority = smartParsePriority(matcher, parameters, PRIORITY_GROUP);
        smartParseDeadline(matcher, parameters, startIndex);
        if (hasPriority) {
            highlightIncorrect(matcher, PRIORITY_GROUP_SECOND);
        } else {
            smartParsePriority(matcher, parameters, PRIORITY_GROUP_SECOND);
        }

        checkInvalid(matcher);
    }

    void smartParseAddFileCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, ADD_FILE_FORMAT);

        String fileName = matcher.group(IDENTIFIER_GROUP);

        addText(new Pair<>(fileName, Color.BLUE));

        smartParseModule(matcher, parameters, startIndex, true);
        smartParseCategory(matcher, parameters, startIndex, true);
        smartParseTask(matcher, parameters, startIndex, true);
        smartParseFile(matcher);

        checkInvalid(matcher);
    }


    private void smartParseDeleteAndListModuleCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DELETE_AND_LIST_MODULE_FORMAT);

        smartParseIdentityModule(matcher, parameters, startIndex, false);

        boolean hasAllFlag = smartParseFlag(matcher, ALL_GROUP, ALL_FLAG);
        smartParseFlag(matcher, EXACT_GROUP, EXACT_FLAG);

        if (hasAllFlag) {
            highlightIncorrect(matcher, ALL_GROUP_SECOND);
        } else {
            smartParseFlag(matcher, ALL_GROUP_SECOND, ALL_FLAG);
        }

        checkInvalid(matcher);
    }

    private void smartParseDeleteAndListCategoryCommand(String parameters, int startIndex)
            throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DELETE_AND_LIST_CATEGORY_FORMAT);

        smartParseIdentityCategory(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, false);

        boolean hasAllFlag = smartParseFlag(matcher, ALL_GROUP, ALL_FLAG);
        smartParseFlag(matcher, EXACT_GROUP, EXACT_FLAG);

        if (hasAllFlag) {
            highlightIncorrect(matcher, ALL_GROUP_SECOND);
        } else {
            smartParseFlag(matcher, ALL_GROUP_SECOND, ALL_FLAG);
        }

        checkInvalid(matcher);
    }

    private void smartParseDeleteAndListTaskCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DELETE_AND_LIST_TASK_FORMAT);

        smartParseIdentityTask(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, false);
        smartParseCategory(matcher, parameters, startIndex, false);

        boolean hasAllFlag = smartParseFlag(matcher, ALL_GROUP, ALL_FLAG);
        smartParseFlag(matcher, EXACT_GROUP, EXACT_FLAG);

        if (hasAllFlag) {
            highlightIncorrect(matcher, ALL_GROUP_SECOND);
        } else {
            smartParseFlag(matcher, ALL_GROUP_SECOND, ALL_FLAG);
        }

        checkInvalid(matcher);
    }

    private void smartParseDeleteAndListFileCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DELETE_AND_LIST_FILE_FORMAT);

        smartParseIdentityFile(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, false);
        smartParseCategory(matcher, parameters, startIndex, false);
        smartParseTask(matcher, parameters, startIndex, false);
        smartParseIdentityModule(matcher, parameters, startIndex, true);

        boolean hasAllFlag = smartParseFlag(matcher, ALL_GROUP, ALL_FLAG);
        smartParseFlag(matcher, EXACT_GROUP, EXACT_FLAG);

        if (hasAllFlag) {
            highlightIncorrect(matcher, ALL_GROUP_SECOND);
        } else {
            smartParseFlag(matcher, ALL_GROUP_SECOND, ALL_FLAG);
        }

        checkInvalid(matcher);
    }

    private void smartParseListTaskSortedCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, LIST_TASK_SORTED_FORMAT);

        smartParseIdentityModule(matcher, parameters, startIndex, true);

        // Check if have only either deadline or priority
        boolean hasPriority = smartParseFlag(matcher, PRIORITY_GROUP, PRIORITY_PREFIX);
        if (hasPriority) {
            highlightIncorrect(matcher, DEADLINE_GROUP);
            highlightIncorrect(matcher, PRIORITY_GROUP_SECOND);
            checkInvalid(matcher);
            return;
        }

        boolean hasDeadline = smartParseFlag(matcher, DEADLINE_GROUP, DEADLINE_PREFIX);

        if (hasDeadline) {
            highlightIncorrect(matcher, PRIORITY_GROUP_SECOND);
        } else {
            smartParseFlag(matcher, PRIORITY_GROUP_SECOND, PRIORITY_PREFIX);
        }

        checkInvalid(matcher);
    }

    private void smartParseDueCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DUE_FORMAT);

        smartParseTimeSpecifier(matcher, parameters, startIndex);
        smartParseFlag(matcher, ALL_GROUP, ALL_FLAG);

        checkInvalid(matcher);
    }

    private void smartParseTimeSpecifier(Matcher matcher, String parameters, int startIndex)
            throws ParseFailureException {
        String rawFilter = matcher.group(IDENTIFIER_GROUP);
        if (rawFilter.isBlank()) {
            return;
        }
        String filter = rawFilter.trim().toLowerCase();

        if (filter.equals("over")) {
            syntaxConsole.getChildren().add(createText(rawFilter, Color.GREEN));
            return;
        }

        String[] filterData = filter.split("\\s+", 2);
        assert filterData.length <= 3 : "Should have at most 2 sections.";
        String timeSpecifier = "";
        String date = "";

        if (filterData.length == 1) {
            date = filterData[0];
        } else if (filterData.length == 2) {
            timeSpecifier = filterData[0];
            date = filterData[1];
        }

        int startIndexOfDate = rawFilter.indexOf(timeSpecifier) + timeSpecifier.length() + startIndex;
        int endIndexOfDate = matcher.end(IDENTIFIER_GROUP) + startIndex;
        String rawTimeSpecifier = rawFilter.substring(0, startIndexOfDate - startIndex);
        String rawDate = rawFilter.substring(startIndexOfDate - startIndex);
        String parametersAfterIncludeDate = parameters.substring(startIndexOfDate - startIndex);
        String parametersAfterExcludeDate = parameters.substring(endIndexOfDate - startIndex);

        if (timeSpecifier.isEmpty()) {
            ArrayList<String> suggestedTimeSpecifiers = generateSuggestedTimeSpecifier();
            populateSuggestions(date, suggestedTimeSpecifiers, startIndexOfDate, endIndexOfDate, NONE);

            if (isNotTypingAttribute(startIndexOfDate, endIndexOfDate)) {
                console.getEntriesPopup().hide();
            }
        }

        // Parse time specifier
        if (!timeSpecifier.isEmpty()) {
            if (isValidTimeSpecifier(timeSpecifier)) {
                addText(new Pair<>(rawTimeSpecifier, Color.GREEN));
            } else {
                if (isNotTypingAttribute(startIndex, startIndexOfDate)) {
                    addText(new Pair<>(rawTimeSpecifier, Color.CRIMSON),
                            new Pair<>(parametersAfterIncludeDate, Color.DARKGRAY));
                } else {
                    addText(new Pair<>(rawTimeSpecifier, Color.DARKGRAY),
                            new Pair<>(parametersAfterIncludeDate, Color.DARKGRAY));
                }
                throw new ParseFailureException();
            }

            if (timeSpecifier.equalsIgnoreCase("over")) {
                if (!date.isEmpty()) {
                    addText(new Pair<>(rawDate, Color.CRIMSON), new Pair<>(parametersAfterExcludeDate, Color.DARKGRAY));
                    throw new ParseFailureException();
                }
            }
        }

        // Parse date
        if (isValidDate(date)) {
            addText(new Pair<>(rawDate, Color.GREEN));
        } else {
            if (isNotTypingAttribute(startIndexOfDate, endIndexOfDate)) {
                addText(new Pair<>(rawDate, Color.CRIMSON), new Pair<>(parametersAfterExcludeDate, Color.DARKGRAY));
            } else {
                addText(new Pair<>(rawDate, Color.ORANGE), new Pair<>(parametersAfterExcludeDate, Color.DARKGRAY));
            }
            throw new ParseFailureException();
        }
    }

    private void smartParseEditModuleCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, EDIT_MODULE_FORMAT);

        smartParseIdentityModule(matcher, parameters, startIndex, true);
        checkDuplicateModule(matcher, parameters);

        checkInvalid(matcher);
    }

    private void smartParseEditCategoryCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, EDIT_CATEGORY_FORMAT);

        smartParseIdentityCategory(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, true);
        checkDuplicateCategory(matcher, parameters);
        smartParsePriority(matcher, parameters, PRIORITY_GROUP);

        checkInvalid(matcher);
    }

    private void smartParseEditTaskCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, EDIT_TASK_FORMAT);

        smartParseIdentityTask(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, true);
        smartParseCategory(matcher, parameters, startIndex, true);
        checkDuplicateTask(matcher, parameters);

        boolean hasPriority = smartParsePriority(matcher, parameters, PRIORITY_GROUP);
        smartParseDeadline(matcher, parameters, startIndex);
        if (hasPriority) {
            highlightIncorrect(matcher, PRIORITY_GROUP_SECOND);
        } else {
            smartParsePriority(matcher, parameters, PRIORITY_GROUP_SECOND);
        }

        checkInvalid(matcher);
    }

    private void smartParseEditFileCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, EDIT_FILE_FORMAT);

        smartParseIdentityFile(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, true);
        smartParseCategory(matcher, parameters, startIndex, true);
        smartParseTask(matcher, parameters, startIndex, true);
        checkDuplicateFile(matcher, parameters);

        checkInvalid(matcher);
    }

    private void smartParseMarkAsDoneCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DONE_FORMAT);

        smartParseIdentityTask(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, true);
        smartParseCategory(matcher, parameters, startIndex, true);

        checkInvalid(matcher);
    }

    private void smartParseOpenFileCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, OPEN_FILE_FORMAT);

        smartParseIdentityFile(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, true);
        smartParseCategory(matcher, parameters, startIndex, true);
        smartParseTask(matcher, parameters, startIndex, true);

        checkInvalid(matcher);
    }

    private void checkDuplicateModule(Matcher matcher, String parameters) throws ParseFailureException {
        String moduleGroup = matcher.group(MODULE_GROUP);
        if (moduleGroup.isBlank()) {
            return;
        }

        String moduleCode = moduleGroup.replace(MODULE_PREFIX, NONE).trim().toUpperCase();

        int endIndexOfPrefix = moduleGroup.indexOf(MODULE_PREFIX) + PREFIX_LENGTH;
        String prefix = moduleGroup.substring(0, endIndexOfPrefix);
        String rawModuleName = moduleGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(MODULE_GROUP));

        // Highlight the prefix first
        addText(new Pair<>(prefix, Color.GREEN));

        // Retrieves Module List and checks if new module code is repeated
        ArrayList<String> suggestedModules = generateSuggestedModules();
        if (suggestedModules.contains(moduleCode)) {
            // Repeated module code
            addText(new Pair<>(rawModuleName, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        } else if (!ModuleManager.getModulesMap().containsKey(moduleCode)) {
            // Module code is not an NUS module
            addText(new Pair<>(rawModuleName, Color.ORANGE));
        } else {
            // Valid module code
            addText(new Pair<>(rawModuleName, Color.BLUE));
        }
    }

    private void checkDuplicateCategory(Matcher matcher, String parameters) throws ParseFailureException {
        String categoryGroup = matcher.group(CATEGORY_GROUP);
        if (categoryGroup.isBlank()) {
            return;
        }

        String categoryName = categoryGroup.replace(CATEGORY_PREFIX, NONE).trim();

        int endIndexOfPrefix = categoryGroup.indexOf(CATEGORY_PREFIX) + PREFIX_LENGTH;
        String prefix = categoryGroup.substring(0, endIndexOfPrefix);
        String rawCategoryName = categoryGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(CATEGORY_GROUP));

        // Highlight the prefix first
        addText(new Pair<>(prefix, Color.GREEN));

        // Retrieves Category List and checks if new category name is repeated
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        try {
            ArrayList<String> suggestedCategories = generateSuggestedCategories(moduleCode, true);
            if (suggestedCategories.contains(categoryName)) {
                // Repeated category name
                addText(new Pair<>(rawCategoryName, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                // Valid new category name
                addText(new Pair<>(rawCategoryName, Color.BLUE));
            }
        } catch (IncorrectDirectoryLevelException e) {
            // Unable to get missing module code
            addText(new Pair<>(rawCategoryName, Color.ORANGE), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }
    }

    private void checkDuplicateTask(Matcher matcher, String parameters) throws ParseFailureException {
        String taskGroup = matcher.group(TASK_GROUP);
        if (taskGroup.isBlank()) {
            return;
        }

        String taskDescription = taskGroup.replace(TASK_PREFIX, NONE).trim();

        int endIndexOfPrefix = taskGroup.indexOf(TASK_PREFIX) + PREFIX_LENGTH;
        String prefix = taskGroup.substring(0, endIndexOfPrefix);
        String rawTaskDescription = taskGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(TASK_GROUP));

        // Highlight the prefix first
        addText(new Pair<>(prefix, Color.GREEN));

        // Retrieves Task List and checks if new task description is repeated
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        try {
            ArrayList<String> suggestedTasks = generateSuggestedTasks(moduleCode, categoryName, true);
            if (suggestedTasks.contains(taskDescription)) {
                // Repeated task description
                addText(new Pair<>(rawTaskDescription, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                // Valid new task description
                addText(new Pair<>(rawTaskDescription, Color.BLUE));
            }
        } catch (IncorrectDirectoryLevelException e) {
            // Unable to get missing module code or category name
            addText(new Pair<>(rawTaskDescription, Color.ORANGE), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }
    }

    private void checkDuplicateFile(Matcher matcher, String parameters) throws ParseFailureException {
        String fileGroup = matcher.group(FILE_GROUP);
        if (fileGroup.isBlank()) {
            return;
        }

        String fileName = fileGroup.replace(FILE_PREFIX, NONE).trim();

        int endIndexOfPrefix = fileGroup.indexOf(FILE_PREFIX) + PREFIX_LENGTH;
        String prefix = fileGroup.substring(0, endIndexOfPrefix);
        String rawFileName = fileGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(FILE_GROUP));

        // Highlight the prefix first
        addText(new Pair<>(prefix, Color.GREEN));

        // Retrieves File List and checks if new file name is repeated
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String taskDescription = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();
        try {
            ArrayList<String> suggestedFiles = generateSuggestedFiles(moduleCode, categoryName,
                    taskDescription, false);
            if (suggestedFiles.contains(fileName)) {
                // Repeated file name
                addText(new Pair<>(rawFileName, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                // Valid new file name
                addText(new Pair<>(rawFileName, Color.BLUE));
            }
        } catch (IncorrectDirectoryLevelException e) {
            // Unable to get missing module code or category name or task description
            addText(new Pair<>(rawFileName, Color.ORANGE), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }
    }

    private void smartParseIdentityModule(Matcher matcher, String parameters, int startIndex, boolean isExact)
            throws ParseFailureException {
        String rawModuleCode = matcher.group(IDENTIFIER_GROUP);
        if (rawModuleCode.isBlank()) {
            return;
        }

        int startIndexOfModule =  matcher.start(IDENTIFIER_GROUP) + startIndex;
        int endIndexOfModule =  matcher.end(IDENTIFIER_GROUP) + startIndex;

        String moduleCode = rawModuleCode.trim().toUpperCase();
        String parametersAfter =  parameters.substring(matcher.end(IDENTIFIER_GROUP));

        ArrayList<String> suggestedModules = generateSuggestedModules();
        populateSuggestions(moduleCode, suggestedModules, startIndexOfModule, endIndexOfModule, NONE);

        if (isNotTypingAttribute(startIndexOfModule, endIndexOfModule)) {
            console.getEntriesPopup().hide();
        }

        highlightInput(moduleCode, rawModuleCode, parametersAfter, endIndexOfModule, suggestedModules, isExact);
    }

    private void smartParseIdentityCategory(Matcher matcher, String parameters, int startIndex)
            throws ParseFailureException {
        String rawCategoryName = matcher.group(IDENTIFIER_GROUP);
        if (rawCategoryName.isBlank()) {
            return;
        }

        int startIndexOfCategory =  matcher.start(IDENTIFIER_GROUP) + startIndex;
        int endIndexOfCategory =  matcher.end(IDENTIFIER_GROUP) + startIndex;

        String categoryName = rawCategoryName.trim();
        String parametersAfter =  parameters.substring(matcher.end(IDENTIFIER_GROUP));

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        ArrayList<String> suggestedCategories;
        try {
            suggestedCategories = generateSuggestedCategories(moduleCode, false);
            populateSuggestions(categoryName, suggestedCategories, startIndexOfCategory, endIndexOfCategory, NONE);
        } catch (IncorrectDirectoryLevelException e) {
            addText(new Pair<>(rawCategoryName, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (isNotTypingAttribute(startIndexOfCategory, endIndexOfCategory)) {
            console.getEntriesPopup().hide();
        }

        highlightInput(categoryName, rawCategoryName, parametersAfter, endIndexOfCategory, suggestedCategories, false);
    }

    private void smartParseIdentityTask(Matcher matcher, String parameters, int startIndex)
            throws ParseFailureException {
        String rawTaskDescription = matcher.group(IDENTIFIER_GROUP);
        if (rawTaskDescription.isBlank()) {
            return;
        }

        int startIndexOfTask =  matcher.start(IDENTIFIER_GROUP) + startIndex;
        int endIndexOfTask =  matcher.end(IDENTIFIER_GROUP) + startIndex;

        String taskDescription = rawTaskDescription.trim();
        String parametersAfter =  parameters.substring(matcher.end(IDENTIFIER_GROUP));

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();

        ArrayList<String> suggestedTasks;
        try {
            suggestedTasks = generateSuggestedTasks(moduleCode, categoryName, false);
            populateSuggestions(taskDescription, suggestedTasks, startIndexOfTask, endIndexOfTask, NONE);
        } catch (IncorrectDirectoryLevelException e) {
            addText(new Pair<>(rawTaskDescription, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (isNotTypingAttribute(startIndexOfTask, endIndexOfTask)) {
            console.getEntriesPopup().hide();
        }

        highlightInput(taskDescription, rawTaskDescription, parametersAfter, endIndexOfTask, suggestedTasks, false);
    }

    private void smartParseIdentityFile(Matcher matcher, String parameters, int startIndex)
            throws ParseFailureException {
        String rawFileName = matcher.group(IDENTIFIER_GROUP);
        if (rawFileName.isBlank()) {
            return;
        }

        int startIndexOfFile =  matcher.start(IDENTIFIER_GROUP) + startIndex;
        int endIndexOfFile =  matcher.end(IDENTIFIER_GROUP) + startIndex;

        String fileName = rawFileName.trim();
        String parametersAfter =  parameters.substring(matcher.end(IDENTIFIER_GROUP));

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        String taskDescription = matcher.group(TASK_GROUP).replace(TASK_PREFIX, NONE).trim();

        ArrayList<String> suggestedFiles;
        try {
            suggestedFiles = generateSuggestedFiles(moduleCode, categoryName, taskDescription, false);
            populateSuggestions(fileName, suggestedFiles, startIndexOfFile, endIndexOfFile, NONE);
        } catch (IncorrectDirectoryLevelException e) {
            addText(new Pair<>(rawFileName, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (isNotTypingAttribute(startIndexOfFile, endIndexOfFile)) {
            console.getEntriesPopup().hide();
        }

        highlightInput(fileName, rawFileName, parametersAfter, endIndexOfFile, suggestedFiles, false);
    }

    private void smartParseModule(Matcher matcher, String parameters, int startIndex, boolean isExact)
            throws ParseFailureException {
        String moduleGroup = matcher.group(MODULE_GROUP);
        if (moduleGroup.isBlank()) {
            return;
        }

        int startIndexOfModule =  matcher.start(MODULE_GROUP) + startIndex;
        int endIndexOfModule =  matcher.end(MODULE_GROUP) + startIndex;

        String moduleCode = moduleGroup.replace(MODULE_PREFIX, NONE).trim().toUpperCase();

        int endIndexOfPrefix = moduleGroup.indexOf(MODULE_PREFIX) + PREFIX_LENGTH;
        String prefix = moduleGroup.substring(0, endIndexOfPrefix);

        addText(new Pair<>(prefix, Color.GREEN));

        ArrayList<String> suggestedModules = generateSuggestedModules();
        populateSuggestions(moduleCode, suggestedModules, startIndexOfModule, endIndexOfModule, MODULE_PREFIX);

        if (isNotTypingAttribute(startIndexOfModule, endIndexOfModule)) {
            console.getEntriesPopup().hide();
        }

        String rawModuleCode = moduleGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(MODULE_GROUP));
        highlightInput(moduleCode, rawModuleCode, parametersAfter, endIndexOfModule, suggestedModules, isExact);
    }

    private void smartParseCategory(Matcher matcher, String parameters, int startIndex, boolean isExact)
            throws ParseFailureException {
        String categoryGroup = matcher.group(CATEGORY_GROUP);
        if (categoryGroup.isBlank()) {
            return;
        }

        int startIndexOfCategory =  matcher.start(CATEGORY_GROUP) + startIndex;
        int endIndexOfCategory =  matcher.end(CATEGORY_GROUP) + startIndex;

        String categoryName = categoryGroup.replace(CATEGORY_PREFIX, NONE).trim();

        int endIndexOfPrefix = categoryGroup.indexOf(CATEGORY_PREFIX) + PREFIX_LENGTH;
        String prefix = categoryGroup.substring(0, endIndexOfPrefix);
        String rawCategoryName = categoryGroup.substring(endIndexOfPrefix);

        addText(new Pair<>(prefix, Color.GREEN));

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        ArrayList<String> suggestedCategories;
        try {
            suggestedCategories = generateSuggestedCategories(moduleCode, isExact);
            populateSuggestions(categoryName, suggestedCategories, startIndexOfCategory,
                    endIndexOfCategory, CATEGORY_PREFIX);
        } catch (IncorrectDirectoryLevelException e) {
            addText(new Pair<>(rawCategoryName, Color.CRIMSON), new Pair<>(parameters, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (isNotTypingAttribute(startIndexOfCategory, endIndexOfCategory)) {
            console.getEntriesPopup().hide();
        }

        String parametersAfter = parameters.substring(matcher.end(CATEGORY_GROUP));
        highlightInput(categoryName, rawCategoryName, parametersAfter, endIndexOfCategory,
                suggestedCategories, isExact);
    }

    private void smartParseTask(Matcher matcher, String parameters, int startIndex, boolean isExact)
            throws ParseFailureException {
        String taskGroup = matcher.group(TASK_GROUP);
        if (taskGroup.isBlank()) {
            return;
        }

        int startIndexOfTask =  matcher.start(TASK_GROUP) + startIndex;
        int endIndexOfTask =  matcher.end(TASK_GROUP) + startIndex;

        String taskDescription = taskGroup.replace(TASK_PREFIX, NONE).trim();

        int endIndexOfPrefix = taskGroup.indexOf(TASK_PREFIX) + PREFIX_LENGTH;
        String prefix = taskGroup.substring(0, endIndexOfPrefix);
        String rawTaskDescription = taskGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(TASK_GROUP));

        addText(new Pair<>(prefix, Color.GREEN));

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        ArrayList<String> suggestedTasks;
        try {
            suggestedTasks = generateSuggestedTasks(moduleCode, categoryName, isExact);
            populateSuggestions(taskDescription, suggestedTasks, startIndexOfTask, endIndexOfTask, TASK_PREFIX);
        } catch (IncorrectDirectoryLevelException e) {
            addText(new Pair<>(rawTaskDescription, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (isNotTypingAttribute(startIndexOfTask, endIndexOfTask)) {
            console.getEntriesPopup().hide();
        }

        highlightInput(taskDescription, rawTaskDescription, parametersAfter, endIndexOfTask,
                suggestedTasks, isExact);
    }

    private void smartParseFile(Matcher matcher) {
        String fileGroup = matcher.group(FILE_GROUP);
        if (fileGroup.isBlank()) {
            return;
        }

        int endIndexOfPrefix = fileGroup.indexOf(FILE_PREFIX) + PREFIX_LENGTH;
        String prefix = fileGroup.substring(0, endIndexOfPrefix);

        addText(new Pair<>(prefix, Color.GREEN));

        String rawFileName = fileGroup.substring(endIndexOfPrefix);
        addText(new Pair<>(rawFileName, Color.BLUE));
    }

    private void smartParseDeadline(Matcher matcher, String parameters, int startIndex) throws ParseFailureException {
        String deadlineGroup = matcher.group(DEADLINE_GROUP);
        if (deadlineGroup.isBlank()) {
            return;
        }

        int startIndexOfDeadline =  matcher.start(DEADLINE_GROUP) + startIndex;
        int endIndexOfDeadline =  matcher.end(DEADLINE_GROUP) + startIndex;

        int endIndexOfPrefix = deadlineGroup.indexOf(DEADLINE_PREFIX) + PREFIX_LENGTH;
        String prefix = deadlineGroup.substring(0, endIndexOfPrefix);
        String rawDeadline = deadlineGroup.substring(endIndexOfPrefix);
        String deadline = rawDeadline.trim();
        String parametersAfter =  parameters.substring(matcher.end(DEADLINE_GROUP));

        addText(new Pair<>(prefix, Color.GREEN));

        if (isValidDeadline(deadline)) {
            addText(new Pair<>(rawDeadline, Color.GREEN));
        } else {
            if (isNotTypingAttribute(startIndexOfDeadline, endIndexOfDeadline)) {
                addText(new Pair<>(rawDeadline, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
            } else {
                addText(new Pair<>(rawDeadline, Color.ORANGE), new Pair<>(parametersAfter, Color.DARKGRAY));
            }
            throw new ParseFailureException();
        }
    }

    private boolean smartParsePriority(Matcher matcher, String parameters, String groupName)
            throws ParseFailureException {
        String priorityGroup = matcher.group(groupName);
        if (priorityGroup.isBlank()) {
            return false;
        }

        int endIndexOfPrefix = priorityGroup.indexOf(PRIORITY_PREFIX) + PREFIX_LENGTH;
        String prefix = priorityGroup.substring(0, endIndexOfPrefix);
        String rawPriority = priorityGroup.substring(endIndexOfPrefix);
        String priority = rawPriority.trim();
        String parametersAfter =  parameters.substring(matcher.end(groupName));

        addText(new Pair<>(prefix, Color.GREEN));

        if (isValidPriority(priority)) {
            addText(new Pair<>(rawPriority, Color.GREEN));
        } else {
            addText(new Pair<>(rawPriority, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        return true;
    }

    private boolean smartParseFlag(Matcher matcher, String flagName, String flagPrefix) {
        String flagGroup = matcher.group(flagName);
        if (flagGroup.isBlank()) {
            return false;
        }

        int endIndexOfPrefix = flagGroup.indexOf(flagPrefix) + PREFIX_LENGTH;
        String prefix = flagGroup.substring(0, endIndexOfPrefix);

        addText(new Pair<>(prefix, Color.GREEN));

        String rest = flagGroup.substring(endIndexOfPrefix);
        addText(new Pair<>(rest, Color.GREEN));
        return true;
    }

    private boolean isValidDeadline(String deadlineString) {
        try {
            // A valid deadline can be successfully parsed as a datetime
            DateTimeFormat.stringToDateTime(deadlineString);
            return true;
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            return false;
        }
    }

    private boolean isValidPriority(String priorityString) {
        try {
            int priority = Integer.parseInt(priorityString);
            return priority >= 0 && priority <= 20;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidTimeSpecifier(String timeSpecifier) {
        final ArrayList<String> acceptedTimeSpecifiers = new ArrayList<>(
                Arrays.asList("after", "a", "before", "b", "on", "over")
        );
        return acceptedTimeSpecifiers.contains(timeSpecifier.toLowerCase());
    }

    private boolean isValidDate(String dateString) {
        try {
            // A valid date can be successfully parsed as a date
            DateTimeFormat.stringToDate(dateString);
            return true;
        } catch (DateTimeFormat.InvalidDateException e) {
            return false;
        }
    }

    private Matcher matchPattern(String parameters, Pattern format)
            throws ParseFailureException {
        final Matcher matcher = format.matcher(parameters);

        if (!matcher.matches()) {
            addText(new Pair<>(parameters, Color.CRIMSON));
            throw new ParseFailureException();
        }

        return matcher;
    }

    private void highlightInput(String parameter, String rawParameter, String parametersAfter, int endIndex,
            ArrayList<String> suggestions, boolean isExact) throws ParseFailureException {
        if (!isPartOfWord(parameter, suggestions)) {
            // Does not match a suggestion at all
            if (isExact) {
                addText(new Pair<>(rawParameter, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                addText(new Pair<>(rawParameter, Color.ORANGE));
            }
            return;
        }

        if (isMatchingWord(parameter, suggestions)) {
            // Completely matches a suggestion
            addText(new Pair<>(rawParameter, Color.GREEN));
        } else if (console.getCaretPosition() > endIndex) {
            // Partially matches a suggestion but not typing
            if (isExact) {
                addText(new Pair<>(rawParameter, Color.CRIMSON), new Pair<>(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                addText(new Pair<>(rawParameter, Color.ORANGE));
            }
        } else {
            // Partially matches a suggestion and still typing
            addText(new Pair<>(rawParameter, Color.DARKGRAY));
            if (isExact) {
                addText(new Pair<>(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            }
        }
    }

    private void highlightIncorrect(Matcher matcher, String groupName) {
        String group = matcher.group(groupName);
        addText(new Pair<>(group, Color.CRIMSON));
    }

    /**
     * Adds colored texts into the syntax console.
     *
     * @param texts
     *  The colored texts to be added
     */
    @SafeVarargs
    private final void addText(Pair<String, Color>... texts) {
        for (Pair<String, Color> stringColorPair : texts) {
            syntaxConsole.getChildren().add(createText(stringColorPair.getKey(), stringColorPair.getValue()));
        }
    }

    private ArrayList<String> generateSuggestedDirectories() {
        ArrayList<String> suggestions = new ArrayList<>();
        switch (getCurrentDirectoryLevel()) {
        case ROOT:
            for (Module module : ModuleManager.getModuleList()) {
                suggestions.add(module.getModuleCode());
            }
            break;

        case MODULE:
            for (Category category : ((Module) getCurrentDirectory()).getCategories().getCategoryList()) {
                suggestions.add(category.getCategoryName());
            }
            break;

        case CATEGORY:
            for (Task task : ((Category) getCurrentDirectory()).getTasks().getTaskList()) {
                suggestions.add(task.getDescription());
            }
            break;

        case TASK:
            for (TaskFile file : ((Task) getCurrentDirectory()).getFiles().getFileList()) {
                suggestions.add(file.getFileName());
            }
            break;

        default:
            break;
        }

        return suggestions;
    }

    private ArrayList<String> generateSuggestedNewModules() {
        ArrayList<String> moduleSuggestions = new ArrayList<>(ModuleManager.getModulesMap().keySet());
        // Show only suggestions of modules that have not been added yet
        ModuleManager.getModuleList().stream()
                .map(Module::getModuleCode)     // Get module code for current modules
                .forEach(moduleSuggestions::remove);   // Remove module code from set

        return moduleSuggestions;
    }

    private ArrayList<String> generateSuggestedModules() {
        return ModuleManager.getModuleList().stream()
                .map(Module::getModuleCode)     // Get all the module codes in the Module List
                .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
    }

    private ArrayList<String> generateSuggestedCategories(String moduleCode, boolean isExact)
            throws IncorrectDirectoryLevelException {

        // Fill in missing information for exact filtering
        if (moduleCode.isEmpty() && isExact) {
            moduleCode = getBaseModule().getModuleCode();
        }

        if (isExact) {
            return ModuleManager.filterExact(moduleCode, NONE).stream()
                    .map(Category::getCategoryName) // Get all the category names in the Category List of the module
                    .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
        } else {
            return ModuleManager.filter(moduleCode, NONE).stream()
                    .map(Category::getCategoryName) // Get all the category names in the Category List of the module
                    .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
        }
    }

    private ArrayList<String> generateSuggestedTasks(String moduleCode, String categoryName, boolean isExact)
            throws IncorrectDirectoryLevelException {

        // Fill in missing information for exact filtering
        if (moduleCode.isEmpty() && isExact) {
            moduleCode = getBaseModule().getModuleCode();
        }
        if (categoryName.isEmpty() && isExact) {
            if (!getBaseModule().isSameModule(moduleCode)) {
                throw new IncorrectDirectoryLevelException();
            }
            categoryName = getBaseCategory().getCategoryName();
        }

        if (isExact) {
            return ModuleManager.filterExact(moduleCode, categoryName, NONE).stream()
                    .map(Task::getDescription) // Get all task descriptions in the Task List of the module's category
                    .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
        } else {
            return ModuleManager.filter(moduleCode, categoryName, NONE).stream()
                    .map(Task::getDescription) // Get all task descriptions in the Task List of the module's category
                    .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
        }
    }

    private ArrayList<String> generateSuggestedFiles(String moduleCode, String categoryName, String taskDescription,
            boolean isExact) throws IncorrectDirectoryLevelException {
        // Fill in missing information for exact filtering
        if (isExact) {
            if (moduleCode.isEmpty()) {
                moduleCode = getBaseModule().getModuleCode();
            }
            if (categoryName.isEmpty()) {
                if (!getBaseModule().isSameModule(moduleCode)) {
                    throw new IncorrectDirectoryLevelException();
                }
                categoryName = getBaseCategory().getCategoryName();
            }
            if (taskDescription.isEmpty()) {
                if (!getBaseModule().isSameModule(moduleCode) || !getBaseCategory().isSameCategory(categoryName)) {
                    throw new IncorrectDirectoryLevelException();
                }
                taskDescription = getBaseTask().getDescription();
            }
        }

        if (isExact) {
            return ModuleManager.filterExact(moduleCode, categoryName, taskDescription, NONE).stream()
                    .map(TaskFile::getFileName) // Get all file names in the File List of the category's tasks
                    .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
        } else {
            return ModuleManager.filter(moduleCode, categoryName, taskDescription, NONE).stream()
                    .map(TaskFile::getFileName) // Get all file names in the File List of the category's tasks
                    .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
        }
    }

    private ArrayList<String> generateSuggestedTimeSpecifier() {
        return new ArrayList<>(Arrays.asList("before", "after", "on", "over"));
    }

    private void populateSuggestions(String keyword, ArrayList<String> suggestions,
                                     int startIndex, int endIndex, String prefix) {
        console.getEntries().clear();
        console.getEntries().addAll(suggestions);
        console.setEnteredText(keyword, startIndex, endIndex, prefix);
        console.displaySuggestions();
    }

    private boolean isNotTypingAttribute(int startIndex, int endIndex) {
        return console.getCaretPosition() < startIndex || console.getCaretPosition() > endIndex;
    }

    private void checkInvalid(Matcher matcher) {
        String invalid = matcher.group(INVALID_GROUP);
        syntaxConsole.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    private boolean isPartOfWord(String givenWord, ArrayList<String> acceptedWords) {
        for (String word : acceptedWords) {
            if (word.contains(givenWord)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatchingWord(String givenWord, ArrayList<String> acceptedWords) {
        for (String word : acceptedWords) {
            if (word.equals(givenWord)) {
                return true;
            }
        }
        return false;
    }

    private void smartParseIndices(String input) throws ParseFailureException {
        final int limit = Executor.getFilteredList().size();
        if (input.isBlank()) {
            return;
        }

        final char whiteSpace = ' ';
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == whiteSpace) {
                continue;
            }

            // Find all list numbers separated by spaces
            int indexBefore = i;
            StringBuilder listNumberToParse = new StringBuilder();
            for (; i < input.length() && input.charAt(i) != whiteSpace; ++i) {
                listNumberToParse.append(input.charAt(i));
            }
            try {
                int listNumber = Integer.parseInt(listNumberToParse.toString());
                // Checks if list number is within limit
                if (isInvalidListNumber(listNumber, limit)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                // List number either not within limit, or contains non-numeric character
                String correctSegment = input.substring(0, indexBefore);
                String incorrectSegment = input.substring(indexBefore, i);
                String afterSegment = (i < input.length()) ? input.substring(i) : "";
                addText(new Pair<>(correctSegment, Color.GREEN), new Pair<>(incorrectSegment, Color.CRIMSON),
                        new Pair<>(afterSegment, Color.DARKGRAY));
                throw new ParseFailureException();
            }
        }
        addText(new Pair<>(input, Color.GREEN));
    }

    private boolean isInvalidListNumber(int listNumber, int limit) {
        return listNumber <= 0 || listNumber > limit;
    }

    private void smartParseConfirmation(String input) {
        final ArrayList<String> acceptedConfirmations = new ArrayList<>(Arrays.asList("yes", "y", "no", "n"));
        if (acceptedConfirmations.contains(input.trim().toLowerCase())) {
            addText(new Pair<>(input, Color.GREEN));
        } else {
            addText(new Pair<>(input, Color.CRIMSON));
        }
    }
}
