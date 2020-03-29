package seedu.happypills.commands;

import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;

public interface Command {
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException;
}
