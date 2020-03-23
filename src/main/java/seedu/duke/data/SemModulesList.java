package seedu.duke.data;

import seedu.duke.module.Module;
import seedu.duke.module.SelectedModule;

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

    /**
     * To retrieve a module from the modules in that semester from the Id or the Name of the module.
     * Assumes that the user knows that the module already exists in the list of modules.
     * @param moduleIdentifier Id or Name of module.
     * @return Module that corresponds to the modules identifier inputted.
     */
    public Module getModule(String moduleIdentifier) {
        for (Module module : this) {
            if (module.getId().equals(moduleIdentifier) || module.getName().equals(moduleIdentifier)) {
                return module;
            }
        }
        return null;
    }

    public void deleteModule(String moduleIdentifier) {
        for (Module module : this) {
            if (module.getName().equals(moduleIdentifier) || module.getId().equals(moduleIdentifier)) {
                this.remove(module);
                break;
            }
        }
    }
}
