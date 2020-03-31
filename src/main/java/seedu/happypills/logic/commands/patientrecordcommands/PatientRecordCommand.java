package seedu.happypills.logic.commands.patientrecordcommands;

import seedu.happypills.logic.commands.Command;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;

public abstract class PatientRecordCommand implements Command {
    public String execute(PatientRecordMap patientRecordMap) throws HappyPillsException {
        return null;
    }
}
