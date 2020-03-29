package seedu.happypills.commands.appointmentcommands;

import seedu.happypills.commands.Command;
import seedu.happypills.data.AppointmentMap;
import seedu.happypills.exception.HappyPillsException;

public abstract class AppointmentCommand implements Command {

    public String execute(AppointmentMap appointments) throws HappyPillsException {
        return null;
    }
}
