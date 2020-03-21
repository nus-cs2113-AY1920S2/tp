package seedu.nuke.directory;

import seedu.nuke.data.ModuleManager;
import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.DirectoryTraversalOutOfBoundsException;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.Stack;

public class DirectoryTraverser {
    private static Stack<Directory> directoryStack = new Stack<>();
    private static final DirectoryLevel[] DIRECTORY_LEVELS = {
            DirectoryLevel.ROOT, DirectoryLevel.MODULE, DirectoryLevel.CATEGORY,
            DirectoryLevel.TASK, DirectoryLevel.FILE};
    private static int currentLevel = 0;
    private static final int MINIMUM_LEVEL = 0;
    private static final int MAXIMUM_LEVEL = 4;

    /**
     * Returns the current subdirectory.
     *
     * @return
     *  The current subdirectory
     */
    public static Directory getCurrentDirectory() {
        return directoryStack.peek();
    }

    /**
     * Returns the current subdirectory level.
     *
     * @return
     *  The current subdirectory level
     */
    public static DirectoryLevel getCurrentDirectoryLevel() {
        return DIRECTORY_LEVELS[currentLevel];
    }

    /**
     * Traverse one level down in the directory.
     *
     * @param nextLevel
     *  The next level of the directory
     * @throws DirectoryTraversalOutOfBoundsException
     *  If the traversal will result in traversing out of the directory
     */
    public static void traverseDown(Directory nextLevel) throws DirectoryTraversalOutOfBoundsException {
        if (currentLevel >= MAXIMUM_LEVEL) {
            throw new DirectoryTraversalOutOfBoundsException();
        }
        currentLevel++;
        directoryStack.push(nextLevel);
    }

    /**
     * Traverse one level up in the directory.
     *
     * @throws DirectoryTraversalOutOfBoundsException
     *  If the traversal will result in traversing out of the directory
     */
    public static void traverseUp() throws DirectoryTraversalOutOfBoundsException {
        if (currentLevel <= MINIMUM_LEVEL) {
            throw new DirectoryTraversalOutOfBoundsException();
        }
        currentLevel--;
        directoryStack.pop();
    }

    /**
     * Finds the next directory to traverse down into
     *
     * @param nextDirectoryName
     *  The name of the next directory
     * @return
     *  The next directory if found
     * @throws DataNotFoundException
     *  If the next directory cannot be found
     * @throws DirectoryTraversalOutOfBoundsException
     *  If the traversal will result in traversing out of the directory
     */
    public static Directory findNextDirectory(String nextDirectoryName)
            throws DataNotFoundException, DirectoryTraversalOutOfBoundsException {
        switch (getCurrentDirectoryLevel()) {
            case ROOT:
                return ModuleManager.getModule(nextDirectoryName);
            case MODULE:
                return ((Module) getCurrentDirectory()).getCategories().getCategory(nextDirectoryName);
            case CATEGORY:
                return ((Category) getCurrentDirectory()).getTasks().getTask(nextDirectoryName);
            case TASK:
                return ((Task) getCurrentDirectory()).getFiles().getFile(nextDirectoryName);
            default:
                throw new DirectoryTraversalOutOfBoundsException();
        }
    }

    /**
     * Returns the full path to the current directory from the root.
     *
     * @return
     *  The full path to the current directory
     */
    public static String getFullPath() {
        StringBuilder path = new StringBuilder("root");

        switch (getCurrentDirectoryLevel()) {
            case MODULE:
                Module currentModuleDirectory = (Module) getCurrentDirectory();
                path.append(String.format(" / %s", currentModuleDirectory.getModuleCode()));
                break;

            case CATEGORY:
                Category currentCategoryDirectory = (Category) getCurrentDirectory();
                path.append(String.format(" / %s / %s",
                        currentCategoryDirectory.getParent().getModuleCode(),
                        currentCategoryDirectory.getCategoryName()));
                break;

            case TASK:
                Task currentTaskDirectory = (Task) getCurrentDirectory();
                path.append(String.format(" / %s / %s / %s",
                        currentTaskDirectory.getParent().getParent().getModuleCode(),
                        currentTaskDirectory.getParent().getCategoryName(),
                        currentTaskDirectory.getDescription()));
                break;

            default:
                break;
        }

        return path.toString();
    }


    /**
     * Returns the base module level directory of the current directory.
     *
     * @return
     *  The base module level directory of the current directory
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the base module level directory
     */
    public static Module getBaseModule() throws IncorrectDirectoryLevelException {
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

    /**
     * Returns the base category level directory of the current directory.
     *
     * @return
     *  The base category level directory of the current directory
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the base category level directory
     */
    public static Category getBaseCategory() throws IncorrectDirectoryLevelException {
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

    /**
     * Returns the base task level directory of the current directory.
     *
     * @return
     *  The base task level directory of the current directory
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the base task level directory
     */
    public static Task getBaseTask() throws IncorrectDirectoryLevelException {
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
