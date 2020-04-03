package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.PatientRecordTextUi;
import seedu.happypills.ui.TextUi;

public class FindPatientRecordCommand extends PatientRecordCommand {
    protected String patientNric;
    protected int index;

    /**
     * Constructor for FindAppointmentCommand Class.
     * It creates a new FindAppointmentCommand Object with the information provided.
     *
     * @param patientNric Contains the NRIC of the patient that is to be retrieved.
     * @param index index for the record of the searched patient
     */
    public FindPatientRecordCommand(String patientNric, int index) {
        this.patientNric = patientNric;
        this.index = index - 1;
    }

    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecordMap
    ) throws HappyPillsException {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        boolean isIndexOutOfBound = patientRecordMap.get(patientNric).size() <= index && index >= 0;
        if (patients.containsKey(patientNric)) {
            if (patientRecordMap.get(patientNric) == null) {
                throw new HappyPillsException(Messages.MESSAGE_EMPTY_PATIENT);
            } else if (isIndexOutOfBound) {
                throw new HappyPillsException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
            }
            return PatientRecordTextUi.getPatientRecordSuccessMessage(patientRecordMap, patientNric, index);
        } else {
            String message = Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND
                    + "\n"
                    + TextUi.DIVIDER;
            return message;
        }
    }
}
