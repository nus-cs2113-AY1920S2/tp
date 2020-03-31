package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

public class DeletePatientRecordCommand extends PatientRecordCommand {
    protected String patientNric;
    protected int index;

    /**
     * Constructor for FindAppointmentCommand Class.
     * It creates a new FindAppointmentCommand Object with the information provided.
     *
     * @param patientNric Contains the NRIC of the patient that is to be retrieved.
     */
    public DeletePatientRecordCommand(String patientNric, int index) {
        this.patientNric = patientNric;
        this.index = index - 1;
    }

    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecordMap
    ) throws HappyPillsException {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            if (patientRecordMap.get(patientNric) == null) {
                return TextUi.emptyPatientRecordMessage();
            }
            return TextUi.deletePatientRecordSuccessMessage(patientRecordMap, patientNric, index);
        } else {
            String message =  TextUi.patientNotExist(patientNric)
                    + "\n"
                    + TextUi.DIVIDER;
            return message;
        }
    }
}
