package seedu.duke.command;

import seedu.duke.command.DeleteCommand;
import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;
import seedu.duke.module.Module;
import seedu.duke.module.NewModule;

public class DeleteFromAvailableCommand extends DeleteCommand {

    private String moduleIdentifier;
    private String type;
    /**
     * Constructor for DeleteFromAvailableCommand.
     * @param moduleIdentifier The Id or the Name of the Module.
     * @param type To determine if the moduleIdentifier is an Id or the Name of the module.
     */

    public DeleteFromAvailableCommand(String moduleIdentifier, String type) {
        this.moduleIdentifier = moduleIdentifier;
        this.type = type;
    }

    public void execute(SelectedModulesList selectedModulesList, AvailableModulesList availableModulesList) {
        boolean isModuleAvailable = checkIfModuleAvailable(availableModulesList);
        if (!isModuleAvailable) {
            Ui.showError(String.format("Module %s not found in available modules", moduleIdentifier));
            return;
        }

        Module moduleChosen = availableModulesList.getModule(moduleIdentifier);
        boolean isPreReq = checkIfIsPreReq(moduleChosen, availableModulesList);
        if (isPreReq) {
            Ui.showError(String.format("Module %s cannot be deleted because it is a prerequisite to other modules.",
                    moduleIdentifier));
            return;
        }
        availableModulesList.remove(moduleChosen); // why did this work????
        Ui.showDeleteFromAvailableMessage(moduleChosen.toString());

        boolean isInModulePlan = checkIfInModulePlan(moduleChosen.getId(), selectedModulesList);
        if (isInModulePlan) {
            for (SemModulesList sem : selectedModulesList) {
                if (sem.isModuleIdInList(moduleChosen.getId())) {
                    sem.deleteModule(moduleIdentifier);
                    Ui.showDeleteFromAvailableFollowUpMessage(moduleChosen.toString());
                    break;
                }
            }
        }
    }

    public boolean checkIfModuleAvailable(AvailableModulesList availableModulesList) {
        if (type.equals("id")) {
            return availableModulesList.isModuleIdInList(moduleIdentifier);
        }
        return availableModulesList.isModuleNameInList(moduleIdentifier);
    }

    public boolean checkIfIsPreReq(Module moduleToCheck, AvailableModulesList availableModulesList) {
        for (Module module : availableModulesList) {
            if (module.getPreRequisiteModules().size() > 0) {
                for (Module preReqModule : module.getPreRequisiteModules()) {
                    if (preReqModule.getId().equals(moduleToCheck.getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkIfInModulePlan(String moduleId, SelectedModulesList selectedModulesList) {
        for (SemModulesList sem : selectedModulesList) {
            if (sem.isModuleIdInList(moduleId)) {
                return true;
            }
        }
        return false;
    }
}
