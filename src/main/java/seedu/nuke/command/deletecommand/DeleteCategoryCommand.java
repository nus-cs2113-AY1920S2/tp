package seedu.nuke.command.deletecommand;

import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Directory;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.Message.*;

/**
 * <h3>Delete Category Command</h3>
 * A <b>Command</b> to delete <b>Category(s)</b> from the <b>Category List</b>.
 * @see Command
 * @see Category
 */
public class DeleteCategoryCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delc";
    public static final String FORMAT = COMMAND_WORD + " <category name>";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<all>(?:\\s+" + ALL_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^m].*|[m]\\S+))*)")
    };

    private String moduleCode;
    private String categoryName;
    private boolean isExact;

    /**
     * Constructs the command to delete a category.
     *
     * @param moduleCode
     *  The module code of the module to delete the category
     * @param categoryName
     *  The name of the category
     * @param isExact
     *  Checks if categories are to be filtered exactly
     */
    public DeleteCategoryCommand(String moduleCode, String categoryName, boolean isExact) {
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.isExact = isExact;
    }

    /**
     *  Executes the initial phase of the category deletion process depending on the number of categories filtered.
     *  If there are no categories in the filtered list, then the deletion ends.
     *  Otherwise, if there is one, there will be a prompt to request confirmation.
     *  If there are multiple categories instead, there will be a prompt to choose which categories to delete.
     *
     * @param filteredCategories
     *  The filtered list of categories containing possibly the categories to be deleted
     * @return
     *  The result of the execution
     */
    @Override
    protected CommandResult executeInitialDelete(ArrayList<Directory> filteredCategories) {
        final int CATEGORIES_COUNT = filteredCategories.size();
        if (CATEGORIES_COUNT == 0) {
            return new CommandResult(MESSAGE_NO_CATEGORIES_FOUND);
        } else if (CATEGORIES_COUNT == 1) {
            Executor.preparePromptConfirmation();
            Executor.setFilteredList(filteredCategories, DirectoryLevel.CATEGORY);
            Category toDelete = (Category) filteredCategories.get(0);
            return new CommandResult(messageConfirmDeleteCategory(toDelete));
        } else {
            Executor.preparePromptIndices();
            Executor.setFilteredList(filteredCategories, DirectoryLevel.CATEGORY);
            return new CommandResult(messagePromptDeleteCategoryIndices(filteredCategories));
        }
    }

    /**
     * Executes the <b>Delete Category Command</b> to delete <b>Category(s)</b> with the <code>category name</code>
     * from the <b>Category List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Category
     * @see CategoryManager
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        ArrayList<Category> filteredCategories =
                isExact ? ModuleManager.filterExact(moduleCode, categoryName) :
                        ModuleManager.filter(moduleCode, categoryName);
        return executeInitialDelete(new ArrayList<>(filteredCategories));
    }
}
