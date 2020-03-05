package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ListCommand extends Command {

    private final String listParam;
    private final String LIST_TODAY_COMMAND = "today";

    public ListCommand(String listParam) {
        this.listParam = listParam;
    }

    public void execute(TaskList taskList, Ui ui) {
        if (listParam.equals(LIST_TODAY_COMMAND)) {
            taskList.listTodayTasks();
        }
    }
}
