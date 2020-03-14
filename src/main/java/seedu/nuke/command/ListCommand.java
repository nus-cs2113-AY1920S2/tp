package seedu.nuke.command;

import seedu.nuke.data.module.ModuleList;
import seedu.nuke.data.task.Task;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_SHOW_MODULES;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT = COMMAND_WORD + " ( -m <module code> -c <category name> -t <task description> )";

    private String moduleKeyword;
    private String categoryKeyword;
    private String taskKeyword;

    public ListCommand(String moduleKeyword, String categoryKeyword, String taskKeyword) {
        this.moduleKeyword = moduleKeyword;
        this.categoryKeyword = categoryKeyword;
        this.taskKeyword = taskKeyword;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CommandResult execute() {
        ArrayList<Task> filteredTaskList = ModuleList.filter(moduleKeyword, categoryKeyword, taskKeyword);
        return new CommandResult(MESSAGE_SHOW_MODULES, true, filteredTaskList);
    }
}
