package seedu.nuke.command;

/**
 * edit-type command
 */
public abstract class EditTaskCommand extends TaskCommand {

    protected String taskDescription;

    /**
     * set the deadline of a task
     * @param taskDescription full command args string
     */
    public EditTaskCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public abstract CommandResult execute();
}
