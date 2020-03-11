package command;

import common.Messages;
import exceptions.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Task;

public class DoneCommand extends Command {
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
        try {
            Task taskToBeMarkDone = taskList.getTask(doneIndex);
            if (taskToBeMarkDone.getIsDone()) {
                return new CommandResult(String.format(Messages.COMPLETED_TASK_ERROR));
            }
            taskList.markTaskAsDone(doneIndex);
            return new CommandResult(String.format(Messages.DONE_SUCCESS_MESSAGE, doneIndex + 1,
                    taskToBeMarkDone.getName()));
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR, getRangeOfValidIndex(taskList)));
        }
    }
}
