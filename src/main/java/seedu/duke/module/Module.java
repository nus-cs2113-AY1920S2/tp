package seedu.duke.module;

import seedu.duke.data.ModuleList;

public class Module {
    protected String name;
    protected String id;
    protected String semester;
    protected String description;
    protected Boolean isSUable;
    protected ModuleList preRequisiteModules;
    protected boolean isNameValid;
    protected boolean isIdValid;
    protected boolean isDone;


    /**
     * This is Module's constructor.
     * @param type the type of module identifier.
     * @param moduleIdentifier the identifier which can be either module's name or module's id.
     * @param semester the module's semester.
     */
    public Module(String type, String moduleIdentifier, String semester) {
        this.isNameValid = type.equals("name");
        this.isIdValid = type.equals("id");
        this.semester = semester;
        if (isNameValid) {
            this.name = moduleIdentifier;
            this.id = "unnamed";
        } else if (isIdValid) {
            this.id = moduleIdentifier;
            this.name = "unnamed";
        }
        this.isDone = false;
    }

    protected Module(){

    }

    @Override
    public String toString() {
        String returnString = null;
        if (isIdValid && isNameValid) {
            returnString = "ID: " + id + " Name: " + name;
        } else if (isNameValid) {
            returnString = "Name: " + name;
        } else if (isIdValid) {
            returnString = "ID: " + id;
        }
        return returnString + " | Sem: " + semester;
    }

    public String getSem() {
        return semester;
    }

    public String getName() {
        return name;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public String getId() {
        return this.id;
    }

    /**
     * Returns the icon [✓] when this module is done, and returns [✗] if the module is not done.
     */
    public String getIcon() {
        if (this.isDone) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    public boolean getDone() {
        return this.isDone;
    }
}
