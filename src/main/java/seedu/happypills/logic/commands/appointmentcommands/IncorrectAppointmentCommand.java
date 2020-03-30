package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

public class IncorrectAppointmentCommand extends AppointmentCommand {
    protected String message;

    public IncorrectAppointmentCommand(String message) {
        this.message = message + TextUi.DIVIDER;
    }

    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits
    ) throws HappyPillsException {
        return message;
    }
}