package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.exception.InputException;
import seedu.duke.exception.RuntimeException;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;
import seedu.duke.data.SemesterList;
import seedu.duke.module.Module;
import seedu.duke.module.NewModule;

public class AddToAvailableCommand extends AddCommand {

    private NewModule newModule;

    public AddToAvailableCommand(NewModule newModule) {
        this.newModule = newModule;
    }

    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) throws RuntimeException {
        addModule(semesterList, availableModulesList);
        Ui.showAddedToDataMessage(newModule.toString());
        super.execute(semesterList, availableModulesList);
    }

    /**
     * adds a module to the user's available module list.
     * @param semesterList : user's current semester list.
     * @param availableModulesList : user's current available modules list.
     */
    private void addModule(SemesterList semesterList, AvailableModulesList availableModulesList)
            throws RuntimeException {
        for (Module module : availableModulesList) {
            boolean hasSameId = newModule.getId().equals(module.getId());
            boolean hasSameName = newModule.getName().equals(module.getName());
            if (hasSameId && hasSameName) {
                throw new RuntimeException("This module's name and Id has already "
                        + "been added to the available modules list");
            } else if (hasSameId) {
                String oldName = module.getName();
                String id = module.getId();
                module.updateName(newModule.getName());
                throw new RuntimeException(String.format("This module's ID <%s> is in available modules list.", id)
                        + System.lineSeparator()
                        + String.format("So we update the module's name: <%s> -> <%s>", oldName, module.getName()));
            } else if (hasSameName) {
                String oldId = module.getId();
                String name = module.getName();
                module.updateId(newModule.getId());
                throw new RuntimeException(String.format("This module's name <%s> is in available modules list.", name)
                        + System.lineSeparator()
                        + String.format("So we update the module's ID: <%s> -> <%s>", oldId, module.getId()));
            }
        }
        availableModulesList.add(newModule);
        checkSemesterList(semesterList);
    }

    @Override
    public String toString() {
        return COMMAND_WORD + " " + newModule;
    }

    /**
     * Update the selected module in SemesterList if the newly added module is already in SemesterList.
     * @param semesterList SemesterList.
     */
    private void checkSemesterList(SemesterList semesterList) {
        for (SemModulesList sem: semesterList) {
            for (SelectedModule selectedModule: sem) {
                boolean hasSameModuleId = selectedModule.getId().equals(newModule.getId());
                boolean hasSameModuleName = selectedModule.getName().equals(newModule.getName());
                if (hasSameModuleId || hasSameModuleName) {
                    selectedModule.setModuleConfig(newModule);
                }
            }
        }
    }
}
