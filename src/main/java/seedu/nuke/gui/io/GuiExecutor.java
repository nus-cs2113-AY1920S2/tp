package seedu.nuke.gui.io;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.promptcommand.PromptType;
import seedu.nuke.common.DataType;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.gui.ui.TextUI;
import seedu.nuke.util.ListCreator;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class GuiExecutor {
    private TextField console;
    private TextFlow consoleScreen;

    public GuiExecutor(TextField console, TextFlow consoleScreen) {
        this.console = console;
        this.consoleScreen = consoleScreen;
    }

    public void executeAction(String userInput) {
        CommandResult result = Executor.executeCommand(userInput);
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
