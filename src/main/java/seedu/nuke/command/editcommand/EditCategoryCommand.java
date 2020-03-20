package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.Message.MESSAGE_EDIT_CATEGORY_SUCCESS;

/**
 * <h3>Edit Category Command</h3>
 * A <b>Command</b> to edit a <b>Category</b> in the <b>Category List</b>.
 * @see Command
 * @see Category
 */
public class EditCategoryCommand extends EditCommand {
    public static final String COMMAND_WORD = "edc";
    public static final String FORMAT = COMMAND_WORD +
            " <new category name> -m <module code> -c <old category name> -p <new priority>";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+)?)"),
            Pattern.compile("(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + " [^-]+))"),
            Pattern.compile("(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + " [^-]+))"),
            Pattern.compile("(?<priority>(?:\\s+" + PRIORITY_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^mcp].*|[mcp]\\S+)))")
    };

    private String oldCategoryName;
    private String moduleCode;
    private String newCategoryName;
    private int newPriority;

    public EditCategoryCommand(String oldCategoryName, String moduleCode, String newCategoryName, int newPriority) {
        this.oldCategoryName = oldCategoryName;
        this.moduleCode = moduleCode;
        this.newCategoryName = newCategoryName;
        this.newPriority = newPriority;
    }

    public EditCategoryCommand(String oldCategoryName, String moduleCode, String newCategoryName) {
        this(oldCategoryName, moduleCode, newCategoryName, -1);
    }

    /**
     * Executes an edit on the category.
     *
     * @return The result of the execution
     */
    @Override
    protected CommandResult executeEdit() {
        try {
            Category toEdit = ModuleManager.getCategory(moduleCode, oldCategoryName);
            if (newPriority < 0) {
                newPriority = toEdit.getCategoryPriority();
            }
            ModuleManager.retrieveList(moduleCode).edit(toEdit, newCategoryName, newPriority);
            return new CommandResult(MESSAGE_EDIT_CATEGORY_SUCCESS);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (CategoryManager.DuplicateCategoryException e) {
            return new CommandResult(MESSAGE_DUPLICATE_CATEGORY);
        }
    }

    /**
     * Executes the <b>Edit Category Command</b> to edit a <b>Category</b> with the <code>category name</code>
     * from the <b>Category List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Category
     * @see CategoryManager
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        return executeEdit();
    }
}
