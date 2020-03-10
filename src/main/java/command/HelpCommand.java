package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class HelpCommand extends Command {

    /**
     * Prints to user the help message.
     *
     * @param taskList TaskList object that handles adding Task
     * @param ui       Ui object that interacts with user
     * @return CommandResult object with acknowledgment message
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        return new CommandResult(Messages.HELP_FORMAT_MESSAGE);
    }
}
