package seedu.duke.command;

import seedu.duke.data.ModuleList;
import seedu.duke.data.SelectedModuleList;
import seedu.duke.data.SemModuleList;
import seedu.duke.module.Module;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private Module module;

    public AddCommand(Module module) {
        this.module = module;
    }

    @Override
    public void execute(SelectedModuleList moduleList) {
        addModule(moduleList);
        System.out.println("Okay! I have added this module to your module list:");
        System.out.println(this.module);
    }

    private void addModule(SelectedModuleList moduleList) {
        for(SemModuleList sem: moduleList){
            if(sem.getSem().equals(module.getSem())){
                sem.add(module);
                return;
            }
        }
        SemModuleList sem = new SemModuleList(module.getSem());
        sem.add(module);
        moduleList.add(sem);
    }
}
