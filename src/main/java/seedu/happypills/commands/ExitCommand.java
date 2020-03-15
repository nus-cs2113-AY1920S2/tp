package seedu.happypills.commands;

import seedu.happypills.data.PatientList;
import seedu.happypills.ui.TextUi;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * Adds a new task to the list with the information provided by calling.
     * {} (or) {}
     * (or) {} as require
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     */
    @Override
    public void execute(PatientList patients) {
        TextUi.printExit();
        System.exit(0);
    }
}
