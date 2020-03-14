package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Task;

public class DeleteCommand extends Command {
    public static final String DELETE_COMMAND_WORD = "delete";
    protected int deleteIndex;

    /**
     * Constructs a DeleteCommand with the parameters supplied.
     * @param index index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.deleteIndex = index;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.NO_TASKS_MSG);
        }
        try {
            Task taskToBeDeleted = taskList.getTask(deleteIndex);
            taskList.deleteTask(deleteIndex);
            return new CommandResult(String.format(Messages.DELETE_SUCCESS_MESSAGE, taskToBeDeleted.getName()));
        } catch (IndexOutOfBoundsException e) {
            assert deleteIndex <= 0 || deleteIndex > taskList.getListSize() - 1;
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR, getRangeOfValidIndex(taskList)));
        }
    }
}
