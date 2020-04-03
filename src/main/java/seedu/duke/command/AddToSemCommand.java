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
        super.execute(semesterList, availableModulesList);
    }

    private void addModule(SemesterList semesterList) throws RuntimeException {
        boolean isModuleExist = checkModuleExist(semesterList);
        if (isModuleExist) {
            String moduleIdentifier = selectedModule.isIdValid() ? selectedModule.getId() : selectedModule.getName();
            throw new RuntimeException(String.format("The module <%s> is already exist in your semester list!",
                    moduleIdentifier));
        }

        if (selectedModule.getModuleCredit() <= 0) {
            throw new RuntimeException("The module should not have non-positive module credit");
        } else if (selectedModule.getModuleCredit() > 50) {
            throw new RuntimeException("Are you sure the module is that large? :O");
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

    /**
     *Allows users to add to data later(or not) and then it if users add a module to data,
     *it will automatically update the information of the selected module.
     * @param semesterList user's current semester list
     * @return boolean value of true if the module is in the user's semester list, and false otherwise
     */
    private boolean checkModuleExist(SemesterList semesterList) {
        for (SemModulesList sem: semesterList) {
            if (sem.isInList(selectedModule.getName()) || sem.isInList(selectedModule.getId())) {
                return true;
            }
        }
        return false;
    }
}
