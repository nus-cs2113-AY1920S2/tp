package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.TextUi;

public class AddPatientRecordCommand extends PatientRecordCommand {

    protected String nric;
    protected String symptom;
    protected String diagnosis;
    protected String date;
    protected String time;

    /**
     * Constructor for PatientRecordCommand Class.
     * It creates a new PatientCommand Object with the information provided.
     *
     * @param nric patient's nric.
     * @param symptom patient's symptom.
     * @param diagnosis patient's diagnosis.
     * @param date date of record.
     * @param time time of record.
     */
    public AddPatientRecordCommand(String nric, String symptom, String diagnosis, String date, String time) {
        this.nric = nric;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.date = date;
        this.time = time;
    }

    @Override
    public String execute(
            PatientMap patients,AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {
        String message = "";
        if (!patients.containsKey(nric)) {
            message = Messages.MESSAGE_PATIENT_NOT_FOUND;
        } else {
            PatientRecord patientRecord = new PatientRecord(nric,symptom,diagnosis,date,time);
            patientRecords.addPersonalRecord(patientRecord, nric);
            message = Messages.MESSAGE_PATIENT_RECORD_ADDED;
        }
        return message;
    }
}
