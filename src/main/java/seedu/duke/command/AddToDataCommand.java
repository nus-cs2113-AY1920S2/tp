package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.exception.InputException;
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
        try {
            addModule(semesterList, availableModulesList);
        } catch (InputException e) {
            e.printStackTrace();
        }
        Ui.showAddedToDataMessage(newModule.toString());
    }

    /**
     *
     * adds a module to the user's available module list.
     * @param semesterList : user's current semester list.
     * @param availableModulesList : user's current available modules list.
     */
    private void addModule(SemesterList semesterList, AvailableModulesList availableModulesList) throws InputException {
        for (Module module : availableModulesList) {
            boolean hasSameId = newModule.getId().equals(module.getId());
            boolean hasSameName = newModule.getName().equals(module.getName());
            if (hasSameId && hasSameName) {
                throw new InputException("This module's name and Id has already "
                        + "been added to the available modules list");
            } else if (hasSameId) {
                module.updateName(newModule.getName());
            } else if (hasSameName) {
                module.updateId(newModule.getId());
            } else {
                throw new InputException("Seems like the format is wrong",
                        "add id/[module code] n/[name of module] mc/[module credit] pre/[pre requisites]\" to add a"
                                + " module to the list of available modules\n");

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
                if (selectedModule.getId().equals(newModule.getId())
                        || selectedModule.getName().equals(newModule.getName())) {
                    selectedModule.setModuleConfig(newModule);
                }
            }
        }
    }
}
