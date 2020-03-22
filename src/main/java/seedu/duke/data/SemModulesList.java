package seedu.duke.data;

import seedu.duke.module.Module;

public class SemModulesList extends ModuleList {
    private String semName;

    public SemModulesList(String semName) {
        this.semName = semName;
    }

    public String getSem() {
        return semName;
    }

    /**
     * Checks if the moduleName is in the semModulesList.
     * @param moduleName : name of the module to check in the ModulesList.
     */
    public boolean isModuleNameInList(String moduleName) {
        for (Module module: this) {
            if (module.getName().equals(moduleName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isModuleIdInList(String moduleId) {
        for (Module module: this) {
            if (module.getId().equals(moduleId)) {
                return true;
            }
        }
        return false;
    }

}
