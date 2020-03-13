package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class IncorrectCommand extends Command {
    public final String description;

    /**
     * Constructor to deal with incorrect commands.
     * @param description String of the wrong command input by user
     */
    public IncorrectCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        return new CommandResult(String.format(Messages.INCORRECT_COMMAND_ERROR, description));
    }
}
