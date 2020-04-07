package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.TextUi;
import seedu.happypills.ui.PatientRecordTextUi;
import seedu.happypills.ui.StorageTextUi;
import seedu.happypills.ui.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DeletePatientRecordCommand extends PatientRecordCommand {
    protected String patientNric;
    protected int index;

    Logger logger = Logger.getLogger(HappyPills.class.getName());

    /**
     * Constructor for FindAppointmentCommand Class.
     * It creates a new FindAppointmentCommand Object with the information provided.
     *
     * @param patientNric Contains the NRIC of the patient that is to be retrieved.
     * @param index index for the record of the searched patient
     */
    public DeletePatientRecordCommand(String patientNric, int index) {
        this.patientNric = patientNric;
        this.index = index - 1;
    }

    private void deletePr(PatientRecordMap patientRecordMap, ArrayList<PatientRecord> patientRecords) {
        patientRecordMap.removePersonalRecord(patientRecords, patientNric);
        patientRecords.remove(index);
    }

    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecordMap
    ) throws HappyPillsException {
        boolean isIndexOutOfBound = patientRecordMap.get(patientNric).size() <= index && index >= 0;
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            if (patientRecordMap.get(patientNric) == null) {
                throw new HappyPillsException(Messages.MESSAGE_EMPTY_PATIENT);
            } else if (isIndexOutOfBound) {
                throw new HappyPillsException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
            }
            ArrayList<PatientRecord> patientRecords = patientRecordMap.get(patientNric);
            PatientRecord patientRecord = patientRecords.get(index);
            deletePr(patientRecordMap,patientRecords);
            try {
                Storage.writeAllToFile(Storage.PATIENT_RECORD_FILEPATH,
                        StorageTextUi.getFormattedPrString(patientRecordMap,patients));
            } catch (IOException e) {
                logger.info(StorageTextUi.FAIL_TO_WRITE_PR_MSG);
            }
            return PatientRecordTextUi.deletePatientRecordSuccessMessage(patientRecord, patientNric);
        } else {
            String message = Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND
                    + "\n"
                    + TextUi.DIVIDER;
            return message;
        }
    }
}
