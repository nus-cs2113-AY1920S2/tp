package seedu.atas;

import command.Command;
import command.CommandResult;
import command.ExitCommand;
import command.ListCommand;
import common.Messages;
import exceptions.AtasException;
import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

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
        showTodayTasksIfAny();
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

    private void showTodayTasksIfAny() {
        ArrayList<Task> todayTasks = taskList.getTasksByDays(0);
        String todayTasksString = new ListCommand(null).showListTasks(taskList.getTaskArray(), todayTasks);
        if (todayTasks.size() == 0) {
            // show a message more suited for a welcome screen instead of the standard no tasks message
            todayTasksString = Messages.NO_TASKS_TODAY_MESSAGE;
        }
        ui.showToUser(todayTasksString);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Atas().run();
    }
}
