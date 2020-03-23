package seedu.happypills.commands;

import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.data.PatientMap;
import seedu.happypills.ui.TextUi;

import java.util.Map;

public class GetCommand extends Command {
    protected String patientNric;

    /**
     * Constructor for RetrieveCommand Class.
     * It creates a new RetrieveCommand Object with the information provided.
     *
     * @param patientNric        Contains the nric of the patient that is to be retrieved.
     */
    public GetCommand(String patientNric) {
        this.patientNric = patientNric;
    }

    @Override
    public String execute(PatientMap patients) {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            return TextUi.getPatientSuccessMessage(patients.get(patientNric));
        } else {
            return TextUi.patientNotExist(patientNric);
        }
    }

}
