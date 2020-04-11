package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.parser.Checker;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.HelpTextUi;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientRecordTextUi;
import seedu.happypills.ui.StorageTextUi;
import seedu.happypills.ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

//@@ itskesin
/*
 * Edits patient record in the Patient Record Map.
 */
public class EditPatientRecordCommand extends PatientRecordCommand {

    protected String nric;
    protected String newContent;
    protected int index;
    Logger logger = Logger.getLogger(HappyPills.class.getName());
    public static final String SYMPTOM_TAG = "/sym";
    public static final String DIAGNOSIS_TAG = "/diag";
    public static final String DATE_TAG = "/d";
    public static final String TIME_TAG = "/t";

    /**
     * Constructor for EditPatientRecordCommand Class.
     *
     * @param nric       Nric of the patient that is to be retrieved.
     * @param index      Index for the record of the searched patient.
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditPatientRecordCommand(String nric, int index, String newContent) {
        this.nric = nric.toUpperCase();
        this.index = index - 1;
        this.newContent = newContent;
        logger.info("New Edit Patient Record Command");
    }

    /**
     * Edits the patient record details with the information provided by user.
     *
     * @param patients       Contains the list of patients on which the commands are executed on.
     * @param appointments   Contains the list of appointments on which the commands are executed on.
     * @param patientRecords Contains the list of patient records.
     * @throws HappyPillsException If the edit field is not valid.
     */
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords)
            throws HappyPillsException {
        if (newContent.length() < 4) {
            throw new HappyPillsException(HelpTextUi.EDIT_PATIENT_RECORD_HELP_MESSAGE);
        }
        String content = newContent.substring(2).trim();
        if (content.length() == 0) {
            throw new HappyPillsException(TextUi.DIVIDER + "/n"
                    + HelpTextUi.EDIT_PATIENT_RECORD_HELP_MESSAGE);
        }
        String field;
        if (newContent.contains(SYMPTOM_TAG)) {
            field = newContent.substring(0, 4);
            content = newContent.substring(4);
        } else if (newContent.contains(DIAGNOSIS_TAG)) {
            field = newContent.substring(0, 5);
            content = newContent.substring(5);
        } else {
            field = newContent.substring(0, 2);
            content = newContent.substring(2);
        }
        PatientRecord editPatientRecord = checkInvalidInput(patientRecords, content);
        return updateRecord(patients, patientRecords, content, field, editPatientRecord);
    }

    private String updateRecord(
            PatientMap patients, PatientRecordMap patientRecords, String content,
             String field, PatientRecord editPatientRecord) throws HappyPillsException {
        content = content.trim();
        boolean output;
        String errorMsg = Messages.MESSAGE_EDIT_ERROR + TextUi.NEWLINE;
        if (field.equals(SYMPTOM_TAG)) {
            output = editSymptom(editPatientRecord, content);
        } else if (field.equals(DIAGNOSIS_TAG)) {
            output = editDiagnosis(editPatientRecord, content);
        } else if (field.equals(DATE_TAG)) {
            output = editDate(editPatientRecord, content);
            errorMsg = output ? errorMsg : Messages.MESSAGE_INVALID_DATE;
        } else if (field.equals(TIME_TAG)) {
            output = editTime(editPatientRecord, content);
            errorMsg = output ? errorMsg : Messages.MESSAGE_INVALID_TIME;
        } else {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_EDIT_PR);
        }
        saveEditedInformation(patients, patientRecords, output);
        errorMsg = TextUi.appendDivider(errorMsg);
        String message = PatientRecordTextUi.editPatientRecordSuccessMessage(editPatientRecord);
        return output ? message : errorMsg;
    }

    /**
     * Saves the edited patient record details with the information provided by user.
     *
     * @param patients       The list of patients on which the commands are executed on.
     * @param patientRecords The list of patient records.
     * @param output         The boolean to check whether the patient record is updated.
     */
    private void saveEditedInformation(PatientMap patients, PatientRecordMap patientRecords, boolean output) {
        if (output) {
            try {
                Storage.writeAllToFile(Storage.PATIENT_RECORD_FILEPATH,
                        StorageTextUi.getFormattedPrString(patientRecords, patients));
            } catch (IOException e) {
                logger.info(StorageTextUi.FAIL_TO_WRITE_PR_MSG);
            }
        }
    }

    /**
     * Check for invalid input provided by user.
     *
     * @param patientRecords The list of patient records.
     * @param content        The updated user input.
     * @throws HappyPillsException If the edit field is not valid.
     */
    private PatientRecord checkInvalidInput(PatientRecordMap patientRecords, String content)
            throws HappyPillsException {
        if (!patientRecords.containsKey(nric)) {
            logger.info("Patient Record not found");
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND);
        }
        PatientRecord editPatientRecord = findPatientRecord(nric, index, patientRecords);
        boolean isIndexOutOfBound = patientRecords.get(nric).size() < index || index < 0;
        if (editPatientRecord == null) {
            logger.info("Patient Record not found");
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND);
        }
        if (patientRecords.get(nric) == null) {
            logger.info("No patient in the list");
            throw new HappyPillsException(Messages.MESSAGE_EMPTY_PATIENT);
        } else if (isIndexOutOfBound) {
            logger.info("Patient Record not found with given index");
            throw new HappyPillsException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
        }
        if (content.isEmpty()) {
            logger.info("Patient Record not found with given index");
            throw new HappyPillsException(Messages.MESSAGE_CONTENT_IS_EMPTY);
        }
        return editPatientRecord;
    }

    /**
     * Edits the Diagnosis of the patient record with the newly given diagnosis.
     *
     * @param patientRecord Contains the patient record.
     * @param newDiagnosis  Contains the new Diagnosis given by the user.
     * @return Boolean to indicate whether the Diagnosis is edited.
     */
    private Boolean editDiagnosis(PatientRecord patientRecord, String newDiagnosis) {
        patientRecord.setDiagnosis(newDiagnosis);
        return true;
    }

    /**
     * Edits the symptom of the patient record with the newly given symptom.
     *
     * @param patientRecord Contains the patient record.
     * @param newSymptom    Contains the new symptom given by the user.
     * @return Boolean to indicate whether the symptom is edited.
     */
    private Boolean editSymptom(PatientRecord patientRecord, String newSymptom) {
        patientRecord.setSymptom(newSymptom);
        return true;
    }

    /**
     * Edits the date of the patient record with the newly given date.
     *
     * @param patientRecord The patient record.
     * @param newDate       The new date given by the user.
     * @return Boolean to indicate whether the date is edited.
     */
    private Boolean editDate(PatientRecord patientRecord, String newDate) {
        if (!Checker.isValidDate(newDate)) {
            return false;
        }
        patientRecord.setDate(newDate);
        return true;
    }

    /**
     * Edits the time of the patient record with the newly given time.
     *
     * @param patientRecord The patient record.
     * @param newTime       The new time given by the user.
     * @return Boolean to indicate whether the time is edited.
     */
    private Boolean editTime(PatientRecord patientRecord, String newTime) {
        if (!Checker.isValidTime(newTime)) {
            return false;
        }
        patientRecord.setTime(newTime);
        return true;
    }

    /**
     * Finds patient record with the given NRIC and index.
     *
     * @param nric           The NRIC of patient.
     * @param index          The index of the given user input.
     * @param patientRecords The list of patient records.
     * @return patientRecord If patient record is existing in the patient record list otherwise null.
     */
    private PatientRecord findPatientRecord(String nric, int index, PatientRecordMap patientRecords) {
        ArrayList<PatientRecord> patientRecordlist = patientRecords.get(nric);
        if (patientRecordlist != null && index < patientRecordlist.size()) {
            return patientRecordlist.get(index);
        } else {
            return null;
        }
    }
}
