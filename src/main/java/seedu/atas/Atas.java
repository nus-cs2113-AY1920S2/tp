package seedu.atas;

import command.Command;
import command.CommandResult;
import command.ExitCommand;
import common.Messages;
import exceptions.AtasException;

import java.io.IOException;

public class Atas {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Instantiate Ui and TaskList.
     */
    public Atas() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        try {
            this.taskList = storage.load();
        } catch (AtasException e) {
            ui.showToUser(e.toString());
        } catch (IOException e) {
            ui.showToUser(Messages.NO_SAVE_FILE_MESSAGE);
        }
    }

    /**
     * Starts Duke Process.
     */
    public void run() {
        ui.printWelcomeMessage();
        runLoop();
    }

    /**
     * Run loop until exit command is received.
     */
    public void runLoop() {
        while (!ExitCommand.isExit()) {
            String input = ui.getUserInput();
            Command command = Parser.parseCommand(input);
            CommandResult result = command.execute(taskList, ui);
            ui.showToUser(result.feedbackToUser);
            trySaveTaskList();
            ui.showToUser(Messages.DIVIDER);
        }
    }

    private void trySaveTaskList() {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.showToUser(Messages.SAVE_FAILED_MESSAGE);
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Atas().run();
    }
}
