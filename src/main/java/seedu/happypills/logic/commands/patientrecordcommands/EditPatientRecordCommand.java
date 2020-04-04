package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.parser.Validation;
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
     * It creates a new EditPatientRecordCommand Object with the information provided.
     *
     * @param nric       Contains the nric of the patient that is to be retrieved.
     * @param index index for the record of the searched patient
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditPatientRecordCommand(String nric, int index, String newContent) {
        this.nric = nric.toUpperCase();
        this.index = index - 1;
        this.newContent = newContent;
    }

    /**
     * Edit the appointment details with the information provided by calling.
     *
     * @param patients     Contains the list of patients on which the commands are executed on.
     * @param appointments Contains the list of appointments on which the commands are executed on.
     * @throws HappyPillsException Throws an exception if the edit field is not valid.
     */
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords)
            throws HappyPillsException {
        if (newContent.length() < 3) {
            return HelpTextUi.editPatientRecordHelpMessage;
        }
        String content = newContent.substring(2).trim();
        if (content.length() == 0) {
            return HelpTextUi.editPatientRecordHelpMessage;
        }
        String field = "";
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
        PatientRecord editPatientRecord = findPatientRecord(nric, index, patientRecords);
        boolean isIndexOutOfBound = patientRecords.get(nric).size() <= index && index > 0;
        if (editPatientRecord == null) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND);
        }
        if (patientRecords.get(nric) == null) {
            throw new HappyPillsException(Messages.MESSAGE_EMPTY_PATIENT);
        } else if (isIndexOutOfBound) {
            throw new HappyPillsException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
        }
        if (content.isEmpty()) {
            throw new HappyPillsException(Messages.MESSAGE_CONTENT_IS_EMPTY);
        }
        boolean output = false;
        String errorMsg = Messages.MESSAGE_EDIT_ERROR + TextUi.NEWLINE;
        if (field.equals(SYMPTOM_TAG)) {
            output = editSymptoms(editPatientRecord, content);
        } else if (field.equals(DIAGNOSIS_TAG)) {
            output = editDiagnosis(editPatientRecord, content);
        } else if (field.equals(DATE_TAG)) {
            output = editDate(editPatientRecord, content);
            errorMsg = output ? errorMsg : Messages.MESSAGE_INVALID_DATE;
        } else if (field.equals(TIME_TAG)) {
            output = editTime(editPatientRecord, content);
            errorMsg = output ? errorMsg : Messages.MESSAGE_INVALID_TIME;
        } else {
            throw new HappyPillsException(Messages.MESSAGE_INVALID_TAG);
        }
        if (output) {
            try {
                Storage.writeAllToFile(Storage.APPOINTMENT_FILEPATH,
                        StorageTextUi.getFormattedApptString(appointments));
            } catch (IOException e) {
                logger.info("Adding patient list to file failed.");
            }
        }
        errorMsg = TextUi.appendDivider(errorMsg);
        return output ? PatientRecordTextUi.editPatientRecordSuccessMessage(editPatientRecord) : errorMsg;
    }

    private Boolean editDiagnosis(PatientRecord editPatientRecord, String content) {
        editPatientRecord.setDiagnosis(content);
        return true;
    }

    private Boolean editSymptoms(PatientRecord editPatientRecord, String content) {
        editPatientRecord.setSymptom(content);
        return true;
    }

    /**
     * Edit the date of the appointment in the list within the patient object.
     *
     * @param patientRecord Contains the patient that to get appointment from.
     * @param newDate       The new date to be edited into.
     * @return the appointment with the specified apptID or null if not found
     */
    private Boolean editDate(PatientRecord patientRecord, String newDate) {
        if (!Validation.isValidDate(newDate)) {
            return false;
        }
        patientRecord.setDate(newDate);
        return true;
    }

    /**
     * Edit the time of the appointment in the shared appointment map.
     *
     * @param patientRecord Contains the patient that to get appointment from.
     * @param newTime       The new time to be edited into.
     * @return the appointment with the specified apptID or null if not found.
     */
    private Boolean editTime(PatientRecord patientRecord, String newTime) {
        if (!Validation.isValidTime(newTime)) {
            return false;
        }
        patientRecord.setTime(newTime);
        return true;
    }

    private PatientRecord findPatientRecord(String nric, int index, PatientRecordMap patientRecords) {
        ArrayList<PatientRecord> patientRecordlist = patientRecords.get(nric);
        if (patientRecordlist != null) {
            PatientRecord patientRecord = patientRecordlist.get(index);
            return patientRecord;
        } else {
            return null;
        }
    }
}
