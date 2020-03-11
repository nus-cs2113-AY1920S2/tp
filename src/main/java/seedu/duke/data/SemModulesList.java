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

    public boolean isInList(String moduleName, SemModulesList semModulesList) {
        for (Module module: semModulesList) {
            if (module.getName().equals(moduleName)) {
                return true;
            }
        }
        return false;
    }

}
