package seedu.happypills.commands;

import seedu.happypills.data.PatientList;
import seedu.happypills.ui.TextUi;

/**
 * Lists all patients to the user.
 */
public class ListCommand extends Command {

    @Override
    public void execute(PatientList patients) {
        TextUi.printList(patients);
    }
}
