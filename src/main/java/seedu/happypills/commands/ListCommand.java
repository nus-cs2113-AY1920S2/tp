package seedu.happypills.commands;

import seedu.happypills.HappyPills;
import seedu.happypills.data.PatientMap;
import seedu.happypills.ui.TextUi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lists all patients to the user.
 */
public class ListCommand extends Command {

    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;

    @Override
    public String execute(PatientMap patients) {
        String message;
        if (patients.size() == 0) {
            message = TextUi.getEmptyList();
            logger.log(logLevel, "no patient information is in list");
        } else {
            assert !patients.isEmpty();
            message = TextUi.getList(patients);
            logger.log(logLevel, "retrieve patient's information");
        }
        return message;
    }
}
