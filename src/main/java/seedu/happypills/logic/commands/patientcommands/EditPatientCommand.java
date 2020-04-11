package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.parser.Checker;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.StorageTextUi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author sitinadiah25
public class EditPatientCommand extends PatientCommand {
    protected String nric;
    protected String newContent;
    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;

    public static final String REMARKS_TAG = "/rm";
    public static final String DATE_OF_BIRTH_TAG = "/dob";
    public static final String NAME_TAG = "/n";
    public static final String PHONE_NUMBER_TAG = "/p";
    public static final String BLOOD_TYPE_TAG = "/b";
    public static final String ALLERGIES_TAG = "/a";

    /**
     * Constructor for EditCommand Class.
     * Creates a new EditCommand Object with the information provided.
     *
     * @param nric       Contains the nric of the patient that is to be retrieved.
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditPatientCommand(String nric, String newContent) {
        this.nric = nric.toUpperCase().trim();
        this.newContent = newContent.trim();
    }

    /**
     * Retrieves the patient from the NRIC of the Edit command.
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
     * Edits the phone number of the patient.
     *
     * @param patient The patient whose phone number is to be edited.
     * @param content The patient's new phone number.
     */
    private String editPhone(Patient patient, String content) {
        patient.setPhoneNumber(Integer.parseInt(content.trim()));
        String message = PatientTextUi.editPatientSuccessMessage(patient);
        return message;
    }

    /**
     * Edits the allergies of the patient.
     *
     * @param patient The patient whose allergies is to be edited.
     * @param content The patient's updated allergies.
     */
    private String editAllergies(Patient patient, String content) {
        patient.setAllergies(content.trim());
        String message = PatientTextUi.editPatientSuccessMessage(patient);
        return message;
    }

    /**
     * Edits the remarks of the patient.
     *
     * @param patient The patient whose remarks is to be edited.
     * @param content The patient's new remarks.
     */
    private String editRemarks(Patient patient, String content) {
        patient.setRemarks(content.trim());
        String message = PatientTextUi.editPatientSuccessMessage(patient);
        return message;
    }

    /**
     * Edits the Date of Birth of the patient.
     *
     * @param patient The patient whose DOB is to be edited.
     * @param content The patient's new DOB.
     */
    private String editDob(Patient patient, String content) {
        patient.setDateOfBirth(content.trim());
        String message = PatientTextUi.editPatientSuccessMessage(patient);
        return message;
    }

    /**
     * Edits the name of the patient.
     *
     * @param patient The patient whose name is to be edited.
     * @param content The patient's new name.
     */
    private String editName(Patient patient, String content) {
        patient.setName(content.trim());
        String message = PatientTextUi.editPatientSuccessMessage(patient);
        return message;
    }

    /**
     * Edits the blood type of the patient.
     *
     * @param patient The patient whose blood type is to be edited.
     * @param content The patient's new blood type.
     */
    private String editBloodType(Patient patient, String content) {
        patient.setBloodType(content.trim());
        String message = PatientTextUi.editPatientSuccessMessage(patient);
        return message;
    }

    /**
     * Executes the edit patient command.
     *
     * @param patients       The list of patients
     * @param appointments   The list of appointments
     * @param patientRecords The list of patient records
     * @return Error Message or Success Message
     * @throws HappyPillsException if NRIC already exist in the patient list
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {
        if (newContent.length() < 2) {
            throw new HappyPillsException(Messages.MESSAGE_INCOMPLETE_COMMAND);
        }
        // assert newContent.length() >= 2 : "Length of content has to be more than 2 characters.";
        String field = "";
        String content = "";
        if (newContent.contains(REMARKS_TAG)) {
            field = newContent.substring(0, 3);
            content = newContent.substring(3);
        } else if (newContent.contains(DATE_OF_BIRTH_TAG)) {
            field = newContent.substring(0, 4);
            content = newContent.substring(4);
        } else {
            field = newContent.substring(0, 2);
            content = newContent.substring(2);
        }
        Patient editPatient = findPatient(patients);
        if (editPatient == null) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_NOT_FOUND);
        }
        if (content.isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_CONTENT_IS_EMPTY);
        }
        String output = "";
        content = content.trim();
        output = checkTag(field, content, editPatient);
        saveEditedInformation(patients);
        assert output.length() > 0 : "output message is invalid";
        return output;
    }

    /**
     * Checks which field of information the user intends to edit.
     *
     * @param field       The tag given by the user.
     * @param content     The information given by the user.
     * @param editPatient The patient's information.
     * @throws HappyPillsException If the edit field is not valid.
     */
    private String checkTag(String field, String content, Patient editPatient) throws HappyPillsException {
        String output;
        if (field.equals(PHONE_NUMBER_TAG)) {
            if (Checker.isValidPhoneNum(content.trim())) {
                output = editPhone(editPatient, content);
            } else {
                throw new HappyPillsException(Messages.MESSAGE_INVALID_PHONE_NUMBER);
            }
        } else if (field.equals(REMARKS_TAG)) {
            output = editRemarks(editPatient, content.trim());
        } else if (field.equals(ALLERGIES_TAG)) {
            output = editAllergies(editPatient, content.trim());
        } else if (field.equals(DATE_OF_BIRTH_TAG)) {
            if (Checker.isValidDate(content.trim())) {
                output = editDob(editPatient, content.trim());
            } else {
                throw new HappyPillsException(Messages.MESSAGE_INVALID_DATE_OF_BIRTH);
            }
        } else if (field.equals(BLOOD_TYPE_TAG)) {
            if (Checker.isValidBloodType(content.trim())) {
                output = editBloodType(editPatient, content.trim());
            } else {
                throw new HappyPillsException(Messages.MESSAGE_INVALID_BLOOD_TYPE);
            }
            output = editBloodType(editPatient, content.trim());
        } else if (field.equals(NAME_TAG)) {
            output = editName(editPatient, content.trim());
        } else {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_EDIT_PATIENT);
        }
        return output;
    }

    /**
     * Saves the edited patient record details with the information provided by user.
     *
     * @param patients The list of patients on which the commands are executed on.
     */
    private void saveEditedInformation(PatientMap patients) {
        try {
            Storage.writeAllToFile(Storage.PATIENT_FILEPATH,
                    StorageTextUi.getFormattedPatientString(patients));
        } catch (IOException e) {
            logger.info(StorageTextUi.FAIL_TO_WRITE_PATIENT_MSG);
        }
    }
}
