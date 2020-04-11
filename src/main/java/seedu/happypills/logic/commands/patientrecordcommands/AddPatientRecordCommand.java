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
 * Adds patient record into Patient Record Map.
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
     * Creates a new PatientCommand Object with the information provided.
     *
     * @param nric      The patient's NRIC.
     * @param symptom   The patient's symptom.
     * @param diagnosis The patient's diagnosis.
     * @param date      The date of the medical treatment.
     * @param time      The time of the medical treatment.
     */
    public AddPatientRecordCommand(String nric, String symptom, String diagnosis, String date, String time) {
        this.nric = nric;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the add patient record command.
     *
     * @param patients       The list of patients.
     * @param appointments   The list of appointments.
     * @param patientRecords The list of patient records.
     * @return message Error message or success message to be displayed.
     * @throws HappyPillsException If NRIC already exist in the patient list.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {
        if (!patients.containsKey(nric)) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_NOT_FOUND);
        } else {
            PatientRecord patientRecord = new PatientRecord(nric, symptom, diagnosis, date, time);
            patientRecords.add(patientRecord, nric);
            try {
                Storage.addSingleItemToFile(Storage.PATIENT_RECORD_FILEPATH, patientRecord.toSave());
            } catch (IOException e) {
                logger.warning(StorageTextUi.FAIL_TO_ADD_PR_MSG);
            }
            String message = Messages.MESSAGE_PATIENT_RECORD_ADDED;
            return message;
        }
    }
}
