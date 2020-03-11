package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.ModuleList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;
import seedu.duke.ui.Ui;

import java.sql.SQLOutput;

public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private String moduleName;
    private String semester;

    /**
     * marks the module in a semester in the selectedList as done.
     * @param moduleName : name of the module that the user wants to mark as done.
     * @param semester : Semester that the user wants to mark as done.
     */
    public MarkAsDoneCommand(String moduleName, String semester) {
        super();
        this.moduleName = moduleName;
        this.semester = semester;
    }

    @Override
    public void execute(SelectedModulesList selectedModulesList, ModuleList availableModulesList) {
        markAsDoneCommand(selectedModulesList);
        Ui.showDoneMessage();
    }

    private void markAsDoneCommand(SelectedModulesList selectedModulesList) {
        for (SemModulesList sem: selectedModulesList) {
            if (sem.getSem().equals(semester)) {
                for (Module module: sem) {
                    System.out.println(module.getName());
                    System.out.println(module.toString());
                    if (module.getName()!=null) {
                        if (module.getName().equals(moduleName)) {
                            module.setAsDone();
                        }
                    }
                }
            }
        }
    }
}
