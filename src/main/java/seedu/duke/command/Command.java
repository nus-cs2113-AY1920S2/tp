package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.RuntimeException;
import seedu.duke.exception.StorageException;

public abstract class Command {

    public void execute(SemesterList semesterList,
                        AvailableModulesList availableModulesList) throws RuntimeException, StorageException {
    }

    public boolean isExit() {
        return false;
    }
}
