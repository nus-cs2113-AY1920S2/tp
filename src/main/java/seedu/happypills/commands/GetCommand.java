package seedu.happypills.commands;

import org.w3c.dom.Text;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.ui.TextUi;

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
    public String execute(PatientList patients) {
        for (Patient patient : patients) {
            if (patient.getNric().equalsIgnoreCase(patientNric)) {
                return TextUi.successfulGetPatientMessage(patient);
            }
        }
        return "The patient you are looking for does not exist";
    }

}
