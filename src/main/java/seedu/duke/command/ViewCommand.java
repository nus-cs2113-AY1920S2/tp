package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.ModuleList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String VIEW_COMPULSORY_MODULES = "cm";
    public static final String VIEW_DONE_MODULES = "dm";
    public static final String VIEW_MODULE_PLAN = "mp";
    public static final String VIEW_SPECIFIC_MODULE = "sm";
    public static final String VIEW_AVAILABLE_MODULES = "am";
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
    public void execute(SelectedModulesList selectedModulesList, AvailableModulesList availableModulesList) {
        switch (viewTaskType) {
        case VIEW_MODULE_PLAN:
            viewModulePlan(selectedModulesList);
            break;
        case VIEW_DONE_MODULES:
            viewDoneModules(selectedModulesList);
            break;
        case VIEW_AVAILABLE_MODULES:
            viewAvailableModules(availableModulesList);
            break;
        default:
            return;
        }
    }

    /**
     * Prints the user's module plan.
     * @param moduleList user's module list.
     */
    private void viewModulePlan(SelectedModulesList moduleList) {
        StringBuilder viewList = new StringBuilder();
        for (SemModulesList sem: moduleList) {
            viewList.append(sem.getSem()).append(System.lineSeparator());
            for (Module selectedModule: sem) {
                int index = sem.indexOf(selectedModule) + 1;
                viewList.append(index).append(".")
                        .append(selectedModule.toString())
                        .append(System.lineSeparator());
            }
            viewList.append(System.lineSeparator());
        }
        Ui.showViewMessage(viewList.toString().trim());
    }

    private void viewDoneModules(SelectedModulesList moduleList) {
        StringBuilder viewList = new StringBuilder();
        for (SemModulesList sem: moduleList) {
            viewList.append(sem.getSem()).append(System.lineSeparator());
            for (Module selectedModule: sem) {
                int index = sem.indexOf(selectedModule) + 1;
                if (selectedModule.getDone()) {
                    viewList.append(index).append(".")
                            .append(selectedModule.toString())
                            .append(System.lineSeparator());

                }
            }
            viewList.append(System.lineSeparator());
        }
        Ui.showViewDoneMessage(viewList.toString().trim());
    }

    private void viewAvailableModules(AvailableModulesList modulesList) {
        StringBuilder viewList = new StringBuilder();
        for (Module module : modulesList) {
            int index = modulesList.indexOf(module) + 1;
            viewList
                    .append(Ui.BOX_MARGIN)
                    .append("\n")
                    .append("|  ").append(String.format("%02d", index)).append(" | ")
                    .append(module.getId());
            String spaces = "";
            for (int i = 0; i < (9 - module.getId().length()); ++i) {
                viewList.append(" ");
            }
            viewList.append("| ").append(module.getName());
            for (int i = 0; i < (62 - module.getName().length()); ++i) {
                viewList.append(" ");
            }
            viewList.append("| ");
            String output;
            if (module.getPreRequisiteModules().size() == 0) {
                output = "None";
            } else if (module.getPreRequisiteModules().size() == 1) {
                output = module.getPreRequisiteModules().get(0).getId();
            } else {
                List<String> preReqModules = new ArrayList<>();
                for (Module preReqModule : module.getPreRequisiteModules()) {
                    preReqModules.add(preReqModule.getId());
                }
                output = preReqModules.stream()
                        .reduce((firstModule, secondModule) -> firstModule + ", " + secondModule)
                        .get(); // hope this won't cause error
            }
            viewList.append(output);
            for (int i = 0; i < (36 - output.length()); ++i) {
                viewList.append(" ");
            }
            viewList.append("|\n");
        }
        /*
        for (Module module : modulesList) {
            int index = modulesList.indexOf(module) + 1;
            viewList.append(index).append(".")
                    .append(module.toString())
                    .append(System.lineSeparator());
        }

         */
        viewList.append(System.lineSeparator());
        Ui.showViewAvailableMessage(viewList.toString().trim());
    }
}
