package seedu.nuke.command.addcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.storage.StorageManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.MODULE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_CATEGORY;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_CATEGORY_EXCEED_LIMIT;
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
    public static final String FORMAT = COMMAND_WORD + " <category name> -m <module code> [ -p <priority> ]";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Add a new category to a module\n"
            + "Format: %s\n"
            + "Example: addc Project -m CS2113T -p 4\n",
            COMMAND_WORD, FORMAT);
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "\\s+\\S+)?)"
            + "(?<invalid>.*)"
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

    private boolean exceedLengthLimit() {
        return categoryName.length() > 15;
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
        if (exceedLengthLimit()) {
            return new CommandResult(MESSAGE_CATEGORY_EXCEED_LIMIT);
        }
        try {
            Module parentModule = DirectoryTraverser.getModuleDirectory(moduleCode);
            Category toAdd = new Category(parentModule, categoryName, categoryPriority);
            parentModule.getCategories().add(toAdd);
            StorageManager.setIsSave();
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
