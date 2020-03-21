package seedu.nuke.command.filterCommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListTaskCommand extends ListCommand {
    public static final String COMMAND_WORD = "lst";
    public static final String FORMAT = COMMAND_WORD;
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)" +
            "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<optional>(?:\\s+-[ea])*)" +
            "(?<invalid>(?:\\s+-.*)*)"
    );
    public static final Pattern REGEX_OPTIONAL_FORMAT = Pattern.compile(
            "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)" +
            "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
    );

    private String moduleKeyWord;
    private String categoryKeyword;
    private String taskKeyword;
    private boolean isExact;
    private boolean isAll;

    /**
     * Constructs the command to list tasks.
     *
     * @param moduleKeyWord
     *  The keyword to filter the modules
     * @param categoryKeyword
     *  The keyword to filter the categories
     * @param taskKeyword
     *  The keyword to filter the tasks
     * @param isExact
     *  Checks if tasks are to be filtered exactly
     * @param isAll
     *  Checks whether to show <b>all</b> tasks across modules and categories
     */
    public ListTaskCommand(String moduleKeyWord, String categoryKeyword, String taskKeyword, boolean isExact, boolean isAll) {
        this.moduleKeyWord = moduleKeyWord;
        this.categoryKeyword = categoryKeyword;
        this.taskKeyword = taskKeyword;
        this.isExact = isExact;
        this.isAll = isAll;
    }

    /**
     * Executes the <b>List Task Command</b> to show a filtered list of modules.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Task
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        ArrayList<Directory> filteredTaskList =
                createFilteredTaskList(moduleKeyWord, categoryKeyword, taskKeyword, isExact, isAll);
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.TASK, filteredTaskList);
    }
}
