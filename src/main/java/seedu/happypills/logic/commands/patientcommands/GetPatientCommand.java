package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.PatientTextUi;

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

    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits) throws HappyPillsException {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            return PatientTextUi.getPatientSuccessMessage(patients.get(patientNric));
        } else {
            throw new HappyPillsException(PatientTextUi.PATIENT_NOT_FOUND_MESSAGE);
        }
    }

}
