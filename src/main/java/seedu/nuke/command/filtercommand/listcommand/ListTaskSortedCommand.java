package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static seedu.nuke.parser.Parser.DEADLINE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_NO_TASKS_TO_SHOW;
import static seedu.nuke.util.Message.messageTaskSuccessfullyList;

/**
 * Sorts all undone tasks across all modules by either deadline or priority and display the sorted tasks to the user.
 * By default, this command sorts all undone tasks by deadline, unless specified by the user to sort by priority.
 */
public class ListTaskSortedCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsts";
    public static final String FORMAT = COMMAND_WORD + " [ <module code> ] [ -d -p (choose 1; default -d) ]";
    public static final String MESSAGE_USAGE = String.format(
            "%s - List all undone tasks from nearest to latest deadline or from largest to smallest priority\n"
            + "Note: -d to filter by deadline (default); -p to filter by priority\n"
            + "Format: %s\n"
            + "Example: lsts cs2113t -p\n",
            COMMAND_WORD, FORMAT);
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + ")?)"
            + "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + ")?)"
            + "(?<prioritySecond>(?:\\s+" + PRIORITY_PREFIX + ")?)"
            + "(?<invalid>.*)"
    );

    private String moduleCode;
    private boolean isByPriority;

    public ListTaskSortedCommand(String moduleCode, boolean isByPriority) {
        this.moduleCode = moduleCode;
        this.isByPriority = isByPriority;
    }

    @Override
    public CommandResult execute() {
        // Checks if module exists in Module List
        if (!moduleCode.isEmpty()) {
            if (!ModuleManager.contains(moduleCode)) {
                return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
            }
        }
        // Get all tasks with module code
        String noKeyword = "";
        ArrayList<Task> filteredTaskList = ModuleManager.filterExact(moduleCode, noKeyword, noKeyword).stream()
                .filter(task -> !task.isDone()).collect(Collectors.toCollection(ArrayList::new));

        if (filteredTaskList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TASKS_TO_SHOW);
        }

        if (isByPriority) {
            sortTaskList(filteredTaskList, false, true);
        } else {
            sortTaskList(filteredTaskList, true, false);
        }

        assert filteredTaskList.isEmpty() : "make sure there are some tasks in the list";

        return new CommandResult(messageTaskSuccessfullyList(filteredTaskList.size()),
                DirectoryLevel.TASK, new ArrayList<>(filteredTaskList));
    }
}
