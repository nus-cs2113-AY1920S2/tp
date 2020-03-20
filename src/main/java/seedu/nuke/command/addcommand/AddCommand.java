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

    protected abstract Directory getParentDirectory() throws IncorrectDirectoryLevelException, ModuleManager.ModuleNotFoundException, CategoryManager.CategoryNotFoundException;

    protected Module getBaseModule() throws IncorrectDirectoryLevelException {
        switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
            case MODULE:
                return (Module) DirectoryTraverser.getCurrentDirectory();
            case CATEGORY:
                return (Module) DirectoryTraverser.getCurrentDirectory().getParent();
            case TASK:
                return (Module) DirectoryTraverser.getCurrentDirectory().getParent().getParent();
            case FILE:
                return (Module) DirectoryTraverser.getCurrentDirectory().getParent().getParent().getParent();
            default:
                throw new IncorrectDirectoryLevelException();
        }
    }

    protected Category getBaseCategory() throws IncorrectDirectoryLevelException {
        switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
            case CATEGORY:
                return (Category) DirectoryTraverser.getCurrentDirectory();
            case TASK:
                return (Category) DirectoryTraverser.getCurrentDirectory().getParent();
            case FILE:
                return (Category) DirectoryTraverser.getCurrentDirectory().getParent().getParent();
            default:
                throw new IncorrectDirectoryLevelException();
        }
    }

    protected Task getBaseTask() throws IncorrectDirectoryLevelException {
        switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
            case TASK:
                return (Task) DirectoryTraverser.getCurrentDirectory();
            case FILE:
                return (Task) DirectoryTraverser.getCurrentDirectory().getParent();
            default:
                throw new IncorrectDirectoryLevelException();
        }
    }

}
