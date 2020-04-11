package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.PatientRecordTextUi;
import seedu.happypills.ui.StorageTextUi;
import seedu.happypills.ui.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

//@@ itskesin
/*
 * Deletes patient record from Patient Record Map.
 */
public class DeletePatientRecordCommand extends PatientRecordCommand {
    protected String nric;
    protected int index;

    Logger logger = Logger.getLogger(HappyPills.class.getName());

    /**
     * Constructor for Delete Patient Record Command Class.
     *
     * @param nric The NRIC of the patient that is to be retrieved.
     * @param index       The index for the record of the searched patient.
     */
    public DeletePatientRecordCommand(String nric, int index) {
        this.nric = nric;
        this.index = index - 1;
    }

    /**
     * Removes the patient record from the patient record map in the program.
     *
     * @param patientRecordMap The NRIC of the patient that is to be retrieved.
     * @param patientRecords   The list of patient records for given NRIC.
     */
    private void deletePatientRecord(PatientRecordMap patientRecordMap, ArrayList<PatientRecord> patientRecords) {
        patientRecordMap.removePersonalRecord(patientRecords, nric);
        patientRecords.remove(index);
    }

    /**
     * Runs the delete command.
     *
     * @param patients       The list of patients.
     * @param appointments   The list of appointments.
     * @param patientRecordMap The list of patient records.
     * @return message The message to confirm deletion of patient or to confirm that the patient has not be deleted.
     * @throws HappyPillsException If the patient does not exist.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecordMap
    ) throws HappyPillsException {
        if (!patientRecordMap.containsKey(nric)) {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND);
        }
        boolean isIndexOutOfBound = patientRecordMap.get(nric).size() <= index && index > 0;
        assert !nric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(nric)) {
            if (patientRecordMap.get(nric) == null || patientRecordMap.get(nric).isEmpty()) {
                throw new HappyPillsException(Messages.MESSAGE_EMPTY_PATIENT_RECORD);
            } else if (isIndexOutOfBound) {
                throw new HappyPillsException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
            }
            ArrayList<PatientRecord> patientRecords = patientRecordMap.get(nric);
            PatientRecord patientRecord = patientRecords.get(index);
            deletePatientRecord(patientRecordMap, patientRecords);
            try {
                Storage.writeAllToFile(Storage.PATIENT_RECORD_FILEPATH,
                        StorageTextUi.getFormattedPrString(patientRecordMap, patients));
            } catch (IOException e) {
                logger.info(StorageTextUi.FAIL_TO_WRITE_PR_MSG);
            }
            String message = PatientRecordTextUi.deletePatientRecordSuccessMessage(patientRecord, nric);
            return message;

        } else {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND);
        }
    }
}
