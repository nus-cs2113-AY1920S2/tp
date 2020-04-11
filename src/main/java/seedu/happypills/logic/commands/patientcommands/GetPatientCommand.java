package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.logic.parser.Checker;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.TextUi;

import java.nio.charset.CharacterCodingException;

//@@author janicetyy
public class GetPatientCommand extends PatientCommand {
    protected String patientNric;

    /**
     * Constructor for GetPatientCommand Class.
     * It creates a new GetPatientCommand Object with the information provided.
     *
     * @param patientNric        Contains the nric of the patient that is to be retrieved.
     */
    public GetPatientCommand(String patientNric) {
        this.patientNric = patientNric;
    }

    /**
     * Get the details of a patient.
     *
     * @param patients Shared map of patients
     * @param appointments Shared map of appointments
     * @param visits Shared map of patient records
     * @return Error Message or Success Message
     * @throws HappyPillsException if patient not found
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits) throws HappyPillsException {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (Checker.isValidNric(patientNric)) {
            return TextUi.INVALID_NRIC_MESSAGE;
        }
        if (patients.containsKey(patientNric)) {
            return PatientTextUi.getPatientSuccessMessage(patients.get(patientNric));
        } else {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_NOT_FOUND);
        }
    }

}
