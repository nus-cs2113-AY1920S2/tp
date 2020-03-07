package seedu.duke.command;

import seedu.duke.data.ModuleList;
import seedu.duke.module.Module;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String VIEW_COMPULSORY_MODULES = "cm";
    public static final String VIEW_DONE_MODULES = "dm";
    public static final String VIEW_MODULE_PLAN = "mp";
    public static final String VIEW_SPECIFIC_MODULE = "sm";
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
    public void execute(ModuleList moduleList) {
        switch (viewTaskType) {
        case VIEW_MODULE_PLAN:
            viewModulePlan(moduleList);
            break;
        default:
            System.out.println("\tError!");
        }
    }

    /**
     * Prints the user's module plan.
     * @param moduleList user's module list.
     */
    private void viewModulePlan(ModuleList moduleList) {
        System.out.println("Okay! Here is your module plan:");
        for (Module module: moduleList) {
            int index = moduleList.indexOf(module) + 1;
            System.out.println("\t" + index + ". " + module);
        }
    }
}
