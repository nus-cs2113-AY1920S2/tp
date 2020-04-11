package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.data.Person;
import seedu.duke.data.SemModulesList;
import seedu.duke.exception.InputException;
import seedu.duke.exception.RuntimeException;
import seedu.duke.exception.StorageException;
import seedu.duke.module.Grading;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;

public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private String description;
    private Grading grade;

    /**
     * marks the module in a semester in the selectedList as done.
     *
     * @param description : name of the module or id of the module that the user wants to mark as done.
     * @param grade       : Grading awarded for the module.
     */
    public MarkAsDoneCommand(String description, Grading grade) {
        this.description = description;
        this.grade = grade;
    }

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList)
            throws RuntimeException, InputException, StorageException {
        markAsDoneCommand(semesterList);
        Ui.showDoneMessage();
        super.execute(semesterList, availableModulesList);
    }

    /**
     * Find selected module in sem and assign module with a grade.
     * Increase user's number of completed module credit if the grade assigned to module is not F or CU.
     *
     * @param semesterList Semester List containing Semester Module Lists, which contains selected modules
     * @throws RuntimeException throws except if module not found in semester
     */
    private void markAsDoneCommand(SemesterList semesterList) throws RuntimeException {
        for (SemModulesList sem : semesterList) {
            for (SelectedModule module : sem) {
                boolean isModuleName = module.getName().equalsIgnoreCase(description);
                boolean isModuleId = module.getId().equalsIgnoreCase(description);
                if (isModuleName || isModuleId) {
                    boolean isNotGradeF = (grade != Grading.F);
                    boolean isNotGradeCU = (grade != Grading.CU);
                    boolean isModuleNotDone = !module.getDone();
                    if (isNotGradeF && isNotGradeCU && isModuleNotDone) {
                        Person.addTotalModuleCreditCompleted(module.getModuleCredit());
                    }
                    if (!isModuleNotDone && (!isNotGradeCU || !isNotGradeF)) {
                        Person.minusTotalModuleCreditCompleted(module.getModuleCredit());
                    }
                    module.setAsDone(grade);
                    if (!isNotGradeF || !isNotGradeCU) {
                        appendFailString(module, sem);
                    }
                    return;
                }
            }
        }
        throw new RuntimeException("module not found");
    }

    private void appendFailString(SelectedModule module, SemModulesList sem) {
        StringBuilder newName = new StringBuilder();
        if (module.isIdValid()) {
            newName.append(module.getId());
        } else if (module.isNameValid()) {
            newName.append(module.getName());
        }
        newName.append(" Failed");
        SelectedModule failedModule = new SelectedModule("name", newName.toString(),
                module.getSem(), module.getModuleCredit());
        sem.remove(module);
        failedModule.setAsDone(grade);
        sem.add(failedModule);
    }
}
