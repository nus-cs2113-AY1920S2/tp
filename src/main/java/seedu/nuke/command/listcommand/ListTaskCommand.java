package seedu.nuke.command.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListTaskCommand extends ListCommand {
    public static final String COMMAND_WORD = "lst";
    public static final String FORMAT = COMMAND_WORD;
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

    public ListTaskCommand(String moduleKeyWord, String categoryKeyword, String taskKeyword, boolean isExact) {
        this.moduleKeyWord = moduleKeyWord;
        this.categoryKeyword = categoryKeyword;
        this.taskKeyword = taskKeyword;
        this.isExact = isExact;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Task> taskList =
                isExact ? ModuleManager.filterExact(moduleKeyWord, categoryKeyword, taskKeyword) :
                        ModuleManager.filter(moduleKeyWord, categoryKeyword, taskKeyword);
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.TASK, new ArrayList<>(taskList));
    }
}
