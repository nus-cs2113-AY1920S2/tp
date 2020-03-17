package seedu.nuke.data;

public abstract class Directory {
    public Directory fatherDir;
    public Directory sonDir;
    public String directoryCode;

    public Directory(Directory fatherDir, String directoryCode) {
        this.fatherDir = fatherDir;
        this.directoryCode = directoryCode;
    }

    public abstract void setSonDir();

    public abstract Directory getFatherDir();
    public abstract Directory getSonDir();
}
