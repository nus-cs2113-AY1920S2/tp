package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientRecordTextUi;

//@@ itskesin
/*
 * Finds patient record in the Patient Record Map.
 */
public class FindPatientRecordCommand extends PatientRecordCommand {
    protected String nric;
    protected int index;

    /**
     * Constructor for FindPatientRecordCommand Class.
     *
     * @param nric  The nric of the patient that is to be retrieved.
     * @param index The index for the record of the searched patient.
     */
    public FindPatientRecordCommand(String nric, int index) {
        this.nric = nric;
        this.index = index - 1;
    }

    /**
     * Finds the patient record details with the information provided by user.
     *
     * @param patients       The list of patients on which the commands are executed on.
     * @param appointments   The list of appointments on which the commands are executed on.
     * @param patientRecords The list of patient records.
     * @throws HappyPillsException If the patient record is not found.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {
        if (!patientRecords.containsKey(nric)) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND);
        }
        assert !nric.isEmpty() : "No NRIC was provided";
        boolean isIndexOutOfBound = patientRecords.get(nric).size() <= index && index > 0;
        if (patients.containsKey(nric)) {
            if (patientRecords.get(nric) == null || patientRecords.get(nric).isEmpty()) {
                throw new HappyPillsException(Messages.MESSAGE_EMPTY_PATIENT_RECORD);
            } else if (isIndexOutOfBound) {
                throw new HappyPillsException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
            }
            String message = PatientRecordTextUi.getPatientRecordSuccessMessage(patientRecords, nric, index);
            return message;
        } else {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND);
        }
    }
}
