package command;

import common.Messages;
import exceptions.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Task;

public class DeleteCommand extends Command {
    protected int deleteIndex;

    /**
     * Delete tasks based on specified index.
     * @param index index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.deleteIndex = index;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        try {
            Task taskToBeDeleted = taskList.getTask(deleteIndex);
            taskList.deleteTask(deleteIndex);
            return new CommandResult(String.format(Messages.DELETE_SUCCESS_MESSAGE, deleteIndex + 1,
                    taskToBeDeleted.getName()));
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR, getRangeOfValidIndex(taskList)));
        }
    }
}
