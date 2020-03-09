package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class IncorrectCommand extends Command {
    public final String description;

    public IncorrectCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        return new CommandResult(String.format(Messages.INCORRECT_COMMAND_ERROR, description));
    }
}
