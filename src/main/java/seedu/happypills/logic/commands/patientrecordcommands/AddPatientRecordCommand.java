package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.StorageTextUi;

import java.io.IOException;
import java.util.logging.Logger;

//@@ NyanWunPaing
/*
 * Add patient record into Patient Record Map.
 */
public class AddPatientRecordCommand extends PatientRecordCommand {

    protected String nric;
    protected String symptom;
    protected String diagnosis;
    protected String date;
    protected String time;

    Logger logger = Logger.getLogger(HappyPills.class.getName());

    /**
     * Constructor for PatientRecordCommand Class.
     * It creates a new PatientCommand Object with the information provided.
     *
     * @param nric      patient's NRIC
     * @param symptom   patient's symptom
     * @param diagnosis patient's diagnosis
     * @param date      date of the medical treatment
     * @param time      time of the medical treatment
     */
    public AddPatientRecordCommand(String nric, String symptom, String diagnosis, String date, String time) {
        this.nric = nric;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.date = date;
        this.time = time;
    }

    /**
     * Execute the add patient record command.
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
        String message;
        if (!patients.containsKey(nric)) {
            message = Messages.MESSAGE_PATIENT_NOT_FOUND;
        } else {
            PatientRecord patientRecord = new PatientRecord(nric, symptom, diagnosis, date, time);
            patientRecords.addPersonalRecord(patientRecord, nric);
            try {
                Storage.addSingleItemToFile(Storage.PATIENT_RECORD_FILEPATH, patientRecord.toSave());
            } catch (IOException e) {
                logger.warning(StorageTextUi.FAIL_TO_ADD_PR_MSG);
            }
            message = Messages.MESSAGE_PATIENT_RECORD_ADDED;
        }
        return message;
    }
}
