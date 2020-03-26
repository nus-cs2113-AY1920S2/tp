package seedu.duke.data;

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


    /**
     * Checks if the moduleIdentifier is in the semModulesList.
     * @param moduleIdentifier : name of the module to check in the ModulesList.
     */
    public boolean isInList(String moduleIdentifier) {
        for (SelectedModule module: this) {
            if (module.getName().equals(moduleIdentifier) || module.getId().equals(moduleIdentifier)) {
                return true;
            }
        }
        return false;
    }
}
