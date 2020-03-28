package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.Person;
import seedu.duke.data.SemModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;

import java.text.DecimalFormat;

public class CalculateCapCommand extends Command {

    public static final String COMMAND_WORD = "CAP";

    public CalculateCapCommand() {
    }

    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList) {
        calculateCap(semesterList);
        DecimalFormat df = new DecimalFormat("#.00");
        String cap = df.format(Person.getTotalCap());
        Ui.showCap(cap);
    }

    /** Calculate User's current Cumulative Average Point (CAP).
     * @param semesterList All modules selected by user
     */
    public void calculateCap(SemesterList semesterList) {
        double totalGradePoint = 0;
        int totalGradeModuleCredit = 0;
        for (SemModulesList sem : semesterList) {
            for (SelectedModule module : sem) {
                boolean isModuleCompleted = module.getDone();
                if (!isModuleCompleted) {
                    continue;
                }
                boolean isGradedModuleFailed = module.getGrade().getGrade().equals("F");
                boolean isUngradedPassed = module.getGrade().getGrade().equals("CS");
                boolean isUngradedFailed = module.getGrade().getGrade().equals("CU");
                boolean isCompletedModuleGraded = !isUngradedPassed && !isUngradedFailed;
                if (isGradedModuleFailed) {
                    totalGradeModuleCredit += module.getModuleCredit();
                } else if (isCompletedModuleGraded) {
                    totalGradePoint += module.getModuleCredit() * module.getGrade().getPoint();
                    totalGradeModuleCredit += module.getModuleCredit();
                }
            }
        }
        double cap = totalGradePoint / totalGradeModuleCredit;
        Person.setTotalCap(cap);
    }
}
