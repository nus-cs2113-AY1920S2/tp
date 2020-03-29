package seedu.happypills.commands.patient_commands;

import seedu.happypills.HappyPills;
import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lists all patients to the user.
 */
public class ListPatientCommand extends PatientCommand {

    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) {
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
