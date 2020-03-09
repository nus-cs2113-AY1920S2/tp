package seedu.nuke.command;

import seedu.nuke.data.DataManager;
import seedu.nuke.task.Task;
import seedu.nuke.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;

public class CheckDeadlineCommand {

    public CommandResult execute(ArrayList<Module> modules) {
        DataManager dataManager = new DataManager(modules);
        return new CommandResult("");
    }

    public CommandResult execute(String moduleCode) {
        return new CommandResult("");
    }
}
