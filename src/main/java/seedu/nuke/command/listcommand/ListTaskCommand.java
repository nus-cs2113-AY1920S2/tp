package seedu.nuke.command.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.*;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListTaskCommand extends ListCommand {
    public static final String COMMAND_WORD = "lst";
    public static final String FORMAT = COMMAND_WORD;
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)+)" +
            "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<optional>(?:\\s+-[ea])*)" +
            "(?<invalid>(?:\\s+-.*)*)"
    );
    public static final Pattern REGEX_OPTIONAL_FORMAT = Pattern.compile(
            "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)" +
            "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
    );
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+)?)"),
            Pattern.compile("(?<moduleCode>(?:\\s" + MODULE_CODE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<categoryName>(?:\\s" + CATEGORY_NAME_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<exact>(?:\\s" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<all>(?:\\s" + ALL_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:\\s-(?:[^ae].*|[ae]\\S+)))")
    };

    private String moduleKeyWord;
    private String categoryKeyword;
    private String taskKeyword;
    private boolean isExact;
    private boolean isAll;

    public ListTaskCommand(String moduleKeyWord, String categoryKeyword, String taskKeyword, boolean isExact, boolean isAll) {
        this.moduleKeyWord = moduleKeyWord;
        this.categoryKeyword = categoryKeyword;
        this.taskKeyword = taskKeyword;
        this.isExact = isExact;
        this.isAll = isAll;
    }

    protected ArrayList<Directory> createFilteredList() {
        ArrayList<Task> filteredTaskList;
        if (isAll || !moduleKeyWord.isEmpty()) {
            filteredTaskList = isExact ? ModuleManager.filterExact(moduleKeyWord, categoryKeyword, taskKeyword) :
                    ModuleManager.filter(moduleKeyWord, categoryKeyword, taskKeyword);
            return new ArrayList<>(filteredTaskList);
        }

        if (!categoryKeyword.isEmpty()) {
            try {
                Module baseModule = DirectoryTraverser.getBaseModule();
                filteredTaskList = isExact ? baseModule.getCategories().filterExact(categoryKeyword, taskKeyword) :
                        baseModule.getCategories().filter(categoryKeyword, taskKeyword);
                return new ArrayList<>(filteredTaskList);
            } catch (IncorrectDirectoryLevelException e) {
                // Current directory is too high to get module information, i.e. at root level
                filteredTaskList = isExact ? ModuleManager.filterExact(moduleKeyWord, categoryKeyword, taskKeyword) :
                        ModuleManager.filter(moduleKeyWord, categoryKeyword, taskKeyword);
                return new ArrayList<>(filteredTaskList);
            }
        }

        try {
            Category baseCategory = DirectoryTraverser.getBaseCategory();
            filteredTaskList = isExact ? baseCategory.getTasks().filterExact(taskKeyword) :
                    baseCategory.getTasks().filter(taskKeyword);
            return new ArrayList<>(filteredTaskList);
        } catch (IncorrectDirectoryLevelException e) {
            // Current directory is too high to get category information, i.e. at root or module level
            filteredTaskList = isExact ? ModuleManager.filterExact(moduleKeyWord, categoryKeyword, taskKeyword) :
                    ModuleManager.filter(moduleKeyWord, categoryKeyword, taskKeyword);
            return new ArrayList<>(filteredTaskList);
        }
    }

    @Override
    public CommandResult execute() {
        ArrayList<Directory> filteredTaskList = createFilteredList();
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.TASK, filteredTaskList);
    }
}
