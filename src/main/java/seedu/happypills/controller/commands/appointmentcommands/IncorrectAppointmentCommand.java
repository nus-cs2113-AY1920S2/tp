package seedu.happypills.controller.commands.appointmentcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.view.ui.TextUi;

public class IncorrectAppointmentCommand extends AppointmentCommand {
    protected String message;

    public IncorrectAppointmentCommand(String message) {
        this.message = message + TextUi.DIVIDER;
    }

    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, VisitMap visits
    ) throws HappyPillsException {
        return message;
    }
}
