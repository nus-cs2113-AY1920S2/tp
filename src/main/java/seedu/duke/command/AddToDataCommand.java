package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;
import seedu.duke.data.SemesterList;
import seedu.duke.module.Module;
import seedu.duke.module.NewModule;

public class AddToDataCommand extends AddCommand {

    private NewModule newModule;

    public AddToDataCommand(NewModule newModule) {
        this.newModule = newModule;
    }

    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) {
        addModule(semesterList, availableModulesList);
        Ui.showAddedToDataMessage(newModule.toString());
    }

    private void addModule(SemesterList semesterList, AvailableModulesList availableModulesList) {
        for (Module module : availableModulesList) {
            boolean hasSameId = newModule.getId().equals(module.getId());
            boolean hasSameName = newModule.getName().equals(module.getName());
            if (hasSameId && hasSameName) {
                return;
            } else if (hasSameId) {
                module.updateName(newModule.getName());
            } else if (hasSameName) {
                module.updateId(newModule.getId());
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
     * Update the selected module in SemesterList if the new add-to-data module is in already in SemesterList.
     * @param semesterList SemesterList.
     */
    private void checkSemesterList(SemesterList semesterList) {
        for (SemModulesList sem: semesterList) {
            for (SelectedModule selectedModule: sem) {
                if (selectedModule.getId().equals(newModule.getId())) {
                    selectedModule.setModuleConfig(newModule);
                }
            }
        }
    }
}
