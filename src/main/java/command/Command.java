package command;

import seedu.atas.TaskList;
import seedu.atas.Ui;

//@@author 
public abstract class Command {
    /**
     * Executes the specific command.
     * @param taskList TaskList object that handles adding Task
     * @param ui Ui object that interacts with user
     * @return CommandResult object with acknowledgment message
     */
    public abstract CommandResult execute(TaskList taskList, Ui ui); //
}
