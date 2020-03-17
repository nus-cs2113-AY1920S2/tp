package seedu.nuke.directory;

import seedu.nuke.common.DataType;
import seedu.nuke.exception.TraverseDirectoryOutOfBoundsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Stack;


public abstract class Directory {
    private static Stack<Directory> directoryStack = new Stack<>();
    private static final ArrayList<DataType> LEVEL_ORDER =
            new ArrayList<>(Arrays.asList(DataType.MODULE, DataType.CATEGORY, DataType.TASK, DataType.FILE));
    private static ListIterator<DataType> currentLevel = LEVEL_ORDER.listIterator();
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
     * Traverse one level up in the directory.
     *
     * @param nextLevel
     *  The next level of the directory
     * @return
     *  The data type of the added level
     * @throws TraverseDirectoryOutOfBoundsException
     *  If the traversal will result in traversing out of the directory
     */
    public static DataType traverseUp(Directory nextLevel) throws TraverseDirectoryOutOfBoundsException {
        if (!currentLevel.hasNext()) {
            throw new TraverseDirectoryOutOfBoundsException();
        }
        directoryStack.push(nextLevel);
        return currentLevel.next();
    }

    /**
     * Traverse one level down in the directory.
     *
     * @return
     *  The data type of the previous level
     * @throws TraverseDirectoryOutOfBoundsException
     *  If the traversal will result in traversing out of the directory
     */
    public static DataType traverseDown() throws TraverseDirectoryOutOfBoundsException {
        if (!currentLevel.hasPrevious()) {
            throw new TraverseDirectoryOutOfBoundsException();
        }
        directoryStack.pop();
        return currentLevel.previous();
    }
}
