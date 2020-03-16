package seedu.happypills.commands;

import org.w3c.dom.Text;
import seedu.happypills.HappyPills;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends Command {
    protected String nric;
    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;

    public DeleteCommand(String nric) {
        this.nric = nric;
    }

    /**
     * Retrieve the patient from the NRIC of the Delete command.
     *
     * @param patients Contains the list of patients to be searched.
     */
    private Patient findPatient(PatientList patients) {
        for (Patient patient : patients) {
            if (patient.getNric().equalsIgnoreCase(nric)) {
                return patient;
            }
        }
        return null;
    }

    private String getPatientConfirmation() {
        Scanner in = new Scanner(System.in);
        String confirm = in.nextLine();
        return confirm;
    }

    private String deletePatient(Patient patient, PatientList patients) {
        patients.remove(patient);
        String message = "      Patient " + patient.getName() + " || " + patient.getNric() +
                " has been deleted successfully.\n";
        return message;
    }

    public String execute(PatientList patients) throws HappyPillsException {
        Patient delPatient = findPatient(patients);
        if (delPatient == null) {
            throw new HappyPillsException("    Patient does not exist. Please try again.");
        }
        TextUi.printDeleteConfirmation(delPatient);
        String message = "";
        String confirm = getPatientConfirmation();
        boolean isConfirmed = false;
        while (!isConfirmed) {
            if (confirm.equalsIgnoreCase("y")) {
                message = deletePatient(delPatient, patients);
                isConfirmed = true;
                logger.log(logLevel, "patient is deleted");
            } else if (confirm.equalsIgnoreCase("n")) {
                message = "    Patient is not deleted.\n";
                isConfirmed = true;
                logger.log(logLevel, "patient is not deleted");
            } else {
                System.out.println("    Please try again. (y/n)");
                confirm = getPatientConfirmation();
            }
        }
        assert isConfirmed : "Delete is not confirmed.";
        message = TextUi.appendDivider(message);
        return message;
    }
}
