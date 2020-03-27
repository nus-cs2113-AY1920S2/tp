package seedu.nuke.gui.io;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import seedu.nuke.command.ChangeDirectoryCommand;
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
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.IncorrectDirectoryLevelException;
import seedu.nuke.exception.ParseFailureException;
import seedu.nuke.gui.component.AutoCompleteTextField;
import seedu.nuke.gui.ui.TextUI;
import seedu.nuke.util.DateTime;
import seedu.nuke.util.DateTimeFormat;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;

public class GuiParser {
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\s*\\w+)(?<parameters>.*)");
    // private static final Pattern GENERAL_COMMAND_FORMAT =
    private static final String WHITESPACES = "\\s+";
    private static final String PARAMETER_SPLITTER = " ";
    private static final int PARSE_FAILURE = -1;

    public static final String MODULE_CODE_PREFIX = "-m";
    public static final String CATEGORY_NAME_PREFIX = "-c";
    public static final String TASK_DESCRIPTION_PREFIX = "-t";
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
    private static final String DEADLINE_GROUP = "deadline";
    private static final String PRIORITY_GROUP = "priority";
    private static final String EXACT_GROUP = "exact";
    private static final String ALL_GROUP = "all";
    private static final String OPTIONAL_GROUP = "optional";
    private static final String INVALID_GROUP = "invalid";

    private AutoCompleteTextField textField;

    public GuiParser(AutoCompleteTextField textField) {
        this.textField = textField;
    }

    private static final String[] COMMAND_WORDS = {
            AddModuleCommand.COMMAND_WORD, AddCategoryCommand.COMMAND_WORD, AddTaskCommand.COMMAND_WORD,
            DeleteModuleCommand.COMMAND_WORD, DeleteCategoryCommand.COMMAND_WORD, DeleteTaskCommand.COMMAND_WORD,
            ListModuleCommand.COMMAND_WORD, ListCategoryCommand.COMMAND_WORD, ListTaskCommand.COMMAND_WORD,
            EditModuleCommand.COMMAND_WORD, EditCategoryCommand.COMMAND_WORD, EditTaskCommand.COMMAND_WORD,
            ChangeDirectoryCommand.COMMAND_WORD
    };
    

    public TextFlow smartParse(String input) {
        TextFlow highlightedInput = new TextFlow();

        try {
            Matcher matcher = smartParseCommandWord(input, highlightedInput);
            smartParseParameters(matcher, highlightedInput);
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
    private Matcher smartParseCommandWord(String input, TextFlow highlightedInput) throws ParseFailureException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input);
        if (!matcher.matches()) {
            highlightedInput.getChildren().add(TextUI.createText(input, Color.CRIMSON));
            throw new ParseFailureException();
        }

        String commandWord = matcher.group(COMMAND_WORD_GROUP).toLowerCase().trim();
        String parameters = matcher.group(PARAMETERS_GROUP);

        int startIndexOfCommandWord = matcher.start(COMMAND_WORD_GROUP);
        int endIndexOfCommandWord = matcher.end(COMMAND_WORD_GROUP);

        populateCommandWordSuggestions(commandWord, parameters, startIndexOfCommandWord, endIndexOfCommandWord);


        if (!isPartOfCommandWord(commandWord)) {
            // Does not match a command word at all
            highlightedInput.getChildren().add(TextUI.createText(input, Color.CRIMSON));
            throw new ParseFailureException();
        }

        if (isMatchingCommandWord(commandWord)) {
            // Completely matches a command word
            String toHighlight = input.substring(startIndexOfCommandWord, endIndexOfCommandWord);
            highlightedInput.getChildren().add(
                    TextUI.createText(toHighlight, Color.GREEN));
            return matcher;
        } else if (!parameters.isEmpty()) {
            // Partially matches a command word BUT not continuing to type
            highlightedInput.getChildren().add(TextUI.createText(input, Color.CRIMSON));
            throw new ParseFailureException();
        } else {
            // Partially matches a command word and continuing to type
            highlightedInput.getChildren().add(TextUI.createText(input, Color.DARKGRAY));
            throw new ParseFailureException();
        }
    }

    private void smartParseParameters(Matcher matcher, TextFlow highlightedInput) throws ParseFailureException {
        String commandWord = matcher.group(COMMAND_WORD_GROUP).toLowerCase();
        String parameters = matcher.group(PARAMETERS_GROUP);
        final int startIndexOfParameters = matcher.start(PARAMETERS_GROUP);

        switch (commandWord) {
        case AddModuleCommand.COMMAND_WORD:
            smartParseAddModuleCommand(parameters, highlightedInput, startIndexOfParameters);
            break;

        case AddCategoryCommand.COMMAND_WORD:
            smartParseAddCategoryCommand(parameters, highlightedInput, startIndexOfParameters);
            break;

        case AddTaskCommand.COMMAND_WORD:
            smartParseAddTaskCommand(parameters, highlightedInput, startIndexOfParameters);
            break;

        //case AddTagCommand.COMMAND_WORD:
        //    return new AddTagCommand(parameters);
        //
        //case DeleteModuleCommand.COMMAND_WORD:
        //    return prepareDeleteAndListModuleCommand(parameters, true);
        //
        //case DeleteCategoryCommand.COMMAND_WORD:
        //    return prepareDeleteAndListCategoryCommand(parameters, true);
        //
        //case DeleteTaskCommand.COMMAND_WORD:
        //    return prepareDeleteAndListTaskCommand(parameters, true);
        //
        //case ListModuleCommand.COMMAND_WORD:
        //    return prepareDeleteAndListModuleCommand(parameters, false);
        //
        //case ListCategoryCommand.COMMAND_WORD:
        //    return prepareDeleteAndListCategoryCommand(parameters, false);
        //
        //case ListTaskCommand.COMMAND_WORD:
        //    return prepareDeleteAndListTaskCommand(parameters, false);
        //
        //case ListAllTasksDeadlineCommand.COMMAND_WORD:
        //    return new ListAllTasksDeadlineCommand();
        //
        //case EditModuleCommand.COMMAND_WORD:
        //    return prepareEditModuleCommand(parameters);
        //
        //case EditCategoryCommand.COMMAND_WORD:
        //    return prepareEditCategoryCommand(parameters);
        //
        //case EditTaskCommand.COMMAND_WORD:
        //    return prepareEditTaskCommand(parameters);
        //
        //case ChangeDirectoryCommand.COMMAND_WORD:
        //    return prepareChangeDirectoryCommand(parameters);
        //
        //case HelpCommand.COMMAND_WORD:
        //    return new HelpCommand();
        //
        //case ExitCommand.COMMAND_WORD:
        //    return new ExitCommand();
        //
        default:
            return;
        }
    }


    private void populateCommandWordSuggestions(String commandWord, String parameters, int startIndex, int endIndex) {
        if (parameters.isEmpty()) {
            // Add the list of command words as possible suggestions
            textField.getEntries().clear();
            textField.getEntries().addAll(Arrays.asList(COMMAND_WORDS));
        } else {
            // Hide current suggestions
            textField.getEntries().clear();
            textField.getEntriesPopup().hide();
        }
        textField.setEnteredText(commandWord, startIndex, endIndex);
        textField.displaySuggestions();
    }

    private boolean isPartOfCommandWord(String givenCommandWord) {
        for (String commandWord : COMMAND_WORDS) {
            if (commandWord.contains(givenCommandWord)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatchingCommandWord(String givenCommandWord) {
        for (String commandWord : COMMAND_WORDS) {
            if (commandWord.equals(givenCommandWord)) {
                return true;
            }
        }
        return false;
    }

    private void smartParseAddModuleCommand(String parameters, TextFlow highlightedInput, int startIndex)
            throws ParseFailureException {
        Pattern format = Pattern.compile(
                "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
                + "(?<invalid>(?:\\s+-.*)*)(?:\\s*)");

        final Matcher matcher = format.matcher(parameters);

        if (!matcher.matches()) {
            highlightedInput.getChildren().add(TextUI.createText(parameters, Color.CRIMSON));
            throw new ParseFailureException();
        }

        String moduleCode = matcher.group(IDENTIFIER_GROUP);
        int startIndexOfModule =  matcher.start(IDENTIFIER_GROUP) + startIndex;
        int endIndexOfModule =  matcher.end(IDENTIFIER_GROUP) + startIndex;

        String invalid = matcher.group(INVALID_GROUP);

        ArrayList<String> suggestedModules = generateSuggestedNewModules();
        populateModuleSuggestions(moduleCode.trim(), suggestedModules, startIndexOfModule, endIndexOfModule, "");

        if (textField.getCaretPosition() > endIndexOfModule) {
            textField.getEntriesPopup().hide();
        }

        if (!isPartOfWord(moduleCode.trim().toUpperCase(), suggestedModules)) {
            // Does not match a command word at all
            highlightedInput.getChildren().addAll(
                    TextUI.createText(moduleCode, Color.CRIMSON),
                    TextUI.createText(invalid, Color.CRIMSON)
            );
            throw new ParseFailureException();
        }

        if (isMatchingWord(moduleCode.trim().toUpperCase(), suggestedModules)) {
            // Completely matches a command word
            highlightedInput.getChildren().addAll(
                    TextUI.createText(moduleCode, Color.GREEN),
                    TextUI.createText(invalid, Color.CRIMSON)
            );
        } else if (textField.getCaretPosition() > endIndexOfModule) {
            highlightedInput.getChildren().addAll(
                    TextUI.createText(moduleCode, Color.ORANGE),
                    TextUI.createText(invalid, Color.CRIMSON)
            );
            throw new ParseFailureException();
        } else {
            // Partially matches a command word
            highlightedInput.getChildren().addAll(
                    TextUI.createText(moduleCode, Color.DARKGRAY),
                    TextUI.createText(invalid, Color.CRIMSON)
            );
            throw new ParseFailureException();
        }
    }

    void smartParseAddCategoryCommand(String parameters, TextFlow highlightedInput, int startIndex)
            throws ParseFailureException {
        Pattern format = Pattern.compile(
                "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
                + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                + "(?<invalid>(?:\\s+-.*)*)(?:\\s*?)");

        final Matcher matcher = format.matcher(parameters);

        if (!matcher.matches()) {
            highlightedInput.getChildren().add(TextUI.createText(parameters, Color.CRIMSON));
            throw new ParseFailureException();
        }

        String categoryName = matcher.group(IDENTIFIER_GROUP);
        String invalid = matcher.group(INVALID_GROUP);

        highlightedInput.getChildren().add(TextUI.createText(categoryName, Color.BLUE));

        smartParseModule(matcher, parameters, startIndex, highlightedInput);

        smartParsePriority(matcher, parameters, highlightedInput);

        highlightedInput.getChildren().add(TextUI.createText(invalid, Color.CRIMSON));

    }

    void smartParseAddTaskCommand(String parameters, TextFlow highlightedInput, int startIndex)
            throws ParseFailureException {
        Pattern format = Pattern.compile(
                "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
                + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                + "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
                + "(?<invalid>(?:\\s+-.*)*)(?:\\s*?)");

        final Matcher matcher = format.matcher(parameters);

        if (!matcher.matches()) {
            highlightedInput.getChildren().add(TextUI.createText(parameters, Color.CRIMSON));
            throw new ParseFailureException();
        }

        String taskDescription = matcher.group(IDENTIFIER_GROUP);
        String invalid = matcher.group(INVALID_GROUP);

        highlightedInput.getChildren().add(TextUI.createText(taskDescription, Color.BLUE));

        smartParseModule(matcher, parameters, startIndex, highlightedInput);
        smartParseCategory(matcher, parameters, startIndex, highlightedInput);
        smartParseDeadline(matcher, parameters, highlightedInput);
         smartParsePriority(matcher, parameters, highlightedInput);

        highlightedInput.getChildren().add(TextUI.createText(invalid, Color.CRIMSON));

    }

    private void smartParseDeadline(Matcher matcher, String parameters, TextFlow highlightedInput)
            throws ParseFailureException {
        String deadlineGroup = matcher.group(DEADLINE_GROUP);
        if (deadlineGroup.isBlank()) {
            return;
        }

        int endIndexOfPrefix = deadlineGroup.indexOf(DEADLINE_PREFIX) + 2;
        String prefix = deadlineGroup.substring(0, endIndexOfPrefix);
        String rawDeadline = deadlineGroup.substring(endIndexOfPrefix);
        String deadline = rawDeadline.trim();
        String parametersAfter =  parameters.substring(matcher.end(DEADLINE_GROUP));

        highlightedInput.getChildren().add(TextUI.createText(prefix, Color.GREEN));

        if (isValidDeadline(deadline)) {
            highlightedInput.getChildren().add(TextUI.createText(rawDeadline, Color.GREEN));
        } else {
            highlightedInput.getChildren().addAll(
                    TextUI.createText(rawDeadline, Color.CRIMSON),
                    TextUI.createText(parametersAfter, Color.DARKGRAY)
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

    private void smartParseCategory(Matcher matcher, String parameters, int startIndex, TextFlow highlightedInput)
            throws ParseFailureException {
        String categoryGroup = matcher.group(CATEGORY_GROUP);
        if (categoryGroup.isBlank()) {
            return;
        }

        int startIndexOfCategory =  matcher.start(CATEGORY_GROUP) + startIndex;
        int endIndexOfCategory =  matcher.end(CATEGORY_GROUP) + startIndex;

        String categoryName = categoryGroup.replace(CATEGORY_NAME_PREFIX, "").trim();

        int endIndexOfPrefix = categoryGroup.indexOf(CATEGORY_NAME_PREFIX) + 2;
        String prefix = categoryGroup.substring(0, endIndexOfPrefix);
        String rawCategoryName = categoryGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(CATEGORY_GROUP));

        highlightedInput.getChildren().add(TextUI.createText(prefix, Color.GREEN));

        String moduleCode = matcher.group(MODULE_GROUP).replace(MODULE_CODE_PREFIX, "").trim();
        ArrayList<String> suggestedCategories;
        try {
            suggestedCategories = generateSuggestedCategories(moduleCode);
            populateModuleSuggestions(categoryName, suggestedCategories, startIndexOfCategory, endIndexOfCategory, CATEGORY_NAME_PREFIX);
        } catch (IncorrectDirectoryLevelException e) {
            highlightedInput.getChildren().addAll(TextUI.createText(rawCategoryName, Color.CRIMSON),
                    TextUI.createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (textField.getCaretPosition() < startIndexOfCategory || textField.getCaretPosition() > endIndexOfCategory) {
            textField.getEntriesPopup().hide();
        }

        if (!isPartOfWord(categoryName, suggestedCategories)) {
            // Does not match a category name at all
            highlightedInput.getChildren().addAll(TextUI.createText(rawCategoryName, Color.ORANGE),
                    TextUI.createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (isMatchingWord(categoryName, suggestedCategories)) {
            // Completely matches a category name
            highlightedInput.getChildren().add(TextUI.createText(rawCategoryName, Color.GREEN));
        } else if (textField.getCaretPosition() > endIndexOfCategory) {
            highlightedInput.getChildren().addAll(
                    TextUI.createText(rawCategoryName, Color.ORANGE),
                    TextUI.createText(parametersAfter, Color.DARKGRAY)
            );
            throw new ParseFailureException();
        } else {
            // Partially matches a category name
            highlightedInput.getChildren().addAll(
                    TextUI.createText(rawCategoryName, Color.DARKGRAY),
                    TextUI.createText(parametersAfter, Color.DARKGRAY)
            );
            throw new ParseFailureException();
        }
    }

    private void smartParseModule(Matcher matcher, String parameters, int startIndex, TextFlow highlightedInput)
            throws ParseFailureException {
        String moduleGroup = matcher.group(MODULE_GROUP);
        if (moduleGroup.isBlank()) {
            return;
        }

        int startIndexOfModule =  matcher.start(MODULE_GROUP) + startIndex;
        int endIndexOfModule =  matcher.end(MODULE_GROUP) + startIndex;

        String moduleCode = moduleGroup.replace(MODULE_CODE_PREFIX, "").trim().toUpperCase();

        int endIndexOfPrefix = moduleGroup.indexOf(MODULE_CODE_PREFIX) + 2;
        String prefix = moduleGroup.substring(0, endIndexOfPrefix);
        String rawModuleCode = moduleGroup.substring(endIndexOfPrefix);
        String parametersAfter =  parameters.substring(matcher.end(MODULE_GROUP));

        highlightedInput.getChildren().add(TextUI.createText(prefix, Color.GREEN));

        ArrayList<String> suggestedModules = generateSuggestedModules();
        populateModuleSuggestions(moduleCode, suggestedModules, startIndexOfModule, endIndexOfModule, MODULE_CODE_PREFIX);

        if (textField.getCaretPosition() < startIndexOfModule || textField.getCaretPosition() > endIndexOfModule) {
            textField.getEntriesPopup().hide();
        }

        if (!isPartOfWord(moduleCode, suggestedModules)) {
            // Does not match a module code at all
            highlightedInput.getChildren().addAll(TextUI.createText(rawModuleCode, Color.CRIMSON),
                    TextUI.createText(parametersAfter, Color.DARKGRAY));
            throw new ParseFailureException();
        }

        if (isMatchingWord(moduleCode, suggestedModules)) {
            // Completely matches a module code
            highlightedInput.getChildren().add(TextUI.createText(rawModuleCode, Color.GREEN));
        } else if (textField.getCaretPosition() > endIndexOfModule) {
            highlightedInput.getChildren().addAll(
                    TextUI.createText(rawModuleCode, Color.ORANGE),
                    TextUI.createText(parametersAfter, Color.DARKGRAY)
            );
            throw new ParseFailureException();
        } else {
            // Partially matches a command word
            highlightedInput.getChildren().addAll(
                    TextUI.createText(rawModuleCode, Color.DARKGRAY),
                    TextUI.createText(parametersAfter, Color.DARKGRAY)
            );
            throw new ParseFailureException();
        }
    }

    private void smartParsePriority(Matcher matcher, String parameters, TextFlow highlightedInput)
            throws ParseFailureException {
        String priorityGroup = matcher.group(PRIORITY_GROUP);
        if (priorityGroup.isBlank()) {
            return;
        }

        int endIndexOfPrefix = priorityGroup.indexOf(PRIORITY_PREFIX) + 2;
        String prefix = priorityGroup.substring(0, endIndexOfPrefix);
        String rawPriority = priorityGroup.substring(endIndexOfPrefix);
        String priority = rawPriority.trim();
        String parametersAfter =  parameters.substring(matcher.end(PRIORITY_GROUP));

        highlightedInput.getChildren().add(TextUI.createText(prefix, Color.GREEN));

        if (isValidPriority(priority)) {
            highlightedInput.getChildren().add(TextUI.createText(rawPriority, Color.GREEN));
        } else {
            highlightedInput.getChildren().addAll(
                    TextUI.createText(rawPriority, Color.CRIMSON),
                    TextUI.createText(parametersAfter, Color.DARKGRAY)
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

    private ArrayList<String> generateSuggestedModules() {
        return ModuleManager.getModuleList().stream()
                .map(Module::getModuleCode)     // Get all the module codes in the Module List
                .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
    }

    private ArrayList<String> generateSuggestedCategories(String moduleCode) throws IncorrectDirectoryLevelException {
        if (moduleCode.isEmpty()) {
            try {
                moduleCode = DirectoryTraverser.getBaseModule().getModuleCode();
            } catch (IncorrectDirectoryLevelException e) {
                throw new IncorrectDirectoryLevelException();
            }
        }
        return ModuleManager.filterExact(moduleCode, "").stream()
                .map(Category::getCategoryName) // Get all the category names in the Category List of the module
                .collect(Collectors.toCollection(ArrayList::new)); // And convert into an Array List
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

    private ArrayList<String> generateSuggestedNewModules() {
        ArrayList<String> moduleSuggestions = new ArrayList<>(ModuleManager.getModulesMap().keySet());
        // Show only suggestions of modules that have not been added yet
        ModuleManager.getModuleList().stream()
                .map(Module::getModuleCode)     // Get module code for current modules
                .forEach(moduleSuggestions::remove);   // Remove module code from set

        return moduleSuggestions;
    }

    private void populateModuleSuggestions(String moduleCode, ArrayList<String> suggestedModules,
                                           int startIndex, int endIndex, String prefix) {
        textField.getEntries().clear();
        textField.getEntries().addAll(suggestedModules);
        textField.setEnteredText(moduleCode, startIndex, endIndex, prefix);
        textField.displaySuggestions();
    }
}
