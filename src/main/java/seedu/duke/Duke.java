package seedu.duke;

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
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
