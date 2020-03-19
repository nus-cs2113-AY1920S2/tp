package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.ui.Ui;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.module.Module;
import seedu.duke.module.NewModule;

public class AddToDataCommand extends AddCommand {

    private NewModule newModule;

    public AddToDataCommand(NewModule newModule) {
        this.newModule = newModule;
    }

    public void execute(SelectedModulesList selectedModulesList, AvailableModulesList availableModulesList) {
        addModule(availableModulesList);
        Ui.showAddedToDataMessage(newModule.toString());
    }

    private void addModule(AvailableModulesList availableModulesList) {
        for (Module module : availableModulesList) {
            boolean hasSameId = newModule.getId().equals(module.getId());
            boolean hasSameName = newModule.getName().equals(module.getName());
            if (hasSameId || hasSameName) {
                return;
            }
        }
        availableModulesList.add(newModule);
    }

    @Override
    public String toString() {
        return COMMAND_WORD + " " + newModule;
    }
}
