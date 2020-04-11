package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientRecordTextUi;

//@@ itskesin

/**
 * Lists all patient records to the user.
 */
public class ListPatientRecordCommand extends PatientRecordCommand {

    protected String nric;

    /**
     * Constructor for List Patient Record Command Class.
     *
     * @param nric Contains the nric of the patient that is to be retrieved.
     */
    public ListPatientRecordCommand(String nric) {
        this.nric = nric;
    }

    /**
     * Executes the list patient record command.
     *
     * @param patients       The list of patients.
     * @param appointments   The list of appointments.
     * @param patientRecords The list of patient records.
     * @return Error Message or Success Message.
     * @throws HappyPillsException If NRIC is not found in the patient record list.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {
        assert !nric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(nric)) {
            if (patientRecords.get(nric) == null) {
                throw new HappyPillsException(Messages.MESSAGE_PATIENT_RECORD_NOT_FOUND);
            }
            String message = PatientRecordTextUi.getPatientRecordListSuccessMessage(patientRecords.get(nric));
            return message;
        } else {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_NOT_FOUND);
        }
    }
}
