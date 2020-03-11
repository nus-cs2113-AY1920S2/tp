package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public abstract class Command {
    /**
     * Executes the specific command.
     * @param taskList TaskList object that handles adding Task
     * @param ui Ui object that interacts with user
     * @return CommandResult object with acknowledgment message
     */
    public abstract CommandResult execute(TaskList taskList, Ui ui); //
    // todo: take TaskList, Ui, Storage as parameters

    /**
     * Obtain a range of numbers that is valid for usage on taskList.
     * @param taskList The interested list in TaskManager to find the range of values
     * @return A string with the range of valid numbers.
     */
    protected String getRangeOfValidIndex(TaskList taskList) {
        int maxTasks = taskList.getListSize();
        return String.format(Messages.RANGE_OF_VALID_TASK_INDEX_MSG, maxTasks);
    }
}

