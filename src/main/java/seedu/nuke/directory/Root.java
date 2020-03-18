package seedu.nuke.directory;

/**
 * The Root of the Directory, i.e. the starting point of the Directory Tree.
 */
public class Root extends Directory {
    public Root() {
        super();
    }

    @Override
    public Directory getParent() {
        return this.parent;
    }
}
