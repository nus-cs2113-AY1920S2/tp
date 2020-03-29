package seedu.happypills.commands.patientcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.PatientMap;
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
        String message = "";
        if (patients.isEmpty()) {
            message = TextUi.getEmptyPatientList();
            logger.log(logLevel, "no patient information is in list");
        } else {
            assert !patients.isEmpty();
            message = TextUi.getPatientList(patients);
            logger.log(logLevel, "retrieve patient's information");
        }
        return message;
    }
}
