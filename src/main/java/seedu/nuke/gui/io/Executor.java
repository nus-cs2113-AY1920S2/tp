package seedu.nuke.gui.io;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.promptcommand.PromptType;
import seedu.nuke.common.DataType;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.gui.controller.MainController;
import seedu.nuke.gui.ui.TextUI;
import seedu.nuke.parser.Parser;
import seedu.nuke.util.ListCreator;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.nuke.util.Message.MESSAGE_PROMPT_FORMAT;

public class Executor {
    /* Variables to transit between parsing regular commands and prompts */
    private static PromptType promptType = PromptType.NONE;
    private static String userMessage;
    private static DataType dataType = DataType.NONE;
    private static ArrayList<?> filteredList;
    private static ArrayList<Integer> indices;

    private TextField console;
    private TextFlow consoleScreen;

    public Executor(TextField console, TextFlow consoleScreen) {
        this.console = console;
        this.consoleScreen = consoleScreen;
    }

    public static void preparePromptIndices() {
        promptType = PromptType.INDICES;
    }

    public static void preparePromptConfirmation() {
        promptType = PromptType.CONFIRMATION;
    }

    public static void terminatePrompt() {
        promptType = PromptType.NONE;
    }

    public static ArrayList<Integer> getIndices() {
        return indices;
    }

    public static void setIndices(ArrayList<Integer> indices) {
        Executor.indices = indices;
    }

    public static DataType getDataType() {
        return dataType;
    }

    public static ArrayList<?> getFilteredList() {
        return filteredList;
    }

    public static void setFilteredList(ArrayList<?> filteredList, DataType dataType) {
        Executor.filteredList = filteredList;
        Executor.dataType = dataType;
    }

    public void executeAction(String userInput) {
        Command command;
        switch (promptType) {
            case CONFIRMATION:
                command = new Parser().parseInputAsConfirmation(userInput.toLowerCase());
                break;

            case INDICES:
                command = new Parser().parseInputAsIndices(userInput);
                break;

            default:
                command = new Parser().parseCommand(userInput);
                break;
        }

        CommandResult result = command.execute();
        displayResult(result);

        if (ExitCommand.isExit()) {
            Stage window = (Stage) console.getScene().getWindow();
            window.close();
        }
    }

    private void displayResult(CommandResult result) {
        Text feedbackToUser = TextUI.createText(String.format("%s\n\n", result.getFeedbackToUser()), Color.BLUE);
        consoleScreen.getChildren().add(feedbackToUser);

        DirectoryLevel dataType = result.getDirectoryLevel();
        String listTableToShow;
        switch (dataType) {
        case MODULE:
            ArrayList<Module> moduleList = result.getShownList().stream()
                    .map(Module.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            listTableToShow = ListCreator.createModuleListTable(moduleList);
            break;

        case CATEGORY:
            ArrayList<Category> categoryList = result.getShownList().stream()
                    .map(Category.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            listTableToShow = ListCreator.createCategoryListTable(categoryList);
            break;

        case TASK:
            ArrayList<Task> taskList = result.getShownList().stream()
                    .map(Task.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            listTableToShow = ListCreator.createTaskListTable(taskList);
            break;

        default:
            return;
        }

        Text taskListTable = TextUI.createText(String.format("%s\n\n", listTableToShow), Color.BROWN);
        consoleScreen.getChildren().add(taskListTable);
    }
}
