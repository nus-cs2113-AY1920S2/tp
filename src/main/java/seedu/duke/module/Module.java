package seedu.duke.module;

public class Module {
    protected String name;
    protected String id;
    protected boolean isName;
    protected boolean isId;

    /**
     * This is Module's constructor.
     * @param type the type of module identifier.
     * @param identifier user input identifier.
     */
    public Module(String type, String identifier) {
        this.isName = type.equals("name");
        this.isId = type.equals("id");
        if (isName) {
            this.name = identifier;
        } else if (isId) {
            this.id = identifier;
        }
    }

    @Override
    public String toString() {
        String returnString = null;
        if (isId && isName) {
            returnString = "id: " + id + " name: " + name;
        } else if (isName) {
            returnString = "name: " + name;
        } else if (isId) {
            returnString = "id: " + id;
        }
        return returnString;
    }
}
