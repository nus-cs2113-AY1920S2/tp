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

public class ListCategoryCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsc";
    public static final String FORMAT = COMMAND_WORD;
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)" +
            "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<optional>(?:\\s+-[ea])*)" +
            "(?<invalid>(?:\\s+-.*)*)"
    );
    public static final Pattern REGEX_OPTIONAL_FORMAT = Pattern.compile(
            "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)" +
            "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
    );

    private String moduleKeyWord;
    private String categoryKeyword;
    private boolean isExact;
    private boolean isAll;

    public ListCategoryCommand(String moduleKeyWord, String categoryKeyword, boolean isExact, boolean isAll) {
        this.moduleKeyWord = moduleKeyWord;
        this.categoryKeyword = categoryKeyword;
        this.isExact = isExact;
        this.isAll = isAll;
    }


    protected ArrayList<Directory> createFilteredList() {
        ArrayList<Category> filteredCategoryList;
        if (isAll || !moduleKeyWord.isEmpty()) {
            filteredCategoryList = isExact ? ModuleManager.filterExact(moduleKeyWord, categoryKeyword) :
                    ModuleManager.filter(moduleKeyWord, categoryKeyword);
            return new ArrayList<>(filteredCategoryList);
        }
        try {
            Module baseModule = DirectoryTraverser.getBaseModule();
            filteredCategoryList = isExact ? baseModule.getCategories().filterExact(categoryKeyword) :
                    baseModule.getCategories().filter(categoryKeyword);
            return new ArrayList<>(filteredCategoryList);
        } catch (IncorrectDirectoryLevelException e) {
            // Current directory is too high to get module information, i.e. at root level
            filteredCategoryList = isExact ? ModuleManager.filterExact(moduleKeyWord, categoryKeyword) :
                    ModuleManager.filter(moduleKeyWord, categoryKeyword);
            return new ArrayList<>(filteredCategoryList);
        }
    }

    @Override
    public CommandResult execute() {
        ArrayList<Directory> filteredCategoryList = createFilteredList();
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.CATEGORY, filteredCategoryList);
    }
}
