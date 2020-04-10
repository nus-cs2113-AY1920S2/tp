package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.nuke.util.Message.MESSAGE_NO_TASKS_TO_SHOW;
import static seedu.nuke.util.Message.messageTaskSuccessfullyList;

/**
 * Sort all the undo tasks in terms of keywords of ModuleCode and category.
 * by either deadline or priority and display the sorted tasks to the user.
 * By default, the tasks will be sorted based on deadline by default;
 * unless, users specify the need to sort all undo tasks by priority.
 */
public class ListTaskSortedCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsts";
    public static final String FORMAT = COMMAND_WORD
            + " [ -m <module keyword> -c <category keyword> ] [ -d -p (choose 1; default -d) ]";
    public static final String MESSAGE_USAGE = String.format(
            "%s - List all undone tasks in ascending order of deadline or in descending order of priority\n"
            + "Note: -d to sort by deadline (default); -p to sort by priority\n"
            + "Format: %s\n"
            + "Example: lsts -m cs2113t -p\n",
            COMMAND_WORD, FORMAT);
    public static final String NO_KEYWORD = "";


    private String moduleCode;
    private String category;
    private boolean isByPriority;

    /**
     * Constructs command to list tasks in sorted order.
     * @param moduleCode the keyword to filter modules
     * @param category the keyword to filter categories
     * @param isByPriority checks whether tasks should be sorted in priority
     */
    public ListTaskSortedCommand(String moduleCode, String category, boolean isByPriority) {
        this.moduleCode = moduleCode;
        this.category = category;
        this.isByPriority = isByPriority;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Task> filteredTaskList =
                createFilteredTaskList(moduleCode, category, NO_KEYWORD, false, false).stream()
                .filter(task -> !task.isDone()).collect(Collectors.toCollection(ArrayList::new));

        if (filteredTaskList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TASKS_TO_SHOW);
        }

        if (isByPriority) {
            sortTaskList(filteredTaskList, false, true);
        } else {
            sortTaskList(filteredTaskList, true, false);
        }

        assert !filteredTaskList.isEmpty() : "make sure there are some tasks in the list to show";

        return new CommandResult(messageTaskSuccessfullyList(filteredTaskList.size()),
                DirectoryLevel.TASK, new ArrayList<>(filteredTaskList));
    }
}
