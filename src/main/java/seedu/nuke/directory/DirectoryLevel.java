package seedu.nuke.directory;

public enum DirectoryLevel {
    NONE,
    ROOT,
    MODULE,
    CATEGORY,
    TASK,
    FILE,
    TAG {
        @Override
        public DirectoryLevel next() {
            return NONE;
        }
    };

    public DirectoryLevel next() {
        return values()[ordinal() + 1];
    }
}
