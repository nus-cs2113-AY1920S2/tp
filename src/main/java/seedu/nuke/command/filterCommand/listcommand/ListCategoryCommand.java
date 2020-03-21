package seedu.nuke.command.filterCommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.*;

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

    /**
     * Constructs the command to list categories.
     *
     * @param moduleKeyWord
     *  The keyword to filter the modules
     * @param categoryKeyword
     *  The keyword to filter the categories
     * @param isExact
     *  Checks if categories are to be filtered exactly
     * @param isAll
     *  Checks whether to show <b>all</b> categories across modules
     */
    public ListCategoryCommand(String moduleKeyWord, String categoryKeyword, boolean isExact, boolean isAll) {
        this.moduleKeyWord = moduleKeyWord;
        this.categoryKeyword = categoryKeyword;
        this.isExact = isExact;
        this.isAll = isAll;
    }

    /**
     * Executes the <b>List Category Command</b> to show a filtered list of modules.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Category
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        ArrayList<Directory> filteredCategoryList =
                createFilteredCategoryList(moduleKeyWord, categoryKeyword, isExact, isAll);
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.CATEGORY, filteredCategoryList);
    }
}
