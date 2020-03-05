package command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public abstract class Command {
    public static final String COMMAND_NAME = null; // todo: override this

    public abstract void execute(TaskList taskList, Ui ui); // todo: take TaskList, Ui, Storage as parameters
}
