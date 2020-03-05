package command;

import seedu.duke.TaskList;

public abstract class Command {
    public static final String COMMAND_NAME = null; // todo: override this

    public abstract void execute(TaskList tasks); // todo: take TaskList, Ui, Storage as parameters
}
