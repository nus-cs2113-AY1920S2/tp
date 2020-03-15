package seedu.nuke.gui.io;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.data.task.Task;
import seedu.nuke.format.ListCreator;
import seedu.nuke.gui.controller.MainController;
import seedu.nuke.gui.ui.TextUI;
import seedu.nuke.parser.Parser;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_PROMPT_FORMAT;

public class Executor {
    /* Variables to transit between parsing regular commands and prompts */
    private static boolean isPrompt = false;
    private static boolean isGetIndices = false;
    private static boolean isConfirmedPrompt = false;
    private static ArrayList<Integer> indices = new ArrayList<>();


    public boolean promptUser(String promptMessage) throws InterruptedException {
        isPrompt = true;
        displayResult(new CommandResult(promptMessage));
        wait();
        return isConfirmedPrompt;
    }

    public ArrayList<Integer> getIndicesFromUser(String promptMessage) throws InterruptedException {
        isGetIndices = true;
        displayResult(new CommandResult(promptMessage));
        wait();
        return indices;
    }

    public void executeAction(String userInput) {
        TextField console = MainController.console;

        if (isPrompt) {
            executePromptUser(userInput.toLowerCase());
        }

        if (isGetIndices) {
            executeGetIndices(userInput);
        }

        Command command = new Parser().parseInput(userInput);
        CommandResult result = command.execute();
        displayResult(result);

        if (ExitCommand.isExit()) {
            Stage window = (Stage) console.getScene().getWindow();
            window.close();
        }
    }

    @SuppressWarnings("unchecked")
    private static void displayResult(CommandResult result) {
        TextFlow consoleScreen = MainController.consoleScreen;

        Text feedbackToUser = TextUI.createText(String.format("%s\n\n", result.getFeedbackToUser()), Color.BLUE);
        consoleScreen.getChildren().add(feedbackToUser);

        if (result.isShowList()) {
            ArrayList<Task> taskList = (ArrayList<Task>) result.getList();
            Text taskListTable = TextUI.createText(ListCreator.createTaskListTable(taskList), Color.BROWN);
            consoleScreen.getChildren().add(taskListTable);
        }
    }

    private void executePromptUser(String userInput) {
        switch (userInput) {
        case "yes":
        case "y":
            isConfirmedPrompt = true;
            isPrompt = false;
            notifyAll(); // Prompt is over

        case "no":
        case "n":
            isConfirmedPrompt = false;
            isPrompt = false;
            notifyAll(); // Prompt is over

        default:
            displayResult(new CommandResult(MESSAGE_PROMPT_FORMAT));
        }
    }

    private void executeGetIndices(String userInput) {
        indices = new Parser().parseInputAsIndices(userInput);
        isGetIndices = false;
        notifyAll();
    }

}
