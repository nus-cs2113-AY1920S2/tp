package seedu.nuke.directory;

import seedu.nuke.exception.TraverseDirectoryOutOfBoundsException;

import java.util.Stack;


public abstract class Directory {
    private static Stack<Directory> directoryStack = new Stack<>();
    private static final DirectoryLevel[] DIRECTORY_LEVELS = {
            DirectoryLevel.ROOT, DirectoryLevel.MODULE, DirectoryLevel.CATEGORY,
            DirectoryLevel.TASK, DirectoryLevel.FILE};
    private static int currentLevel = 0;
    private static final int MINIMUM_LEVEL = 0;
    private static final int MAXIMUM_LEVEL = 4;
    private Directory parent;

    /**
     * Constructs the Directory without a parent
     */
    public Directory() {
        this.parent = null;
    }

    /**
     * Constructs the Directory that has a parent
     *
     * @param parent
     *  The parent of the class in the Directory
     */
    public Directory(Directory parent) {
        this.parent = parent;
    }

    /**
     * Returns the parent of the class in the Directory
     *
     * @return
     *  The parent of the class in the Directory
     */
    public Directory getParent() {
        return parent;
    }

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
     * Traverse one level up in the directory.
     *
     * @param nextLevel
     *  The next level of the directory
     * @throws TraverseDirectoryOutOfBoundsException
     *  If the traversal will result in traversing out of the directory
     */
    public static void traverseUp(Directory nextLevel) throws TraverseDirectoryOutOfBoundsException {
        if (currentLevel >= MAXIMUM_LEVEL) {
            throw new TraverseDirectoryOutOfBoundsException();
        }
        currentLevel++;
        directoryStack.push(nextLevel);
    }

    /**
     * Traverse one level down in the directory.
     *
     * @throws TraverseDirectoryOutOfBoundsException
     *  If the traversal will result in traversing out of the directory
     */
    public static void traverseDown() throws TraverseDirectoryOutOfBoundsException {
        if (currentLevel <= MINIMUM_LEVEL) {
            throw new TraverseDirectoryOutOfBoundsException();
        }
        currentLevel--;
        directoryStack.pop();
    }


}
