package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public class IncorrectCommand extends Command {
    public final String description;

    public IncorrectCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.println("Oh no. " + description);
    }
}
