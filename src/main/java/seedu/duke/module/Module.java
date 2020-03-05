package seedu.duke.module;

public class Module {
    protected String name;
    protected String id;
    protected String semester;
    protected boolean isName;
    protected boolean isId;


    /**
     * This is Module's constructor.
     * @param type the type of module identifier.
     * @param identifier user input identifier.
     */
    public Module(String type, String identifier, String semester) {
        this.isName = type.equals("name");
        this.isId = type.equals("id");
        this.semester = semester;
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
            returnString = "ID: " + id + " Name: " + name;
        } else if (isName) {
            returnString = "Name: " + name;
        } else if (isId) {
            returnString = "ID: " + id;
        }
        return returnString + " / Semester: " + semester;
    }
}
