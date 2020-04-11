package seedu.duke.module;

import seedu.duke.data.ModuleList;

public abstract class Module {
    protected String name;
    protected String id;
    protected String description;
    protected Boolean isSUable;
    protected ModuleList preRequisiteModules;
    protected boolean isNameValid;
    protected boolean isIdValid;
    protected int moduleCredit;


    /**
     * This is Module's constructor.
     * @param type the type of module identifier.
     * @param moduleIdentifier the identifier which can be either module's name or module's id.
     * @param moduleCredit     the module's amount of module credit awarded
     */
    public Module(String type, String moduleIdentifier, int moduleCredit) {
        this.moduleCredit = moduleCredit;
        this.isNameValid = type.equals("name");
        this.isIdValid = type.equals("id");
        if (isNameValid) {
            this.name = moduleIdentifier;
            this.id = "unnamed";
        } else if (isIdValid) {
            this.id = moduleIdentifier;
            this.name = "unnamed";
        }
    }

    public Module(String type, String moduleId, String moduleName, int moduleCredit) {
        this.moduleCredit = moduleCredit;
        this.isNameValid = true;
        this.isIdValid = true;
        this.name = moduleName;
        this.id = moduleId;
    }

    public Module() {

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
        return returnString + " | Module Credit: " + moduleCredit;
    }


    public String getName() {
        if (name == null) {
            name = "unnamed";
            return name;
        }
        return name;
    }

    public String getId() {
        return this.id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateId(String id) {
        this.id = id;
    }

    public int getModuleCredit() {
        return this.moduleCredit;
    }

    public ModuleList getPreRequisiteModules() {
        return preRequisiteModules;
    }

    public String getPreReqModulesID() {
        String preReqModulesStringList = "";
        boolean hasNoPreReqModules = preRequisiteModules.size() == 0;
        if (hasNoPreReqModules) {
            preReqModulesStringList = ("None");
        }
        for (Module preReqModule : preRequisiteModules) {
            preReqModulesStringList += preReqModule.getId() + " ";
        }
        return preReqModulesStringList;
    }

    public String getPreReqModulesString() {
        return null;
    }

    public abstract String toStorageString();

    @Override
    public boolean equals(Object object) {
        assert (object instanceof Module);
        Module moduleObject = (Module) object;
        boolean sameId = false;
        if (moduleObject.getId().equals(this.getId())) {
            sameId = true;
        }
        boolean sameName = false;
        if (moduleObject.getName().equals(this.getName())) {
            sameName = true;
        }
        boolean sameMC = false;
        if (moduleObject.getModuleCredit() == this.getModuleCredit()) {
            sameMC = true;
        }
        return sameId && sameMC && sameName;
    }

    public boolean isNameValid() {
        return isNameValid;
    }

    public boolean isIdValid() {
        return isIdValid;
    }
}
