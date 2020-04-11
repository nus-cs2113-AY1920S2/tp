package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.logic.commands.Command;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.exception.HappyPillsException;

public abstract class AppointmentCommand implements Command {

    public String execute(AppointmentMap appointments) throws HappyPillsException {
        return null;
    }
}
