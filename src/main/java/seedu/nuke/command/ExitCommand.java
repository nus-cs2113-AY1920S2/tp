package seedu.nuke.command;

import seedu.nuke.task.Task;

import static seedu.nuke.util.Message.MESSAGE_FAREWELL;

public class ExitCommand extends TaskCommand{
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE_1 = COMMAND_WORD + ": Exit the program.";
    public static final String MESSAGE_USAGE_2 = "    Example: " + COMMAND_WORD;

    public ExitCommand() {

    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_FAREWELL));
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
