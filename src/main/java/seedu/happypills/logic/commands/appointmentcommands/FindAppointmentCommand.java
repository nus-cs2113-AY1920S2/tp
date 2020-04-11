package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.logic.parser.Checker;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.AppointmentTextUi;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.TextUi;

public class FindAppointmentCommand extends AppointmentCommand {
    protected String patientNric;

    /**
     * Constructor for FindAppointmentCommand Class.
     * It creates a new FindAppointmentCommand Object with the information provided.
     *
     * @param patientNric        Contains the NRIC of the patient that is to be retrieved.
     */
    public FindAppointmentCommand(String patientNric) {
        this.patientNric = patientNric.toUpperCase();
    }

    /**
     * Find appointments for a specific patient.
     * @author janicetyy
     * @param patients Shared map of patients
     * @param appointments Shared map of appointments
     * @param visits Shared map of patient records
     * @return errorMessage or successMessage
     * @throws HappyPillsException If patient not found
     */
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap visits)
            throws HappyPillsException {
        if (!Checker.isValidNric(patientNric)) {
            return TextUi.appendDivider(TextUi.INVALID_NRIC_MESSAGE);
        }
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            return AppointmentTextUi.getAppointmentSuccessMessage(patients.get(patientNric));
        } else {
            throw new HappyPillsException(PatientTextUi.PATIENT_NOT_FOUND_MESSAGE);
        }
    }

}
