package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.storage.StorageManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.CATEGORY_PREFIX;
import static seedu.nuke.parser.Parser.MODULE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_CATEGORY;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_CATEGORY_EXCEED_LIMIT;
import static seedu.nuke.util.Message.MESSAGE_EDIT_CATEGORY_SUCCESS;

/**
 * <h3>Edit Category Command</h3>
 * A <b>Command</b> to edit a <b>Category</b> in the <b>Category List</b>.
 * @see Command
 * @see Category
 */
public class EditCategoryCommand extends EditCommand {
    public static final String COMMAND_WORD = "edc";
    public static final String FORMAT = COMMAND_WORD
            + " <category name> -m <module code> { -c <new category name> -p <new priority> }";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Edit the name and priority of a category\n"
            + "Format: %s\n"
            + "Example: edc Priject -m CS2113T -c Project -p 3\n",
            COMMAND_WORD, FORMAT);
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "\\s+\\S+)?)"
            + "(?<invalid>.*)"
    );

    private String oldCategoryName;
    private String moduleCode;
    private String newCategoryName;
    private int newPriority;

    /**
     * Constructs the command to edit a category.
     *
     * @param oldCategoryName
     *  The name of the category to be edited
     * @param moduleCode
     *  The module code of the module containing the category to be edited
     * @param newCategoryName
     *  The new name of the category if any
     * @param newPriority
     *  The new priority of the category if any
     */
    public EditCategoryCommand(String oldCategoryName, String moduleCode, String newCategoryName, int newPriority) {
        this.oldCategoryName = oldCategoryName;
        this.moduleCode = moduleCode;
        this.newCategoryName = newCategoryName;
        this.newPriority = newPriority;
    }

    /**
     * Constructs the command to edit a category without a priority.
     *
     * @param oldCategoryName
     *  The name of the category to be edited
     * @param moduleCode
     *  The module code of the module containing the category to be edited
     * @param newCategoryName
     *  The new name of the category if any
     */
    public EditCategoryCommand(String oldCategoryName, String moduleCode, String newCategoryName) {
        this(oldCategoryName, moduleCode, newCategoryName, -1);
    }

    /**
     * Fills in all missing attributes needed to edit the category.
     *
     * @param toEdit
     *  The category to be edited
     */
    private void fillAllAttributes(Category toEdit) {
        moduleCode = toEdit.getParent().getModuleCode();
        if (newCategoryName.isEmpty()) {
            newCategoryName = toEdit.getCategoryName();
        }
        if (newPriority < 0) {
            newPriority = toEdit.getCategoryPriority();
        }
    }

    private boolean isExceedLengthLimit() {
        return newCategoryName.length() > 15;
    }

    /**
     * Edits the category.
     *
     * @param toEdit
     *  The category to edit
     * @throws CategoryManager.DuplicateCategoryException
     *  If the new category name is duplicated
     */
    @Override
    protected void edit(Directory toEdit) throws CategoryManager.DuplicateCategoryException {
        ((Category) toEdit).getParent().getCategories().edit((Category) toEdit, newCategoryName, newPriority);
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
        if (isExceedLengthLimit()) {
            return new CommandResult(MESSAGE_CATEGORY_EXCEED_LIMIT);
        }
        try {
            Category toEdit = DirectoryTraverser.getCategoryDirectory(moduleCode, oldCategoryName);
            fillAllAttributes(toEdit);
            edit(toEdit);
            StorageManager.setIsSave();
            return new CommandResult(MESSAGE_EDIT_CATEGORY_SUCCESS);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.CategoryNotFoundException e) {
            return new CommandResult(MESSAGE_CATEGORY_NOT_FOUND);
        } catch (CategoryManager.DuplicateCategoryException e) {
            return new CommandResult(MESSAGE_DUPLICATE_CATEGORY);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        }
    }
}
