package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.data.ModuleList;
import seedu.duke.module.Module;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private Module module;

    public AddCommand(Module module) {
        this.module = module;
    }

    @Override
    public void execute(ModuleList selectedModulesList, ModuleList availableModulesList) {
        addModule(selectedModulesList);
        Ui.showAddedMessage(module.toString());
    }

    private void addModule(ModuleList moduleList) {
        moduleList.add(this.module);
    }
}
