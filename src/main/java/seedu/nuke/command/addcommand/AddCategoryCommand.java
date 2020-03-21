package seedu.nuke.command.addcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.regex.Pattern;

import static seedu.nuke.directory.DirectoryTraverser.getBaseModule;
import static seedu.nuke.parser.Parser.MODULE_CODE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.util.ExceptionMessage.*;
import static seedu.nuke.util.Message.messageAddCategorySuccess;

/**
 * <h3>Add Category Command</h3>
 * A <b>Command</b> to add a <b>Category</b> for the user's <i>tasks</i>.
 *
 * @see Command
 * @see Category
 */
public class AddCategoryCommand extends AddCommand {
    public static final String COMMAND_WORD = "addc";
    public static final String FORMAT = COMMAND_WORD + " <category name> -m <module code> -p <priority>";
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)+)" +
            "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)" +
            "(?<invalid>(?:\\s+-.*)*)"
    );

    private String moduleCode;
    private String categoryName;
    private int categoryPriority;

    /**
     * Constructs the command to add a category.
     *
     * @param moduleCode
     *  The module code of the module to add the category
     * @param categoryName
     *  The name of the category
     * @param categoryPriority
     *  The priority of the category
     */
    public AddCategoryCommand(String moduleCode, String categoryName, int categoryPriority) {
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.categoryPriority = categoryPriority;
    }

    /**
     * Constructs the command to add a category without a priority.
     *
     * @param moduleCode
     *  The module code of the module to add the category
     * @param categoryName
     *  The name of the category
     */
    public AddCategoryCommand(String moduleCode, String categoryName) {
        this(moduleCode, categoryName, 0);
    }

    /**
     * Returns the parent module level directory of the Directory to be added.
     *
     * @return
     *  The parent module level directory of the Directory to be added
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the parent module level directory
     * @throws ModuleManager.ModuleNotFoundException
     *  If the module with the module code is not found in the Module List
     */
    protected Module getParentDirectory() throws IncorrectDirectoryLevelException, ModuleManager.ModuleNotFoundException {
        if (moduleCode.isEmpty()) {
            return getBaseModule();
        } else {
            return ModuleManager.getModule(moduleCode);
        }
    }

    /**
     * Executes the <b>Add Category Command</b> to add a <b>Category</b> into the <b>Category List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Category
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            Module parentModule = getParentDirectory();
            Category toAdd = new Category(parentModule, categoryName, categoryPriority);
            parentModule.getCategories().add(toAdd);
            return new CommandResult(messageAddCategorySuccess(categoryName));
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.DuplicateCategoryException e) {
            return new CommandResult(MESSAGE_DUPLICATE_CATEGORY);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        }
    }
}
