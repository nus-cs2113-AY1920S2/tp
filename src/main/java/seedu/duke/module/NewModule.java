package seedu.duke.module;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.ModuleList;

public class NewModule extends Module {
    public NewModule(String id, String name, String ... preRequisiteModules) {
        this.id = id;
        this.name = name;
        this.preRequisiteModules = convertFromStringToModuleList(preRequisiteModules);
        this.isIdValid = true;
        this.isNameValid = true;
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
}
