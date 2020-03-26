package seedu.duke.module;

public class SelectedModule extends Module {

    protected boolean isDone;
    private Grading grade;
    protected String semester;

    /**
     * This is Module's constructor.
     *
     * @param type             the type of module identifier.
     * @param moduleIdentifier the identifier which can be either module's name or module's id.
     * @param semester         the module's semester.
     * @param moduleCredit     the amount of MC the module awards
     */
    public SelectedModule(String type, String moduleIdentifier, String semester, int moduleCredit) {
        super(type, moduleIdentifier, moduleCredit);
        this.semester = semester;
        this.isDone = false;
    }

    /**
     * This is Module's constructor.
     *
     * @param type             the type of module identifier.
     * @param moduleId         the module's id
     * @param moduleName       the module's name
     * @param semester         the module's semester.
     * @param moduleCredit     the module's amount of module credit awarded
     */
    public SelectedModule(String type, String moduleId, String moduleName, String semester, int moduleCredit) {
        super(type, moduleId, moduleName, moduleCredit);
        this.semester = semester;
        this.isDone = false;
    }

    public void setModuleConfig(Module availableModule) {
        this.name = availableModule.name;
        this.id = availableModule.id;
        this.description = availableModule.description;
        this.isSUable = availableModule.isSUable;
        this.preRequisiteModules = availableModule.preRequisiteModules;
        this.isNameValid = availableModule.isNameValid;
        this.isIdValid = availableModule.isIdValid;
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

    public void setAsDone(Grading grade) {
        this.isDone = true;
        this.grade = grade;
    }

    public String getSem() {
        return semester;
    }

    public String announceAdded() {
        return super.toString() + " | Sem: " + semester;
    }

    public Grading getGrade() {
        return this.grade;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return this.getIcon() + " " + super.toString() + " | Sem: " + semester
                    + " | Grade: " + grade.getGrade();
        } else {
            return this.getIcon() + " " + super.toString() + " | Sem: " + semester;
        }
    }
}
