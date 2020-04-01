package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.TextUi;

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
     * @param nric       Contains the nric of the patient that is to be retrieved.
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditPatientCommand(String nric, String newContent) {
        this.nric = nric.toUpperCase();
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
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits
    ) throws HappyPillsException {
        if (newContent.length() < 2) {
            throw new HappyPillsException("    Content is invalid. Please try again");
        }
        // assert newContent.length() >= 2 : "Length of content has to be more than 2 characters.";
        String field = "";
        String content = "";
        if (newContent.contains("/rm")) {
            field = newContent.substring(0, 3);
            content = newContent.substring(3);
        } else if (newContent.contains("/dob")) {
            field = newContent.substring(0, 4);
            content = newContent.substring(4);
        } else {
            field = newContent.substring(0, 2);
            content = newContent.substring(2);
        }
        Patient editPatient = findPatient(patients);
        String output = "";
        if (editPatient == null) {
            throw new HappyPillsException("    Patient not found. Please try again.");
        }
        if (content.isEmpty()) {
            throw new HappyPillsException("    Please do not leave the field as empty string");
        }
        // assert editPatient != null : "Patient is not in PatientList";
        if (field.equals("/p")) {
            if (checkPhoneNum(content)) {
                output = editPhone(editPatient, content);
            } else {
                throw new HappyPillsException("    Please ensure that all the phone number is 8 digit");
            }
        } else if (field.equals("/rm")) {
            output = editRemarks(editPatient, content);
        } else if (field.equals("/a")) {
            output = editAllergies(editPatient, content);
        } else if (field.equals("/dob")) {
            if (checkDate(content)) {
                output = editDob(editPatient, content);
            } else {
                throw new HappyPillsException("    Please ensure that the DATE is in DD/MM/YYYY ");
            }
        } else if (field.equals("/b")) {
            if (checkType(content)) {
                output = editBloodType(editPatient, content);
            } else {
                throw new HappyPillsException("    Please ensure that the DATE is in [A|B|AB|O][+-] ");
            }
            output = editBloodType(editPatient, content);
        } else if (field.equals("/n")) {
            output = editName(editPatient, content);
        } else {
            throw new HappyPillsException("    Please try again. To learn more about the Edit command, "
                    + "\n    enter \"help edit\"");
        }
        try {
            Storage.writeAllToFile(Storage.PATIENT_FILEPATH, TextUi.getFormattedPatientString(patients));
        } catch (IOException e) {
            logger.info("Adding patient list to file failed.");
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
