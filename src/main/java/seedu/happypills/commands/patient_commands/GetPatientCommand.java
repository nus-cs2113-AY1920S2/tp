package seedu.happypills.commands.patient_commands;

import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

public class GetPatientCommand extends PatientCommand {
    protected String patientNric;

    /**
     * Constructor for RetrieveCommand Class.
     * It creates a new RetrieveCommand Object with the information provided.
     *
     * @param patientNric        Contains the nric of the patient that is to be retrieved.
     */
    public GetPatientCommand(String patientNric) {
        this.patientNric = patientNric;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            return TextUi.getPatientSuccessMessage(patients.get(patientNric));
        } else {
            return TextUi.patientNotExist(patientNric);
        }
    }

}
