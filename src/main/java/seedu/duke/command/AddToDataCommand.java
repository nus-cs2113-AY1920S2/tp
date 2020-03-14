package seedu.duke.command;

import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;
import seedu.duke.data.ModuleList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;

public class AddToDataCommand extends Command {

    public static final String COMMAND_WORD = "addtodata";
    private Module module;

    public AddToDataCommand(Module module) {
        this.module = module;
    }

    @Override
    public void execute(SelectedModulesList selectedModulesList, ModuleList availableModulesList) {
        addModule(selectedModulesList);
        Ui.showAddedMessage(module.toString());
    }

    /*
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
     */
}
