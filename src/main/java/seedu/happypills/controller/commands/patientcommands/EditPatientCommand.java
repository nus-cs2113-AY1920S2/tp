package seedu.happypills.controller.commands.patientcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.view.ui.TextUi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditPatientCommand extends PatientCommand {
    protected String nric;
    protected String newContent;
    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;

    /**
     * Constructor for EditCommand Class.
     * It creates a new EditCommand Object with the information provided.
     *
     * @param nric Contains the nric of the patient that is to be retrieved.
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditPatientCommand(String nric, String newContent) {
        this.nric = nric;
        this.newContent = newContent;
    }

    /**
     * Retrieve the patient from the NRIC of the Edit command.
     *
     * @param patients Contains the list of patients to be searched.
     */
    private Patient findPatient(PatientMap patients) {
        if (patients.containsKey(nric)) {
            logger.log(logLevel, "patient to be edited is found");
            return patients.get(nric);
        } else {
            return null;
        }
    }

    /**
     * Edit the phone number of the patient.
     *
     * @param patient The patient whose phone number is to be edited.
     * @param content The patient's new phone number.
     */
    private String editPhone(Patient patient, String content) {
        patient.setPhoneNumber(Integer.parseInt(content));
        String message = TextUi.printEditPatientSuccess(patient);
        return message;
    }

    /**
     * Edit the allergies of the patient.
     *
     * @param patient The patient whose allergies is to be edited.
     * @param content The patient's updated allergies.
     */
    private String editAllergies(Patient patient, String content) {
        patient.setAllergies(content);
        String message = TextUi.printEditPatientSuccess(patient);
        return message;
    }

    /**
     * Edit the remarks of the patient.
     *
     * @param patient The patient whose remarks is to be edited.
     * @param content The patient's new remarks.
     */
    private String editRemarks(Patient patient, String content) {
        patient.setRemarks(content);
        String message = TextUi.printEditPatientSuccess(patient);
        return message;
    }

    /**
     * Edit the Date of Birth of the patient.
     *
     * @param patient The patient whose DOB is to be edited.
     * @param content The patient's new DOB.
     */
    private String editDob(Patient patient, String content) {
        patient.setDateOfBirth(content);
        String message = TextUi.printEditPatientSuccess(patient);
        return message;
    }

    /**
     * Edit the name of the patient.
     *
     * @param patient The patient whose name is to be edited.
     * @param content The patient's new name.
     */
    private String editName(Patient patient, String content) {
        patient.setName(content);
        String message = TextUi.printEditPatientSuccess(patient);
        return message;
    }

    /**
     * Edit the blood type of the patient.
     *
     * @param patient The patient whose blood type is to be edited.
     * @param content The patient's new blood type.
     */
    private String editBloodType(Patient patient, String content) {
        patient.setBloodType(content);
        String message = TextUi.printEditPatientSuccess(patient);
        return message;
    }

    /**
     * Adds a new task to the list with the information provided by calling.
     * {} (or) {}
     * (or) {} as require
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @throws HappyPillsException Throws an exception if the edit field is not valid.
     */
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException {
        if (newContent.length() < 2) {
            throw new HappyPillsException("    Content is invalid. Please try again");
        }
        // assert newContent.length() >= 2 : "Length of content has to be more than 2 characters.";
        String field = newContent.substring(0,2);
        String content = newContent.substring(2);
        Patient editPatient = findPatient(patients);
        String output = "";
        if (editPatient == null) {
            throw new HappyPillsException("    Patient not found. Please try again.");
        }
        // assert editPatient != null : "Patient is not in PatientList";
        if (field.equals("/p")) {
            output = editPhone(editPatient, content);
        } else if (field.equals("/r")) {
            output = editRemarks(editPatient, content);
        } else if (field.equals("/a")) {
            output = editAllergies(editPatient, content);
        } else if (field.equals("/d")) {
            output = editDob(editPatient, content);
        } else if (field.equals("/b")) {
            output = editBloodType(editPatient, content);
        } else if (field.equals("/n")) {
            output = editName(editPatient, content);
        } else {
            throw new HappyPillsException("    Please try again. To learn more about the Edit command, "
            + "\n    enter \"help edit\"");
        }
        try {
            Storage.writeAllToFile(Storage.PATIENT_FILEPATH,TextUi.getFormattedPatientString(patients));
        } catch (IOException e) {
            logger.info("Adding patient list to file failed.");
        }
        assert output.length() > 0 : "output message is invalid";
        return output;
    }
}
