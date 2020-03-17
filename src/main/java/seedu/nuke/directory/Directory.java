package seedu.nuke.directory;

public abstract class Directory {
    private Directory father;
    private Directory son;

    public Directory() {
    }

    public Directory(Directory father) {
        this.father = father;
    }

    public Directory getFather() {
        return father;
    }

    public void setFather(Directory father) {
        this.father = father;
    }

    public Directory getSon() {
        return son;
    }

    public void setSon(Directory son) {
        this.son = son;
    }
}
