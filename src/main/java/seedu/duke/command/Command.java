package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.exception.RuntimeException;

public class Command {

    public void execute(SelectedModulesList selectedModulesList,
                        AvailableModulesList availableModulesList) throws RuntimeException {
    }

    public boolean isExit() {
        return false;
    }
}
