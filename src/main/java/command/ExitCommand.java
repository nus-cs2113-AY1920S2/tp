package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ExitCommand extends Command {
    public static final String EXIT_COMMAND_WORD = "exit";

    /**
     * Executes the Exit command.
     * @param taskList TaskList object that handles adding Task
     * @param ui       Ui object that interacts with user
     * @return CommandResult object with acknowledgment message
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        CommandResult.isExit = true;
        return new CommandResult(Messages.EXIT_MESSAGE);
    }
}
