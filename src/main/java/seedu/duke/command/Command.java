package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.RuntimeException;

public class Command {

    public void execute(SemesterList semesterList,
                        AvailableModulesList availableModulesList) throws RuntimeException {
    }

    public boolean isExit() {
        return false;
    }
}
