package seedu.nuke.data;

public class Root extends Directory{
    public static final String name = "root";

    public Root(Directory fatherDir) {
        super(null, this.name);
    }

    @Override
    public void setSonDir() {

    }

    @Override
    public Directory getFatherDir() {
        return null;
    }

    @Override
    public Directory getSonDir() {
        return null;
    }
}
