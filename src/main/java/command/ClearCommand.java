package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ClearCommand  extends Command {
    public static final String CLEAR_COMMAND_WORD = "clear";

    /**
     * Constructs the clear command to empty TaskList.
     */
    public ClearCommand() {
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        if (taskList.getListSize() == 0) {
            return new CommandResult(String.format(Messages.NO_TASKS_MSG));
        } else {
            taskList.clearList();
            return new CommandResult(String.format(Messages.CLEAR_SUCCESS_MESSAGE));
        }
    }
}
