package seedu.nuke.command.filtercommand.deletecommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;

public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "delg";
    public static final String FORMAT = COMMAND_WORD
            + " <tag info> -m <module code> -c <category name>"
            + " -t <task description>";
    public static final String MESSAGE_USAGE = COMMAND_WORD + System.lineSeparator() + "Delete tags of task"
            + System.lineSeparator() + FORMAT + System.lineSeparator();

    @Override
    public CommandResult execute() {
        return null;
    }
}
