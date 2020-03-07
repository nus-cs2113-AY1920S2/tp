package seedu.happypills.commands;

import seedu.happypills.data.PatientList;
import seedu.happypills.ui.TextUi;

/**
 * Displays full help instructions for every command.
 */
public class HelpCommand extends Command {

    public void execute(PatientList patients) {
        TextUi.printHelp();
    }
}
