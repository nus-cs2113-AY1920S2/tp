package seedu.happypills.commands.appointmentcommands;

import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;

public class IncorrectAppointmentCommand extends AppointmentCommand {
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException {
        return "    Command is incorrect. Please try again.\n";
    }
}
