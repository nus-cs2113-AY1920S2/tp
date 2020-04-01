package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.PatientRecordTextUi;
import seedu.happypills.ui.TextUi;

public class ListPatientRecordCommand extends PatientRecordCommand {

    protected String patientNric;

    public ListPatientRecordCommand(String patientNric) {
        this.patientNric = patientNric;
    }

    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            if (patientRecords.get(patientNric) == null) {
                throw new HappyPillsException(PatientRecordTextUi.emptyPatientRecordMessage);
            }
            return PatientRecordTextUi.getPatientRecordListSuccessMessage(patientRecords.get(patientNric));
        } else {
            String message =  PatientTextUi.patientNotFoundMessage
                    + "\n"
                    + TextUi.DIVIDER;
            return message;
        }
    }
}
