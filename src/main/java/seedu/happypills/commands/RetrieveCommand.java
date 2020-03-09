package seedu.happypills.commands;

import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.ui.TextUi;

public class RetrieveCommand extends Command {
    protected String patientNric;

    /**
     * Constructor for RetrieveCommand Class.
     * It creates a new RetrieveCommand Object with the information provided.
     *
     * @param patientNric        Contains the nric of the patient that is to be retrieved.
     */
    public RetrieveCommand(String patientNric) {
        this.patientNric = patientNric;
    }

    @Override
    public void execute(PatientList patients) {
        for (Patient patient : patients) {
            if (patient.getNric().equals(patientNric)) {
                TextUi.printPatient(patient);
            }
        }
    }

}
