package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.StorageException;
import seedu.duke.storage.StorageAvailableModulesList;
import seedu.duke.storage.StoragePersonInfo;
import seedu.duke.storage.StorageSemesterList;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) throws StorageException {
        StoragePersonInfo.save();
        StorageAvailableModulesList.save(availableModulesList);
        StorageSemesterList.save(semesterList);
    }

    public boolean isExit() {
        return true;
    }
}
