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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * It creates a new EditCommand Object with the information provided.
     *
     * @param nric       Contains the nric of the patient that is to be retrieved.
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditPatientCommand(String nric, String newContent) {
        this.nric = nric.toUpperCase().trim();
        this.newContent = newContent.trim();
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
        String message = PatientTextUi.editPatientSuccessMessage(patient);
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
        String message = PatientTextUi.editPatientSuccessMessage(patient);
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
        String message = PatientTextUi.editPatientSuccessMessage(patient);
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
        String message = PatientTextUi.editPatientSuccessMessage(patient);
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
        String message = PatientTextUi.editPatientSuccessMessage(patient);
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
        String message = PatientTextUi.editPatientSuccessMessage(patient);
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
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits
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
        String output = "";
        if (editPatient == null) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_NOT_FOUND);
        }
        if (content.isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_CONTENT_IS_EMPTY);
        }
        // assert editPatient != null : "Patient is not in PatientList";
        if (field.equals(PHONE_NUMBER_TAG)) {
            if (checkPhoneNum(content.trim())) {
                output = editPhone(editPatient, content);
            } else {
                throw new HappyPillsException(Messages.MESSAGE_INVALID_PHONE_NUMBER);
            }
        } else if (field.equals(REMARKS_TAG)) {
            output = editRemarks(editPatient, content.trim());
        } else if (field.equals(ALLERGIES_TAG)) {
            output = editAllergies(editPatient, content.trim());
        } else if (field.equals(DATE_OF_BIRTH_TAG)) {
            if (checkDate(content.trim())) {
                output = editDob(editPatient, content.trim());
            } else {
                throw new HappyPillsException(Messages.MESSAGE_INVALID_DATE_OF_BIRTH);
            }
        } else if (field.equals(BLOOD_TYPE_TAG)) {
            if (checkType(content.trim())) {
                output = editBloodType(editPatient, content.trim());
            } else {
                throw new HappyPillsException("    Please ensure that the DATE is in [A|B|AB|O][+-] ");
            }
            output = editBloodType(editPatient, content.trim());
        } else if (field.equals(NAME_TAG)) {
            output = editName(editPatient, content.trim());
        } else {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_TAG);
        }
        try {
            Storage.writeAllToFile(Storage.PATIENT_FILEPATH,
                    StorageTextUi.getFormattedPatientString(patients));
        } catch (IOException e) {
            logger.info(StorageTextUi.failToWritePatientMsg);
        }
        assert output.length() > 0 : "output message is invalid";
        return output;
    }

    /**
     * Check if the String can be converted to Integer.
     *
     * @param input value to check if is integer
     * @return true if is an integer, false otherwise
     */
    public static boolean isInteger(String input) {
        try {
            int x = Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * To check format for nric.
     *
     * @param nric details of nric
     * @return boolean true if the time format is correct otherwise false
     */
    static boolean checkType(String nric) {
        String pattern = "([A|B|AB|O][+-])";
        return nric.matches(pattern);
    }

    /**
     * To check format for phone.
     *
     * @param phoneNum details of time
     * @return boolean true if the time format is correct otherwise false
     */
    static boolean checkPhoneNum(String phoneNum) {
        String pattern = "([0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])";
        return phoneNum.matches(pattern);
    }

    static boolean checkDate(String date) {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean flag = false;
        if (date.matches(pattern)) {
            flag = true;
        }
        return flag;
    }

}
