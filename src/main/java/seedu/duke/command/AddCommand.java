package seedu.duke.command;

import seedu.duke.data.ModuleList;
import seedu.duke.module.Module;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private Module module;

    public AddCommand(Module module) {
        this.module = module;
    }

    @Override
    public void execute(ModuleList moduleList) {
        addModule(moduleList);
        System.out.println("Okay! I have added this module to your module list:");
        System.out.println(this.module);
    }

    private void addModule(ModuleList moduleList) {
        moduleList.add(this.module);
    }
}
