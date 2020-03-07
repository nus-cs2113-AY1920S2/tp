package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public class DoneCommand extends Command {
    protected int doneIndex;

    /**
     * Constructs a DoneCommand with parameters supplied.
     * @param index index in the ArrayList to be marked done
     */
    public DoneCommand(int index) {
        this.doneIndex = index;
    }

    /**
     * Set Task as completed, prints Ui message.
     * @param taskList TaskList object that handles adding Task
     * @param ui Ui object that interacts with user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.markTaskAsDone(doneIndex);
        ui.showDoneMessage(taskList.getTask(doneIndex), doneIndex);
    }
}
