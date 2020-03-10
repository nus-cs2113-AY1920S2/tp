package seedu.nuke.command;

import static seedu.nuke.util.Message.MESSAGE_SHOW_TASKS;

public class ListModuleCommand extends Command {
    public static final String COMMAND_WORD = "lsm";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SHOW_TASKS, true);
    }
}
