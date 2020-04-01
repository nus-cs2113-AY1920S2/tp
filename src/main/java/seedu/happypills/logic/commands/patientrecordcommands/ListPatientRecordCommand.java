package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.PRTextUi;
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
                return PRTextUi.emptyPatientRecordMessage();
            }
            return PRTextUi.getPatientRecordListSuccessMessage(patientRecords.get(patientNric));
        } else {
            String message =  TextUi.patientNotExist
                    + "\n"
                    + TextUi.DIVIDER;
            return message;
        }
    }
}
