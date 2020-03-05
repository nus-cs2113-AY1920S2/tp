package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public class DeleteCommand extends Command {
    protected int deleteIndex;

    public DeleteCommand(int index) {
        this.deleteIndex = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showDeleteMessage(taskList.getTask(deleteIndex), deleteIndex);
        taskList.deleteTask(deleteIndex);
    }
}
