package seedu.duke.command;

import seedu.duke.exception.RuntimeException;
import seedu.duke.data.AvailableModulesList;

import seedu.duke.data.SemesterList;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;

import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;

public class AddToSemCommand extends AddCommand {

    private SelectedModule selectedModule;

    public AddToSemCommand(SelectedModule selectedModule) {
        this.selectedModule = selectedModule;
        checkAvailableModulesList(selectedModule);
    }

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) throws RuntimeException {
        addModule(semesterList);
        Ui.showAddedToSemMessage(selectedModule.announceAdded());
    }

    private void addModule(SemesterList semesterList) throws RuntimeException {
        boolean isModuleExist = checkModuleExist(semesterList);
        if (isModuleExist) {
            throw new RuntimeException("The module is already exist in your semester list!");
        }

        for (SemModulesList sem: semesterList) {
            if (sem.getSem().equals(selectedModule.getSem())) {
                sem.add(selectedModule);
                return;
            }
        }

        SemModulesList sem = new SemModulesList(selectedModule.getSem());
        sem.add(selectedModule);
        semesterList.add(sem);
    }

    private void checkAvailableModulesList(SelectedModule selectedModule) {
        for (Module availableModule: AvailableModulesList.availableModulesList) {
            boolean isSameName = availableModule.getName().equals(selectedModule.getName());
            boolean isSameId = availableModule.getId().equals(selectedModule.getId());
            if (isSameName || isSameId) {
                this.selectedModule.setModuleConfig(availableModule);
            }
        }
    }

    private boolean checkModuleExist(SemesterList semesterList) throws RuntimeException {
        boolean isInAvailableList = false;

        for (Module module: AvailableModulesList.availableModulesList) {
            if (module.getName().equals(selectedModule.getName())) {
                isInAvailableList = true;
            }
        }

        if (!isInAvailableList) {
            throw new RuntimeException("Please add this module to available list first!");
        }

        for (SemModulesList sem: semesterList) {
            if (sem.isInList(selectedModule.getName()) || sem.isInList(selectedModule.getId())) {
                return true;
            }
        }

        return false;
    }
}
