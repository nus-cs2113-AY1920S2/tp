package seedu.nuke.command;

import static seedu.nuke.util.Message.MESSAGE_SHOW_TASKS;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SHOW_TASKS, true);
    }
}
