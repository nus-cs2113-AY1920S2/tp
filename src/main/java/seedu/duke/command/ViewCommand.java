package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.module.Module;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String VIEW_COMPULSORY_MODULES = "cm";
    public static final String VIEW_DONE_MODULES = "dm";
    public static final String VIEW_MODULE_PLAN = "mp";
    public static final String VIEW_SPECIFIC_MODULE = "sm";
    public static final String VIEW_AVAILABLE_MODULES = "am";
    public static final String VIEW_COMPLETED_CREDITS = "cc";
    private String viewTaskType;
    private String moduleToBeViewed;

    public ViewCommand(String viewTaskType) {
        this.viewTaskType = viewTaskType;
    }

    public ViewCommand(String viewTaskType, String moduleIdentifier) {
        this.viewTaskType = viewTaskType;
        this.moduleToBeViewed = moduleIdentifier;
    }

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) {
        switch (viewTaskType) {
        case VIEW_MODULE_PLAN:
            viewModulePlan(semesterList);
            break;
        case VIEW_DONE_MODULES:
            viewDoneModules(semesterList);
            break;
        case VIEW_AVAILABLE_MODULES:
            viewAvailableModules(availableModulesList);
            break;
        case VIEW_COMPLETED_CREDITS:
            viewCompletedCredits();
            break;
        default:
        }
    }

    /**
     * Prints the user's module plan.
     *
     * @param semesterList user's module list.
     */
    private void viewModulePlan(SemesterList semesterList) {
        StringBuilder viewList = new StringBuilder();
        for (SemModulesList sem : semesterList) {
            viewList.append(sem.getYearSemester()).append(System.lineSeparator());
            for (Module selectedModule : sem) {
                int index = sem.indexOf(selectedModule) + 1;
                viewList.append(index).append(".")
                        .append(selectedModule.toString())
                        .append(System.lineSeparator());
            }
            viewList.append(System.lineSeparator());
        }
        Ui.showViewMessage(viewList.toString().trim());
    }

    /**
     * Prints the user's completed modules.
     * @param semesterList user's module list.
     */
    private void viewDoneModules(SemesterList semesterList) {
        StringBuilder viewList = new StringBuilder();
        for (SemModulesList sem : semesterList) {
            StringBuilder viewSemList = new StringBuilder();
            boolean haveCompletedModule = false;
            viewSemList.append(sem.getYearSemester()).append(System.lineSeparator());
            int index = 1;
            for (SelectedModule selectedModule : sem) {
                if (selectedModule.getDone()) {
                    haveCompletedModule = true;
                    viewSemList.append(index).append(".")
                            .append(selectedModule.toString())
                            .append(System.lineSeparator());
                    index++;
                }
            }
            if (haveCompletedModule) {
                viewList.append(viewSemList.toString()).append(System.lineSeparator());
            }
        }
        Ui.showViewDoneMessage(viewList.toString().trim());
    }

    /**
     * print user's available module list
     *
     * @param modulesList user's available module list.
     */
    private void viewAvailableModules(AvailableModulesList modulesList) {
        StringBuilder viewList = new StringBuilder();
        for (Module module : modulesList) {
            int index = modulesList.indexOf(module) + 1;
            viewList.append(Ui.BOX_MARGIN)
                    .append(System.lineSeparator())
                    .append("|  ").append(String.format("%02d", index)).append(" | ");

            viewList.append(module.getId());
            alignId(viewList, module);

            viewList.append(module.getName());
            alignName(viewList, module);

            viewList.append(module.getModuleCredit());
            alignModuleCredit(viewList, module);

            viewList.append(module.getPreReqModulesID());
            alignPreReqModules(viewList, module);
        }

        viewList.append(System.lineSeparator());
        Ui.showViewAvailableMessage(viewList.toString().trim());
    }

    private void viewCompletedCredits() {
        Ui.showCompletedCredits();
    }

    private void alignPreReqModules(StringBuilder viewList, Module module) {
        int lengthOfPreReqModulesColumn = 24;
        viewList.append(" ".repeat(Math.max(0,
                (lengthOfPreReqModulesColumn - module.getPreReqModulesID().length()))));
        viewList.append("|").append(System.lineSeparator());
    }

    private void alignModuleCredit(StringBuilder viewList, Module module) {
        int lengthOfCreditColumn = 14;
        viewList.append(" ".repeat(lengthOfCreditColumn - 1));
        viewList.append("| ");
    }

    private void alignName(StringBuilder viewList, Module module) {
        int lengthOfNameColumn = 62;
        viewList.append(" ".repeat(Math.max(0,
                (lengthOfNameColumn - module.getName().length()))));
        viewList.append("| ");
    }

    private void alignId(StringBuilder viewList, Module module) {
        int lengthOfIDsColumn = 9;
        viewList.append(" ".repeat(Math.max(0,
                (lengthOfIDsColumn - module.getId().length()))));
        viewList.append("| ");
    }

}
