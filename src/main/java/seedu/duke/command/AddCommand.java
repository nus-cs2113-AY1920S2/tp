package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.data.ModuleList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private Module module;

    public AddCommand(Module module) {
        this.module = module;
    }

    @Override
    public void execute(SelectedModulesList selectedModulesList, ModuleList availableModulesList) {
        addModule(selectedModulesList);
        Ui.showAddedMessage(module.toString());
    }

    private void addModule(SelectedModulesList moduleList) {
        for (SemModulesList sem: moduleList) {
            if (sem.getSem().equals(module.getSem())) {
                sem.add(module);
                return;
            }
        }
        SemModulesList sem = new SemModulesList(module.getSem());
        sem.add(module);
        moduleList.add(sem);
    }
}
