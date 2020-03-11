package seedu.duke;

import command.Command;
import command.CommandResult;
import common.Messages;

public class Duke {
    private Ui ui;
    private TaskList taskList;

    /**
     * Instantiate Ui and TaskList.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    /**
     * Starts Duke Process.
     */
    public void run() {
        ui.printWelcomeMessage();
        runLoop();
        ui.printGoodbyeMessage();
    }

    /**
     * Run loop until exit command is received.
     */
    public void runLoop() {
        while (true) {
            String input = ui.getUserInput();
            if (input.equals("bb")) {
                break;
            }
            Command command = Parser.parseCommand(input);
            CommandResult result = command.execute(taskList, ui);
            ui.showToUser(result.feedbackToUser);
            ui.showToUser(Messages.DIVIDER);
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
