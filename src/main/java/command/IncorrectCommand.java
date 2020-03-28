package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;

//@@author lwxymere
public class IncorrectCommand extends Command {
    public final String description;

    /**
     * Constructor to deal with incorrect commands.
     * @param description String of the wrong command input by user
     */
    public IncorrectCommand(String description) {
        this.description = description;
    }

    /**
     * Returns a String as the description of the IncorrectCommand.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        return new CommandResult(String.format(Messages.INCORRECT_COMMAND_ERROR, description));
    }
}
