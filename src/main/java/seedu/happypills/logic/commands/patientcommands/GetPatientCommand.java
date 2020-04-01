package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.TextUi;

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
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap visits) {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            return PatientTextUi.getPatientSuccessMessage(patients.get(patientNric));
        } else {
            return TextUi.patientNotExist;
        }
    }

}
