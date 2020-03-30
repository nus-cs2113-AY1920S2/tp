package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;

public class IncorrectPatientRecordCommand extends PatientRecordCommand {
    public final String feedbackToUser;

    public IncorrectPatientRecordCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecordMap) {
        return feedbackToUser;
    }

}
