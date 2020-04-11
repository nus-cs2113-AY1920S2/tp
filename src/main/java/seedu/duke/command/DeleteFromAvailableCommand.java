package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.InputException;
import seedu.duke.exception.RuntimeException;
import seedu.duke.exception.StorageException;
import seedu.duke.ui.Ui;
import seedu.duke.module.Module;

public class DeleteFromAvailableCommand extends DeleteCommand {

    private String moduleIdentifier;
    private String type;

    /**
     * Constructor for DeleteFromAvailableCommand.
     * @param moduleIdentifier The Id or the Name of the Module.
     * @param type To determine if the moduleIdentifier is an Id or the Name of the module.
     */
    public DeleteFromAvailableCommand(String moduleIdentifier, String type) {
        this.moduleIdentifier = moduleIdentifier.trim();
        this.type = type;
    }

    public void execute(SemesterList selectedModulesList, AvailableModulesList availableModulesList)
            throws RuntimeException, StorageException, InputException {
        deleteModule(selectedModulesList, availableModulesList);
        super.execute(selectedModulesList, availableModulesList);
    }

    /**
     * This method deletes the module from the list of available modules after chcecking that it exists in the list
     * and that it is not a prerequisite of any modules.
     * It also deletes the module from the module plan, if applicable.
     * @param selectedModulesList The module plan of the user.
     * @param availableModulesList The list of available modules to choose from.
     * @throws RuntimeException Throws exception when the module does not exist or if the module is a prerequisite
     *     of other modules.
     */
    private void deleteModule(SemesterList selectedModulesList, AvailableModulesList availableModulesList)
            throws RuntimeException {
        boolean isModuleAvailable = checkIfModuleAvailable(availableModulesList);
        if (!isModuleAvailable) {
            throw new RuntimeException(String.format("Module %s not found in available modules", moduleIdentifier));
        }

        Module moduleToBeDeleted = availableModulesList.getModule(moduleIdentifier);
        boolean isPreReq = checkIfIsPreReq(moduleToBeDeleted, availableModulesList);
        if (isPreReq) {
            throw new RuntimeException(String.format(
                    "Module %s cannot be deleted because it is a prerequisite to other modules.",
                    moduleIdentifier));
        }
        availableModulesList.remove(moduleToBeDeleted);
        Ui.showDeleteFromAvailableMessage(moduleToBeDeleted.toString());

        boolean isInModulePlan = checkIfInModulePlan(moduleToBeDeleted.getId(), selectedModulesList);
        if (isInModulePlan) {
            for (SemModulesList sem : selectedModulesList) {
                if (sem.isInList(moduleToBeDeleted.getId())) {
                    sem.deleteModule(moduleIdentifier);
                    Ui.showDeleteFromAvailableFollowUpMessage(moduleToBeDeleted.toString());
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
                    if (preReqModule.getId().equalsIgnoreCase(moduleToCheck.getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkIfInModulePlan(String moduleId, SemesterList selectedModulesList) {
        for (SemModulesList sem : selectedModulesList) {
            if (sem.isInList(moduleId)) {
                return true;
            }
        }
        return false;
    }
}
