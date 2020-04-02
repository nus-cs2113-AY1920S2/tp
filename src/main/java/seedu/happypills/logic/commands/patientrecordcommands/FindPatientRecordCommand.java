package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
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
        if (patients.containsKey(patientNric)) {
            if (patientRecordMap.get(patientNric) == null) {
                throw new HappyPillsException(PatientRecordTextUi.emptyPatientRecordMessage);
            } else if (patientRecordMap.get(patientNric).size() <= index) {
                throw new HappyPillsException(PatientRecordTextUi.getEmptyPatientRecordList);
            }
            return PatientRecordTextUi.getPatientRecordSuccessMessage(patientRecordMap, patientNric, index);
        } else {
            String message =  PatientTextUi.patientNotFoundMessage
                    + "\n"
                    + TextUi.DIVIDER;
            return message;
        }
    }
}
