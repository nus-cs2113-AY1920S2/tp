package command;

import seedu.duke.TaskList;

public class ListCommand extends Command {

    private final String listParam;
    private final String LIST_TODAY_COMMAND = "today";

    public ListCommand(String listParam) {
        this.listParam = listParam;
    }

    @Override
    public void execute(TaskList tasks) {
        if (listParam.equals(LIST_TODAY_COMMAND)) {

        }


    }
}
