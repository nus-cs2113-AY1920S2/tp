package seedu.happypills.controller.commands.patientcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectPatientCommand extends PatientCommand {

    public final String feedbackToUser;

    public IncorrectPatientCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) {
        return feedbackToUser;
    }

}