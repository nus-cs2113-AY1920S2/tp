package seedu.duke.module;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.ModuleList;

/**
 * Mainly for the input of any new modules to the database.
 * In order to differentiate the constructors for normal Modules and new Modules with prerequisites to be added in.
 */

public class NewModule extends Module {

    public NewModule(String id, String name, int moduleCredit, String... preRequisiteModules) {
        super("Both", id, name, moduleCredit);
        this.preRequisiteModules = convertFromStringToModuleList(preRequisiteModules);
    }

    public static ModuleList convertFromStringToModuleList(String[] moduleIdentifiers) {
        ModuleList modules = new ModuleList();
        for (String moduleIdentifier : moduleIdentifiers) {
            for (Module module : AvailableModulesList.availableModulesList) {
                if (moduleIdentifier.equals(module.getId()) || moduleIdentifier.equals(module.getName())) {
                    modules.add(module);
                }
            }
        }
        return modules;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(String.format("ID: %s Name: %s | Modular Credit: %d",
                id, name, moduleCredit));
        boolean hasPreReqModule = false;
        StringBuilder prereq = new StringBuilder(" | Prerequisites:");
        for (Module preReqModule : this.preRequisiteModules) {
            prereq.append(" ").append(preReqModule.getId());
            hasPreReqModule = true;
        }
        if (hasPreReqModule) {
            output.append(prereq);
        }
        return output.toString();
    }
}
