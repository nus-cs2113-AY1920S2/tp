package seedu.nuke.command;

import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.Task;

/**
 * task level command inherits ModuleCommand class.
 */
public abstract class TaskCommand extends ModuleCommand {

    protected TaskManager taskManager;
    //public String commandWord;
    private int targetIndex = -1;
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    public TaskCommand() {
    }

    public TaskCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return taskManager.getTaskList().get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    @Override
    public abstract CommandResult execute();
}
