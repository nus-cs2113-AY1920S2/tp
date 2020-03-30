package seedu.happypills.controller.commands.visitcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;

public class IncorrectVisitCommand extends VisitCommand {
    public final String feedbackToUser;

    public IncorrectVisitCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, VisitMap visits) {
        return feedbackToUser;
    }

}
