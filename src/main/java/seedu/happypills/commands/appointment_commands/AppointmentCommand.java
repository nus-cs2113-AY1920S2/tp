package seedu.happypills.commands.appointment_commands;

import seedu.happypills.commands.Command;
import seedu.happypills.data.AppointmentMap;
import seedu.happypills.exception.HappyPillsException;

public abstract class AppointmentCommand implements Command {

    public String execute(AppointmentMap appointments) throws HappyPillsException {
        return null;
    }
}
