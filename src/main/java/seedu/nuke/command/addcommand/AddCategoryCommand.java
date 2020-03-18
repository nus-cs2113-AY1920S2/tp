package seedu.nuke.command.addcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;

import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.MODULE_CODE_PREFIX;
import static seedu.nuke.parser.Parser.PRIORITY_PREFIX;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_CATEGORY;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
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
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+))"),
            Pattern.compile("(?<moduleCode>(?:" + MODULE_CODE_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<priority>(?:" + PRIORITY_PREFIX + " [^-]+)?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^mp].*|[mp]\\S+))*)")
    };

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
     * Executes the <b>Add Category Command</b> to add a <b>Category</b> into the <b>Category List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Category
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            Module parentModule = ModuleManager.getModule(moduleCode);
            Category toAdd = new Category(parentModule, categoryName, categoryPriority);
            ModuleManager.retrieveList(moduleCode).add(toAdd);
            return new CommandResult(messageAddCategorySuccess(categoryName));
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (CategoryManager.DuplicateCategoryException e) {
            return new CommandResult(MESSAGE_DUPLICATE_CATEGORY);
        }
    }
}
