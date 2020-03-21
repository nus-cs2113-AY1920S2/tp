package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.util.DateTime;
import seedu.nuke.directory.Task;

/**
 * edit the deadline of the task.
 */
public class EditDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "editDeadline";
    //public static final String MESSAGE_USAGE_1 = COMMAND_WORD + "task description " + ": Edit the deadline of a
    // task.";
    //public static final String MESSAGE_USAGE_2 = "      Example: " + COMMAND_WORD + " read a book" + "YYYY-MM-DD
    // hh:mm:ss";
    public static final String MESSAGE_DONE = "  Nice! I've marked this task as done:  %s";
    public DateTime deadline;

    /**
     * set the new deadline.
     * @param toEdit full command args string
     */
    public EditDeadlineCommand(Task toEdit, DateTime deadline) {
        //super(toEdit);
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
//        // todo find the target task
//        final Task toEdit = new Task();
//        // todo set the description
//        toEdit.setDeadline(deadline);
//        return new CommandResult(String.format(MESSAGE_DONE, toEdit.getDescription()));
        return null;
    }
}
