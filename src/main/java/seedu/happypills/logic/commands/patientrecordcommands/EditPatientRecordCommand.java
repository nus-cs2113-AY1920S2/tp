package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.HelpTextUi;
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

    /**
     * Constructor for EditPatientRecordCommand Class.
     * It creates a new EditPatientRecordCommand Object with the information provided.
     *
     * @param nric       Contains the nric of the patient that is to be retrieved.
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
     * @param patients Contains the list of patients on which the commands are executed on.
     * @param appointments Contains the list of appointments on which the commands are executed on.
     * @throws HappyPillsException Throws an exception if the edit field is not valid.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {
        if (newContent.length() < 3) {
            return HelpTextUi.editPatientRecordHelpMessage;
        }
        String content = newContent.substring(2).trim();
        if (content.length() == 0) {
            return HelpTextUi.editPatientRecordHelpMessage;
        }
        String field = "";
        if (newContent.contains("/sym")) {
            field = newContent.substring(0, 4);
            content = newContent.substring(4);
        } else if (newContent.contains("/diag")) {
            field = newContent.substring(0, 5);
            content = newContent.substring(5);
        } else {
            field = newContent.substring(0, 2);
            content = newContent.substring(2);
        }
        PatientRecord editPatientRecord = findPatientRecord(nric, index, patientRecords);
        if (editPatientRecord == null) {
            throw new HappyPillsException("    Patient record not found. Please try again.");
        }
        if (patientRecords.get(nric) == null) {
            throw new HappyPillsException(PatientRecordTextUi.emptyPatientRecordMessage);
        } else if (patientRecords.get(nric).size() <= index) {
            throw new HappyPillsException(PatientRecordTextUi.getEmptyPatientRecordList);
        }
        Boolean output = false;
        String errorMsg = "    Something went wrong, the edit could not be made.\n";
        if (field.equals("/sym")) {
            output = editSymptoms(editPatientRecord, content);
        } else if (field.equals("/diag")) {
            output = editDiagnosis(editPatientRecord, content);
            errorMsg = output ? errorMsg : "    Invalid time or time format(HH:MM).\n";
        } else if (field.equals("/d")) {
            output = editDate(editPatientRecord, content);
            errorMsg = output ? errorMsg : "    Invalid date or date format(DD/MM/YYYY).\n";
        } else if (field.equals("/t")) {
            output = editTime(editPatientRecord, content);
            errorMsg = output ? errorMsg : "    Invalid time or time format(HH:MM).\n";
        } else {
            throw new HappyPillsException("    Please try again. To learn more about the Edit appointment command, "
                    + "\n    enter \"help appt edit\"");
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
     * check string if fits date format.
     * @param date date in String type
     * @return true if correct date format, false otherwise
     */
    static boolean checkDate(String date) {
        String pattern = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((2[0-9])[0-9]{2})";
        return date.matches(pattern);
    }

    /**
     * Edit the date of the appointment in the list within the patient object.
     *
     * @param patientRecord Contains the patient that to get appointment from.
     * @param newDate The new date to be edited into.
     * @return the appointment with the specified apptID or null if not found
     */
    private Boolean editDate(PatientRecord patientRecord, String newDate) {
        if (!checkDate(newDate)) {
            return false;
        }
        patientRecord.setDate(newDate);
        return true;
    }

    /**
     * check string if fits time format.
     * @param time time in String type.
     * @return true if correct date format, false otherwise.
     */
    static boolean checkTime(String time) {
        String pattern = "([01][0-9]|2[0-3]):([0-5][0-9])";
        return time.matches(pattern);
    }

    /**
     * Edit the time of the appointment in the shared appointment map.
     *
     * @param patientRecord Contains the patient that to get appointment from.
     * @param newTime The new time to be edited into.
     * @return the appointment with the specified apptID or null if not found.
     */
    private Boolean editTime(PatientRecord patientRecord, String newTime) {
        if (!checkTime(newTime)) {
            return false;
        }
        newTime += ":00";
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
