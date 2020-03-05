package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ListCommand extends Command {

    private final String listParam;
    private static final String TODAY_COMMAND = "today";
    private static final String WEEK_COMMAND = "week";

    public ListCommand(String listParam) {
        this.listParam = listParam;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (listParam.equals(TODAY_COMMAND)) {
            taskList.listTodayTasks();
        }
    }
}
