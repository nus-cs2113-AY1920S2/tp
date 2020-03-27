package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String COMMAND_USAGE = "Mark Task as Done: done [TASK NUMBER]";

    protected int doneIndex;

    /**
     * Constructs a DoneCommand with parameters supplied.
     * @param index index in the ArrayList to be marked done
     */
    public DoneCommand(int index) {
        this.doneIndex = index;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.NO_TASKS_MSG);
        }
        try {
            Task taskToBeMarkDone = taskList.getTask(doneIndex);
            if (taskToBeMarkDone.getIsDone()) {
                return new CommandResult(Messages.COMPLETED_TASK_ERROR);
            }
            taskList.markTaskAsDone(doneIndex);
            return new CommandResult(String.format(Messages.DONE_SUCCESS_MESSAGE, taskToBeMarkDone.getName()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR, getRangeOfValidIndex(taskList)));
        }
    }
}
