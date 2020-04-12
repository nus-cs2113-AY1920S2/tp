package seedu.command;

import seedu.common.Messages;

/**
 * Sends the exit signal to main loop, program is exited after this command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_INFO = COMMAND_WORD + ": exits the program";
    public static final String COMMAND_USAGE = COMMAND_WORD;
    public static final String ERROR_FEEDBACK = "No arguments required";

    /**
     * Check if exit command is given.
     *
     * @return signal to main loop that program will be terminated
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.BYE_MESSAGE);
    }
}

