package seedu.nuke.directory;

public abstract class Stuff {
    private Stuff father;
    private Stuff son;

    public Stuff(Stuff father) {
        this.father = father;
    }

    public Stuff getFather() {
        return father;
    }

    public void setFather(Stuff father) {
        this.father = father;
    }

    public Stuff getSon() {
        return son;
    }

    public void setSon(Stuff son) {
        this.son = son;
    }
}
