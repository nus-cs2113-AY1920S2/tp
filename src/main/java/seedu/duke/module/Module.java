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
    protected String grade;
    protected double cap;
    protected int modularCredit;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
        switch (grade) {
        case "A+":
        case "A":
            this.cap = 5.0;
            break;
        case "A-":
            this.cap = 4.5;
            break;
        case "B+":
            this.cap = 4.0;
            break;
        case "B":
            this.cap = 3.5;
            break;
        case "B-":
            this.cap = 3.0;
            break;
        case "C+":
            this.cap = 2.5;
            break;
        case "C":
            this.cap = 2.0;
            break;
        case "D+":
            this.cap = 1.5;
            break;
        case "D":
            this.cap = 1.0;
            break;
        case "F":
            this.cap = 0.0;
            break;
        case "CS":
            this.cap = 0.0;
            break;
        case "CU":
            this.cap = 0.0;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + grade);
        }
    }

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

    public ModuleList getPreRequisiteModules() {
        return preRequisiteModules;
    }

    public String getPreReqModulesID() {
        String preReqModulesList = new String();
        boolean hasNoPreReqModules = preRequisiteModules.size() == 0;
        if (hasNoPreReqModules) {
            preReqModulesList = ("None");
        }
        for (Module preReqModule : preRequisiteModules) {
            preReqModulesList += preReqModule.getId() + " ";
        }
        return preReqModulesList;
    }

}
