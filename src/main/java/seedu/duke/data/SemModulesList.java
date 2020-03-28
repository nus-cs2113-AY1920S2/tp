package seedu.duke.data;

import seedu.duke.module.Module;
import seedu.duke.module.SelectedModule;

import java.util.ArrayList;


public class SemModulesList extends ArrayList<SelectedModule> {
    private String semester;

    public SemModulesList(String semester) {
        this.semester = semester;
    }

    public String getSem() {
        return semester;
    }

    public boolean isInList(String moduleIdentifier) {
        for (SelectedModule module: this) {
            if (module.getName().equals(moduleIdentifier) || module.getId().equals(moduleIdentifier)) {
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
