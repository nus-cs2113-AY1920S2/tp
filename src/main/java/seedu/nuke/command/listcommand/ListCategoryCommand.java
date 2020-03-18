package seedu.nuke.command.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListCategoryCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsc";
    public static final String FORMAT = COMMAND_WORD;
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+)?)"),
            Pattern.compile("(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<all>(?:\\s+" + ALL_FLAG + ")?)"),
            Pattern.compile("(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^ae].*|[ae]\\S+))*)")
    };

    private String moduleKeyWord;
    private String categoryKeyword;
    private boolean isExact;

    public ListCategoryCommand(String moduleKeyWord, String categoryKeyword, boolean isExact) {
        this.moduleKeyWord = moduleKeyWord;
        this.categoryKeyword = categoryKeyword;
        this.isExact = isExact;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Category> categoryList =
                isExact ? ModuleManager.filterExact(moduleKeyWord, categoryKeyword) :
                        ModuleManager.filter(moduleKeyWord, categoryKeyword);
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.CATEGORY, new ArrayList<>(categoryList));
    }
}
