package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.ui.Ui;
import seedu.duke.module.Module;

public class DeleteFromSemCommand extends DeleteCommand {

    private String moduleIdentifier;
    private String semester;
    private String type;

    /**
     * Constructor for DeleteFromSemCommand.
     * @param moduleIdentifier The Id or the Name of the Module.
     * @param semester The Semester the module was allocated to.
     * @param type To determine if the moduleIdentifier is an Id or the Name of the module.
     */
    public DeleteFromSemCommand(String moduleIdentifier, String semester, String type) {
        this.moduleIdentifier = moduleIdentifier;
        this.semester = semester;
        this.type = type;
    }

    public void execute(SemesterList selectedModulesList, AvailableModulesList availableModulesList) {
        boolean isModuleInSem = checkModuleExistInCorrectSem(selectedModulesList);
        if (!isModuleInSem) {
            Ui.showError(String.format("Module %s not found in Semester %s", moduleIdentifier, semester));
        } else {
            Module module;
            for (SemModulesList semModulesList : selectedModulesList) {
                if (semester.equals(semModulesList.getSem())) {
                    module = semModulesList.getModule(moduleIdentifier);
                    semModulesList.remove(module);
                    break;
                }
            }

            Ui.showDeleteFromSemMessage(String.format("Module %s has been deleted from semester %s",
                    moduleIdentifier, semester));
        }
    }

    private boolean checkModuleExistInCorrectSem(SemesterList moduleList) {
        for (SemModulesList sem: moduleList) {
            if (type.equals("id")) {
                if (sem.getSem().equals(semester) && sem.isModuleIdInList(moduleIdentifier)) {
                    return true;
                }
            }
            if (type.equals("name")) {
                if (sem.getSem().equals(semester) && sem.isInList(moduleIdentifier)) {
                    return true;
                }
            }
        }
        return false;
    }
}
