package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import java.util.logging.Logger;

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
            assert taskList.getListSize() == 0;
            return new CommandResult(Messages.NO_TASKS_MSG);
        } else {
            assert taskList.getListSize() != 0;
            taskList.clearList();
            assert taskList.getListSize() == 0;
            return new CommandResult(Messages.CLEAR_SUCCESS_MESSAGE);
        }
    }
}
