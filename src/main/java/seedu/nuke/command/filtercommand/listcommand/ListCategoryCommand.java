package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.DirectoryLevel;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_CATEGORIES_TO_SHOW;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListCategoryCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsc";
    public static final String FORMAT = COMMAND_WORD + " [ <category keyword> -m <module keyword> -e -a ]";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Filter and show the categories of module(s)\n"
            + "Note: -e to filter for exact keywords; -a to filter across ALL your categories\n"
            + "Format: %s\n"
            + "Example: lsc tutorial -e -a\n",
            COMMAND_WORD, FORMAT);

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
        ArrayList<Category> filteredCategoryList =
                createFilteredCategoryList(moduleKeyWord, categoryKeyword, isExact, isAll);
        if (filteredCategoryList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_CATEGORIES_TO_SHOW);
        }
        sortCategoryList(filteredCategoryList);
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.CATEGORY, new ArrayList<>(filteredCategoryList));
    }
}
