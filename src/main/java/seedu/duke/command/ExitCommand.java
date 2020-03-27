package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.RuntimeException;
import seedu.duke.exception.StorageException;
import seedu.duke.storage.StoragePersonInfo;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) throws StorageException {
        StoragePersonInfo.save();
    }

    public boolean isExit() {
        return true;
    }
}
