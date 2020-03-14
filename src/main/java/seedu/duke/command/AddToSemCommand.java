package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;

public class AddToSemCommand extends Command {

    public static final String COMMAND_WORD = "addtosem";
    private Module module;
    private SelectedModule selectedModule;

    public AddToSemCommand(Module module) {
        this.module = module;
        this.selectedModule = new SelectedModule(module);
    }

    @Override
    public void execute(SelectedModulesList selectedModulesList, AvailableModulesList availableModulesList) {
        addModule(selectedModulesList);
        Ui.showAddedToSemMessage(module.toString());
    }

    private void addModule(SelectedModulesList moduleList) {
        for (SemModulesList sem: moduleList) {
            if (sem.getSem().equals(selectedModule.getSem())) {
                sem.add(selectedModule);
                return;
            }
        }
        SemModulesList sem = new SemModulesList(selectedModule.getSem());
        sem.add(selectedModule);
        moduleList.add(sem);
    }
}
