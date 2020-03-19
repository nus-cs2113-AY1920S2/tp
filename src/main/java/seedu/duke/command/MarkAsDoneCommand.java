package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.Person;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.exception.RuntimeException;
import seedu.duke.module.Module;
import seedu.duke.ui.Ui;

public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private int credit;
    private String description;
    private String grade;

    /**
     * marks the module in a semester in the selectedList as done.
     * @param description : name of the module or id of the module that the user wants to mark as done.
     * @param grade : grade of the module achieved by the user
     * @param credit : credit of the module done by the user
     */
    public MarkAsDoneCommand(String description, String grade, int credit) {
        super();
        this.description = description;
        this.grade = grade;
        this.credit = credit;
    }

    @Override
    public void execute(SelectedModulesList selectedModulesList,
                        AvailableModulesList availableModulesList) throws RuntimeException {
        markAsDoneCommand(selectedModulesList);
        Ui.showDoneMessage();
    }

    private void markAsDoneCommand(SelectedModulesList selectedModulesList) throws RuntimeException {
        for (SemModulesList sem: selectedModulesList) {
            for (Module module: sem) {
                if (module.getName().equals(description) || module.getId().equals(description)) {
                    module.setAsDone();
                    module.setGrade(grade);
                    Person.addTotalModularCreditCompleted(credit);
                    return;
                }
            }
        }
        throw new RuntimeException("module not found");
    }
}
