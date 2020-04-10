package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.StorageTextUi;
import seedu.happypills.ui.TextUi;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeletePatientCommand extends PatientCommand {
    protected String nric;
    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;

    /**
     * Constructor for Delete Patient Command Class.
     *
     * @param nric Contains the nric of the patient that is to be retrieved.
     */
    public DeletePatientCommand(String nric) {
        this.nric = nric.toUpperCase();
    }

    /**
     * Retrieve the patient's confirmation.
     *
     * @return y to confirm deletion, n to stop deletion.
     */
    private String getPatientConfirmation() {
        Scanner in = HappyPills.scanner;
        String confirm = in.nextLine();
        return confirm;
    }

    /**
     * Remove the patient from the patient list in the program.
     *
     * @param patient  The patient to be deleted.
     * @param patients The patient list within the program.
     * @return Message to inform the user that the patient has been deleted.
     */
    private String deletePatient(Patient patient, PatientMap patients) {
        patients.remove(nric);
        String message = PatientTextUi.deletePatientSuccessMessage(patient);
        return TextUi.prependDivider(message);
    }

    /**
     * Run the delete command.
     *
     * @param patients       Contains the list of tasks on which the commands are executed on.
     * @param appointments   The list of appointments
     * @param patientRecords The list of patient records
     * @return The message to confirm deletion of patient or to confirm that the patient has not be deleted.
     * @throws HappyPillsException Throws an exception if patient does not exist.
     */
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {

        if (patients.containsKey(nric)) {
            Patient patient = patients.get(nric);
            PatientTextUi.printDeleteConfirmation(patient);
            String message = "";
            String confirm = getPatientConfirmation();
            boolean isConfirmed = false;
            while (!isConfirmed) {
                if (confirm.equalsIgnoreCase("y")) {
                    message = deletePatient(patient, patients);
                    try {
                        Storage.writeAllToFile(Storage.PATIENT_FILEPATH,
                                StorageTextUi.getFormattedPatientString(patients));
                    } catch (IOException e) {
                        logger.info(StorageTextUi.FAIL_TO_WRITE_PATIENT_MSG);
                    }
                    isConfirmed = true;
                    logger.log(logLevel, "patient is deleted");
                } else if (confirm.equalsIgnoreCase("n")) {
                    message = PatientTextUi.patientNotDeletedMessage;
                    isConfirmed = true;
                    logger.log(logLevel, PatientTextUi.patientNotDeletedMessage);
                } else {
                    PatientTextUi.printDeleteConfirmationAgain(patient);
                    confirm = getPatientConfirmation();
                }
            }
            assert isConfirmed : "Delete is not confirmed.";
            message = TextUi.appendDivider(message);
            return message;

        } else {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_NOT_FOUND);
        }
    }
}
