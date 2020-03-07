package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public class DeleteCommand extends Command {
    protected int deleteIndex;

    /**
     * Delete tasks based on specified index.
     * @param index index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.deleteIndex = index;
    }

    /**
     * Deletes task and prints out Ui message.
     * @param taskList ArrayList of tasks
     * @param ui ui object representing delete message
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showDeleteMessage(taskList.getTask(deleteIndex), deleteIndex);
        taskList.deleteTask(deleteIndex);
    }
}
