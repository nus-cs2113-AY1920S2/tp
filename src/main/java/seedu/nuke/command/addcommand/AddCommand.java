package seedu.nuke.command.addcommand;

import seedu.nuke.command.Command;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.*;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

/**
 * <h3>Add Command</h3>
 * A general <b>Command</b> to add a Directory.
 *
 * @see Command
 */
public abstract class AddCommand extends Command {

    /**
     * Returns the parent directory of the Directory to be added.
     *
     * @return
     *  The parent directory of the Directory to be added
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the parent directory
     * @throws ModuleManager.ModuleNotFoundException
     *  If the module with the module code is not found in the Module List
     * @throws CategoryManager.CategoryNotFoundException
     *  If the category with the category name is not found in the Module List
     */
    protected abstract Directory getParentDirectory()
            throws IncorrectDirectoryLevelException, ModuleManager.ModuleNotFoundException,
            CategoryManager.CategoryNotFoundException;



}
