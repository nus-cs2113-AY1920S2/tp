package seedu.happypills.commands;

import seedu.happypills.data.PatientMap;
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
    public String execute(PatientMap patients) {
        TextUi.printExit();
        System.exit(0);
        assert false;
        return null;
    }
}
