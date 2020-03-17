package seedu.nuke.command;

import seedu.nuke.directory.Task;

/**
 * edit-type command.
 */
public abstract class EditTaskCommand extends TaskCommand {

    protected String taskDescription;
    protected Task task;

    /**
     * set the deadline of a task.
     * @param taskDescription full command args string
     */
    public EditTaskCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public EditTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public abstract CommandResult execute();
}
