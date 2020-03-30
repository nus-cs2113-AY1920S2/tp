package seedu.happypills.controller.commands.appointmentcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;

public class IncorrectAppointmentCommand extends AppointmentCommand {
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException {
        return "    Command is incorrect. Please try again.\n";
    }
}
