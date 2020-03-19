package seedu.duke.module;

public class SelectedModule extends Module {

    /**
     * This is Module's constructor.
     *
     * @param type             the type of module identifier.
     * @param moduleIdentifier the identifier which can be either module's name or module's id.
     * @param semester         the module's semester.
     */
    public SelectedModule(String type, String moduleIdentifier, String semester) {
        super(type, moduleIdentifier, semester);
    }

    /**
     * This is Module's constructor.
     *
     * @param module           the module from available module list.
     */
    public SelectedModule(Module module) {
        //super(); // not sure if commenting this out will affect anything
        super.name = module.name;
        super.id = module.id;
        super.semester = module.semester;
        super.description = module.description;
        super.isSUable = module.isSUable;
        super.preRequisiteModules = module.preRequisiteModules;
        super.isNameValid = module.isNameValid;
        super.isIdValid = module.isIdValid;
        super.isDone = module.isDone;
    }

    public void setModuleConfig(Module availableModule) {
        super.name = availableModule.name;
        super.id = availableModule.id;
        super.description = availableModule.description;
        super.isSUable = availableModule.isSUable;
        super.preRequisiteModules = availableModule.preRequisiteModules;
        super.isNameValid = availableModule.isNameValid;
        super.isIdValid = availableModule.isIdValid;
    }

    @Override
    public String toString() {
        return super.getIcon() + " " + super.toString();
    }
}
