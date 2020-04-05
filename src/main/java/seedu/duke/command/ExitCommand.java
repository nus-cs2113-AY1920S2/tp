package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.InputException;
import seedu.duke.exception.RuntimeException;
import seedu.duke.exception.StorageException;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList)
            throws StorageException, RuntimeException, InputException {
        super.execute(semesterList, availableModulesList);
    }

    public boolean isExit() {
        return true;
    }
}
