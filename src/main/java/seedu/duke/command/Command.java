package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.InputException;
import seedu.duke.exception.RuntimeException;
import seedu.duke.exception.StorageException;
import seedu.duke.storage.StorageAvailableModulesList;
import seedu.duke.storage.StoragePersonInfo;
import seedu.duke.storage.StorageSemesterList;

public abstract class Command {

    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList)
            throws RuntimeException, StorageException, InputException {
        StoragePersonInfo.save();
        StorageAvailableModulesList.save(availableModulesList);
        StorageSemesterList.save(semesterList);
    }

    public boolean isExit() {
        return false;
    }
}
