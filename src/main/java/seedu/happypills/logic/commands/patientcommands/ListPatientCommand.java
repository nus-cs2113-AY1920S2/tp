package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.ui.PatientTextUi;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@ itskesin
/**
 * Lists all patients to the user.
 */
public class ListPatientCommand extends PatientCommand {

    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;

    /**
     * Executes the list patient command.
     *
     * @param patients The list of patients.
     * @param appointments The list of appointments.
     * @param patientRecords The list of patient records.
     * @return Error Message or Success Message.
     */
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords) {
        String message;
        if (patients.isEmpty()) {
            message = PatientTextUi.EMPTY_PATIENT_LIST_MESSAGE;
            logger.log(logLevel, "no patient information is in list");
        } else {
            assert !patients.isEmpty();
            message = PatientTextUi.getPatientList(patients);
            logger.log(logLevel, "retrieve patient's information");
        }
        return message;
    }
}
