package seedu.happypills.commands;

import seedu.happypills.data.PatientList;
import seedu.happypills.ui.TextUi;

/**
 * Lists all patients to the user.
 */
public class ListCommand extends Command {

    @Override
    public String execute(PatientList patients) {
        String message;
        if (patients.size() == 0) {
            message = TextUi.getEmptyList();
        } else {
            message = TextUi.getList(patients);
        }
        return message;
    }
}
