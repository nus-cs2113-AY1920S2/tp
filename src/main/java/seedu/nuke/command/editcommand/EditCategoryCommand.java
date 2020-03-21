package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.regex.Pattern;

import static seedu.nuke.directory.DirectoryTraverser.getBaseCategory;
import static seedu.nuke.directory.DirectoryTraverser.getBaseModule;
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
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)" +
            "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<invalid>(?:\\s+-.*)*)"
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

    /**
     * Returns the base category level directory of the current Directory.
     *
     * @return
     *  The base category level directory of the current Directory
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the category level directory
     * @throws ModuleManager.ModuleNotFoundException
     *  If the module with the module code is not found in the Module List
     * @throws CategoryManager.CategoryNotFoundException
     *  If the category with the category name is not found in the Category List
     */
    protected Category getBaseCategoryDirectory()
            throws IncorrectDirectoryLevelException, ModuleManager.ModuleNotFoundException,
            CategoryManager.CategoryNotFoundException {
        if (moduleCode.isEmpty()) {
            if (oldCategoryName.isEmpty()) {
                return getBaseCategory();
            }
            return getBaseModule().getCategories().getCategory(oldCategoryName);
        }
        if (oldCategoryName.isEmpty()) {
            if (!getBaseModule().isSameModule(moduleCode)) {
                throw new IncorrectDirectoryLevelException();
            }
            return getBaseCategory();
        }
        return ModuleManager.getCategory(moduleCode, oldCategoryName);
    }

    /**
     * Executes an edit on the category.
     *
     * @return The result of the execution
     */
    @Override
    protected CommandResult executeEdit() {
        try {
            Category toEdit = getBaseCategoryDirectory();
            fillAllAttributes(toEdit);
            ModuleManager.retrieveList(moduleCode).edit(toEdit, newCategoryName, newPriority);
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
