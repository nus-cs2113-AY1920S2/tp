package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;

//@@author
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_USAGE = "Delete a Task: delete [TASK NUMBER]";
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
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR, taskList.getRangeOfValidIndex(taskList)));
        }
    }
}
