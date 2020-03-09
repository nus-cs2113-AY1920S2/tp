package seedu.nuke.command;

/*
    edit-type command
 */
public abstract class EditCommand extends Command{

    protected String taskDescription;

    /**
     * set the deadline of a task
     * @param taskDescription full command args string
     */
    public EditCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public abstract CommandResult execute();
}
