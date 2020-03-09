package seedu.nuke.command;

/*
    edit the deadline of the task
 */
public class EditDeadlineCommand extends EditCommand {
    public static final String COMMAND_WORD = "editDeadline";
    public static final String MESSAGE_USAGE_1 = COMMAND_WORD + "task description "+": Edit the deadline of a task.";
    public static final String MESSAGE_USAGE_2 = "      Example: " + COMMAND_WORD+ " read a book" + "YYYY-MM-DD hh:mm:ss";

    public EditDeadlineCommand(String toEdit) {
        super(toEdit);
    }

    @Override
    public CommandResult execute() {
        return null;
    }

}
