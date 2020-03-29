package seedu.nuke.gui.io;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import seedu.nuke.command.ChangeDirectoryCommand;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.addcommand.AddCategoryCommand;
import seedu.nuke.command.addcommand.AddModuleCommand;
import seedu.nuke.command.addcommand.AddTaskCommand;
import seedu.nuke.command.editcommand.EditCategoryCommand;
import seedu.nuke.command.editcommand.EditModuleCommand;
import seedu.nuke.command.editcommand.EditTaskCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteCategoryCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteModuleCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteTaskCommand;
import seedu.nuke.command.filtercommand.listcommand.ListCategoryCommand;
import seedu.nuke.command.filtercommand.listcommand.ListModuleCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskCommand;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.directory.TaskFile;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.exception.ParseFailureException;
import seedu.nuke.gui.component.AutoCompleteTextField;
import seedu.nuke.util.DateTimeFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static seedu.nuke.directory.DirectoryTraverser.getBaseCategory;
import static seedu.nuke.directory.DirectoryTraverser.getBaseModule;
import static seedu.nuke.directory.DirectoryTraverser.getCurrentDirectory;
import static seedu.nuke.directory.DirectoryTraverser.getCurrentDirectoryLevel;
import static seedu.nuke.gui.io.GuiCommandPattern.ADD_CATEGORY_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.ADD_MODULE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.ADD_TASK_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.BASIC_COMMAND_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DELETE_AND_LIST_CATEGORY_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DELETE_AND_LIST_MODULE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.DELETE_AND_LIST_TASK_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.EDIT_CATEGORY_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.EDIT_MODULE_FORMAT;
import static seedu.nuke.gui.io.GuiCommandPattern.EDIT_TASK_FORMAT;
import static seedu.nuke.gui.util.TextUtil.createText;


public class GuiParser {
    private static final String NONE = "";

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
    private static final String FILE_GROUP = "filePath";
    private static final String DEADLINE_GROUP = "deadline";
    private static final String PRIORITY_GROUP = "priority";
    private static final String EXACT_GROUP = "exact";
    private static final String ALL_GROUP = "all";
    private static final String INVALID_GROUP = "invalid";

    private AutoCompleteTextField console;
    private TextFlow highlightedInput;

    public GuiParser(AutoCompleteTextField console) {
        this.console = console;
        this.highlightedInput = new TextFlow();
    }

    private static final ArrayList<String> COMMAND_WORDS = new ArrayList<>(Arrays.asList(
            AddModuleCommand.COMMAND_WORD, AddCategoryCommand.COMMAND_WORD, AddTaskCommand.COMMAND_WORD,
            DeleteModuleCommand.COMMAND_WORD, DeleteCategoryCommand.COMMAND_WORD, DeleteTaskCommand.COMMAND_WORD,
            ListModuleCommand.COMMAND_WORD, ListCategoryCommand.COMMAND_WORD, ListTaskCommand.COMMAND_WORD,
            EditModuleCommand.COMMAND_WORD, EditCategoryCommand.COMMAND_WORD, EditTaskCommand.COMMAND_WORD,
            ChangeDirectoryCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD
    ));

    public TextFlow smartParse(String input) {
        try {
            Matcher matcher = smartParseCommandWord(input);
            smartParseParameters(matcher);
        } catch (ParseFailureException e) {
            return highlightedInput;
        }
        return highlightedInput;
    }

    /**
     * Parses the command word in the input string from the GUI console.
     *
     * @param input
     *  The user input read by the <b>GUI</b>
     */
    private Matcher smartParseCommandWord(String input) throws ParseFailureException {
        final Matcher matcher = matchPattern(input, BASIC_COMMAND_FORMAT);

        String rawCommandWord = matcher.group(COMMAND_WORD_GROUP);
        String commandWord = rawCommandWord.trim().toLowerCase();
        String parameters = matcher.group(PARAMETERS_GROUP);

        int startIndexOfCommandWord = matcher.start(COMMAND_WORD_GROUP);
        int endIndexOfCommandWord = matcher.end(COMMAND_WORD_GROUP);

        populateSuggestions(commandWord, COMMAND_WORDS, startIndexOfCommandWord, endIndexOfCommandWord, NONE);

        if (console.getCaretPosition() < startIndexOfCommandWord
                || console.getCaretPosition() > endIndexOfCommandWord) {
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
        case AddModuleCommand.COMMAND_WORD:
            smartParseAddModuleCommand(parameters, startIndexOfParameters);
            break;

        case AddCategoryCommand.COMMAND_WORD:
            smartParseAddCategoryCommand(parameters, startIndexOfParameters);
            break;

        case AddTaskCommand.COMMAND_WORD:
            smartParseAddTaskCommand(parameters, startIndexOfParameters);
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

        case EditModuleCommand.COMMAND_WORD:
            smartParseEditModuleCommand(parameters, startIndexOfParameters);
            break;

        case EditCategoryCommand.COMMAND_WORD:
            smartParseEditCategoryCommand(parameters, startIndexOfParameters);
            break;

        case EditTaskCommand.COMMAND_WORD:
            smartParseEditTaskCommand(parameters, startIndexOfParameters);
            break;

        case ChangeDirectoryCommand.COMMAND_WORD:
            smartParseChangeDirectoryCommand(parameters, startIndexOfParameters);
            break;

        default:
            break;
        }
    }


    private void populateCommandWordSuggestions(String commandWord, String parameters, int startIndex, int endIndex) {
        if (parameters.isEmpty()) {
            // Add the list of command words as possible suggestions
            console.getEntries().clear();
            console.getEntries().addAll(COMMAND_WORDS);
        } else {
            // Hide current suggestions
            console.getEntries().clear();
            console.getEntriesPopup().hide();
        }
        console.setEnteredText(commandWord, startIndex, endIndex);
        console.displaySuggestions();
    }

    private void smartParseChangeDirectoryCommand(String parameters, int startIndex) {
        int endIndex = startIndex + parameters.length();
        String nextDirectory = parameters.trim();

        if (nextDirectory.equals("..")) {
            highlightedInput.getChildren().add(createText(parameters, Color.GREEN));
        } else {
            ArrayList<String> suggestedDirectories = generateSuggestedDirectories();
            populateSuggestions(nextDirectory, suggestedDirectories, startIndex, endIndex, NONE);

            if (isMatchingWord(nextDirectory, suggestedDirectories)) {
                highlightedInput.getChildren().add(createText(parameters, Color.GREEN));
            } else if (isPartOfWord(nextDirectory, suggestedDirectories)) {
                highlightedInput.getChildren().add(createText(parameters, Color.ORANGE));
            } else {
                highlightedInput.getChildren().add(createText(parameters, Color.CRIMSON));
            }
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

    private void smartParseAddModuleCommand(String parameters, int startIndex)
            throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, ADD_MODULE_FORMAT);

        String moduleCode = matcher.group(IDENTIFIER_GROUP);
        int startIndexOfModule =  matcher.start(IDENTIFIER_GROUP) + startIndex;
        int endIndexOfModule =  matcher.end(IDENTIFIER_GROUP) + startIndex;

        String invalid = matcher.group(INVALID_GROUP);

        ArrayList<String> suggestedModules = generateSuggestedNewModules();
        populateSuggestions(moduleCode.trim(), suggestedModules, startIndexOfModule, endIndexOfModule, NONE);

        if (console.getCaretPosition() > endIndexOfModule) {
            console.getEntriesPopup().hide();
        }

        highlightInput(moduleCode.trim().toUpperCase(), moduleCode, NONE, endIndexOfModule,
                suggestedModules, true);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    void smartParseAddCategoryCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, ADD_CATEGORY_FORMAT);

        String categoryName = matcher.group(IDENTIFIER_GROUP);
        String invalid = matcher.group(INVALID_GROUP);

        highlightedInput.getChildren().add(createText(categoryName, Color.BLUE));

        smartParseModule(matcher, parameters, startIndex, true);

        smartParsePriority(matcher, parameters);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));

    }

    void smartParseAddTaskCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, ADD_TASK_FORMAT);

        String taskDescription = matcher.group(IDENTIFIER_GROUP);
        String invalid = matcher.group(INVALID_GROUP);

        highlightedInput.getChildren().add(createText(taskDescription, Color.BLUE));

        smartParseModule(matcher, parameters, startIndex, true);
        smartParseCategory(matcher, parameters, startIndex, true);
        smartParseDeadline(matcher, parameters);
        smartParsePriority(matcher, parameters);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    private void smartParseDeleteAndListModuleCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DELETE_AND_LIST_MODULE_FORMAT);

        String invalid = matcher.group(INVALID_GROUP);

        smartParseIdentityModule(matcher, parameters, startIndex, false);
        smartParseFlag(matcher, EXACT_GROUP);
        smartParseFlag(matcher, ALL_GROUP);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    private void smartParseDeleteAndListCategoryCommand(String parameters, int startIndex)
            throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DELETE_AND_LIST_CATEGORY_FORMAT);

        String invalid = matcher.group(INVALID_GROUP);

        smartParseIdentityCategory(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, false);
        smartParseFlag(matcher, EXACT_GROUP);
        smartParseFlag(matcher, ALL_GROUP);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    private void smartParseDeleteAndListTaskCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, DELETE_AND_LIST_TASK_FORMAT);

        String invalid = matcher.group(INVALID_GROUP);

        smartParseIdentityTask(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, false);
        smartParseCategory(matcher, parameters, startIndex, false);
        smartParseFlag(matcher, EXACT_GROUP);
        smartParseFlag(matcher, ALL_GROUP);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    private void smartParseEditModuleCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, EDIT_MODULE_FORMAT);

        String invalid = matcher.group(INVALID_GROUP);

        smartParseIdentityModule(matcher, parameters, startIndex, true);
        checkDuplicateModule(matcher, parameters);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    private void smartParseEditCategoryCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, EDIT_CATEGORY_FORMAT);

        String invalid = matcher.group(INVALID_GROUP);

        smartParseIdentityCategory(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, true);
        checkDuplicateCategory(matcher, parameters);
        smartParsePriority(matcher, parameters);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    private void smartParseEditTaskCommand(String parameters, int startIndex) throws ParseFailureException {
        final Matcher matcher = matchPattern(parameters, EDIT_TASK_FORMAT);

        String invalid = matcher.group(INVALID_GROUP);

        smartParseIdentityTask(matcher, parameters, startIndex);
        smartParseModule(matcher, parameters, startIndex, true);
        smartParseCategory(matcher, parameters, startIndex, true);
        checkDuplicateTask(matcher, parameters);
        smartParseDeadline(matcher, parameters);
        smartParsePriority(matcher, parameters);

        highlightedInput.getChildren().add(createText(invalid, Color.CRIMSON));
    }

    private void checkDuplicateTask(Matcher matcher, String parameters) throws ParseFailureException {
        String taskGroup = matcher.group(TASK_GROUP);
        if (taskGroup.isBlank()) {
            return;
        }

        String taskDescription = taskGroup.replace(TASK_PREFIX, NONE).trim();

        int endIndexOfPrefix = taskGroup.indexOf(TASK_PREFIX) + 2;
        String prefix = taskGroup.substring(0, endIndexOfPrefix);
        String rawTaskDescription = taskGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(TASK_GROUP));

        // Highlight the prefix first
        highlightedInput.getChildren().add(createText(prefix, Color.GREEN));

        // Retrieves Task List and checks if new task description is repeated
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        String categoryName = matcher.group(CATEGORY_GROUP).replace(CATEGORY_PREFIX, NONE).trim();
        try {
            ArrayList<String> suggestedTasks = generateSuggestedTasks(moduleCode, categoryName, true);
            if (suggestedTasks.contains(taskDescription)) {
                // Repeated task description
                highlightedInput.getChildren().addAll(
                        createText(rawTaskDescription, Color.CRIMSON),
                        createText(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                // Valid new task description
                highlightedInput.getChildren().add(createText(rawTaskDescription, Color.BLUE));
            }
        } catch (IncorrectDirectoryLevelException e) {
            // Unable to get missing module code or category name
            highlightedInput.getChildren().addAll(
                    createText(rawTaskDescription, Color.ORANGE),
                    createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }
    }

    private void checkDuplicateModule(Matcher matcher, String parameters) throws ParseFailureException {
        String moduleGroup = matcher.group(MODULE_GROUP);
        if (moduleGroup.isBlank()) {
            return;
        }

        String moduleCode = moduleGroup.replace(MODULE_PREFIX, NONE).trim().toUpperCase();

        int endIndexOfPrefix = moduleGroup.indexOf(MODULE_PREFIX) + 2;
        String prefix = moduleGroup.substring(0, endIndexOfPrefix);
        String rawModuleName = moduleGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(MODULE_GROUP));

        // Highlight the prefix first
        highlightedInput.getChildren().add(createText(prefix, Color.GREEN));

        // Retrieves Module List and checks if new module code is repeated
        ArrayList<String> suggestedModules = generateSuggestedModules();
        if (suggestedModules.contains(moduleCode)) {
            // Repeated module code
            highlightedInput.getChildren().addAll(
                    createText(rawModuleName, Color.CRIMSON),
                    createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        } else if (!ModuleManager.getModulesMap().containsKey(moduleCode)) {
            // Module code is not an NUS module
            highlightedInput.getChildren().add(createText(rawModuleName, Color.ORANGE));
        } else {
            // Valid module code
            highlightedInput.getChildren().add(createText(rawModuleName, Color.BLUE));
        }
    }

    private void checkDuplicateCategory(Matcher matcher, String parameters) throws ParseFailureException {
        String categoryGroup = matcher.group(CATEGORY_GROUP);
        if (categoryGroup.isBlank()) {
            return;
        }

        String categoryName = categoryGroup.replace(CATEGORY_PREFIX, NONE).trim();

        int endIndexOfPrefix = categoryGroup.indexOf(CATEGORY_PREFIX) + 2;
        String prefix = categoryGroup.substring(0, endIndexOfPrefix);
        String rawCategoryName = categoryGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(CATEGORY_GROUP));

        // Highlight the prefix first
        highlightedInput.getChildren().add(createText(prefix, Color.GREEN));

        // Retrieves Category List and checks if new category name is repeated
        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        try {
            ArrayList<String> suggestedCategories = generateSuggestedCategories(moduleCode, true);
            if (suggestedCategories.contains(categoryName)) {
                // Repeated category name
                highlightedInput.getChildren().addAll(
                        createText(rawCategoryName, Color.CRIMSON),
                        createText(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                // Valid new category name
                highlightedInput.getChildren().add(createText(rawCategoryName, Color.BLUE));
            }
        } catch (IncorrectDirectoryLevelException e) {
            // Unable to get missing module code
            highlightedInput.getChildren().addAll(
                    createText(rawCategoryName, Color.ORANGE),
                    createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }
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
            highlightedInput.getChildren().addAll(createText(rawCategoryName, Color.CRIMSON),
                    createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (console.getCaretPosition() < startIndexOfCategory || console.getCaretPosition() > endIndexOfCategory) {
            console.getEntriesPopup().hide();
        }

        highlightInput(categoryName, rawCategoryName, parametersAfter, endIndexOfCategory,
                suggestedCategories, false);
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
            highlightedInput.getChildren().addAll(createText(rawTaskDescription, Color.CRIMSON),
                    createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (console.getCaretPosition() < startIndexOfTask || console.getCaretPosition() > endIndexOfTask) {
            console.getEntriesPopup().hide();
        }

        highlightInput(taskDescription, rawTaskDescription, parametersAfter, endIndexOfTask, suggestedTasks, false);
    }

    private void smartParseFlag(Matcher matcher, String flagName) {
        String flag = matcher.group(flagName);
        highlightedInput.getChildren().add(createText(flag, Color.GREEN));
    }


    private void smartParseDeadline(Matcher matcher, String parameters) throws ParseFailureException {
        String deadlineGroup = matcher.group(DEADLINE_GROUP);
        if (deadlineGroup.isBlank()) {
            return;
        }

        int endIndexOfPrefix = deadlineGroup.indexOf(DEADLINE_PREFIX) + 2;
        String prefix = deadlineGroup.substring(0, endIndexOfPrefix);
        String rawDeadline = deadlineGroup.substring(endIndexOfPrefix);
        String deadline = rawDeadline.trim();
        String parametersAfter =  parameters.substring(matcher.end(DEADLINE_GROUP));

        highlightedInput.getChildren().add(createText(prefix, Color.GREEN));

        if (isValidDeadline(deadline)) {
            highlightedInput.getChildren().add(createText(rawDeadline, Color.GREEN));
        } else {
            highlightedInput.getChildren().addAll(
                    createText(rawDeadline, Color.CRIMSON),
                    createText(parametersAfter, Color.DARKGRAY)
            );
            throw new ParseFailureException();
        }
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

    private void smartParseCategory(Matcher matcher, String parameters, int startIndex, boolean isExact)
            throws ParseFailureException {
        String categoryGroup = matcher.group(CATEGORY_GROUP);
        if (categoryGroup.isBlank()) {
            return;
        }

        int startIndexOfCategory =  matcher.start(CATEGORY_GROUP) + startIndex;
        int endIndexOfCategory =  matcher.end(CATEGORY_GROUP) + startIndex;

        String categoryName = categoryGroup.replace(CATEGORY_PREFIX, NONE).trim();

        int endIndexOfPrefix = categoryGroup.indexOf(CATEGORY_PREFIX) + 2;
        String prefix = categoryGroup.substring(0, endIndexOfPrefix);
        String rawCategoryName = categoryGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(CATEGORY_GROUP));

        highlightedInput.getChildren().add(createText(prefix, Color.GREEN));

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();
        ArrayList<String> suggestedCategories;
        try {
            suggestedCategories = generateSuggestedCategories(moduleCode, isExact);
            populateSuggestions(categoryName, suggestedCategories, startIndexOfCategory, endIndexOfCategory, CATEGORY_PREFIX);
        } catch (IncorrectDirectoryLevelException e) {
            highlightedInput.getChildren().addAll(createText(rawCategoryName, Color.CRIMSON),
                    createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (console.getCaretPosition() < startIndexOfCategory || console.getCaretPosition() > endIndexOfCategory) {
            console.getEntriesPopup().hide();
        }

        highlightInput(categoryName, rawCategoryName, parametersAfter, endIndexOfCategory,
                suggestedCategories, isExact);
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

        if (console.getCaretPosition() < startIndexOfModule || console.getCaretPosition() > endIndexOfModule) {
            console.getEntriesPopup().hide();
        }

        highlightInput(moduleCode, rawModuleCode, parametersAfter, endIndexOfModule,
                suggestedModules, isExact);
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

        int endIndexOfPrefix = moduleGroup.indexOf(MODULE_PREFIX) + 2;
        String prefix = moduleGroup.substring(0, endIndexOfPrefix);
        String rawModuleCode = moduleGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(MODULE_GROUP));

        highlightedInput.getChildren().add(createText(prefix, Color.GREEN));

        ArrayList<String> suggestedModules = generateSuggestedModules();
        populateSuggestions(moduleCode, suggestedModules, startIndexOfModule, endIndexOfModule, MODULE_PREFIX);

        if (console.getCaretPosition() < startIndexOfModule || console.getCaretPosition() > endIndexOfModule) {
            console.getEntriesPopup().hide();
        }

        highlightInput(moduleCode, rawModuleCode, parametersAfter, endIndexOfModule, suggestedModules, isExact);
    }

    private void smartParsePriority(Matcher matcher, String parameters) throws ParseFailureException {
        String priorityGroup = matcher.group(PRIORITY_GROUP);
        if (priorityGroup.isBlank()) {
            return;
        }

        int endIndexOfPrefix = priorityGroup.indexOf(PRIORITY_PREFIX) + 2;
        String prefix = priorityGroup.substring(0, endIndexOfPrefix);
        String rawPriority = priorityGroup.substring(endIndexOfPrefix);
        String priority = rawPriority.trim();
        String parametersAfter =  parameters.substring(matcher.end(PRIORITY_GROUP));

        highlightedInput.getChildren().add(createText(prefix, Color.GREEN));

        if (isValidPriority(priority)) {
            highlightedInput.getChildren().add(createText(rawPriority, Color.GREEN));
        } else {
            highlightedInput.getChildren().addAll(
                    createText(rawPriority, Color.CRIMSON),
                    createText(parametersAfter, Color.DARKGRAY)
            );
            throw new ParseFailureException();
        }
    }

    private boolean isValidPriority(String priorityString) {
        try {
            int priority = Integer.parseInt(priorityString);
            return priority >= 0 && priority <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Matcher matchPattern(String parameters, Pattern format)
            throws ParseFailureException {
        final Matcher matcher = format.matcher(parameters);

        if (!matcher.matches()) {
            highlightedInput.getChildren().add(createText(parameters, Color.CRIMSON));
            throw new ParseFailureException();
        }

        return matcher;
    }

    private void highlightInput(String parameter, String rawParameter, String parametersAfter, int endIndex,
            ArrayList<String> suggestions, boolean isExact) throws ParseFailureException {
        if (!isPartOfWord(parameter, suggestions)) {
            // Does not match a suggestion at all
            if (isExact) {
                highlightedInput.getChildren().addAll(
                        createText(rawParameter, Color.CRIMSON),
                        createText(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                highlightedInput.getChildren().add(createText(rawParameter, Color.ORANGE));
            }
            return;
        }

        if (isMatchingWord(parameter, suggestions)) {
            // Completely matches a suggestion
            highlightedInput.getChildren().add(createText(rawParameter, Color.GREEN));
        } else if (console.getCaretPosition() > endIndex) {
            // Partially matches a suggestion but not typing
            if (isExact) {
                highlightedInput.getChildren().addAll(
                        createText(rawParameter, Color.CRIMSON),
                        createText(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            } else {
                highlightedInput.getChildren().add(createText(rawParameter, Color.ORANGE));
            }
        } else {
            // Partially matches a suggestion and still typing
            highlightedInput.getChildren().add(createText(rawParameter, Color.DARKGRAY));
            if (isExact) {
                highlightedInput.getChildren().add(createText(parametersAfter, Color.DARKGRAY));
                throw new ParseFailureException();
            }
        }
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

    private void populateSuggestions(String keyword, ArrayList<String> suggestions,
                                     int startIndex, int endIndex, String prefix) {
        console.getEntries().clear();
        console.getEntries().addAll(suggestions);
        console.setEnteredText(keyword, startIndex, endIndex, prefix);
        console.displaySuggestions();
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
}
