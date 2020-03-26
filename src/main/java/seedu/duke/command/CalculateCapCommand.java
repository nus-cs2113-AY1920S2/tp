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

    public void calculateCap(SemesterList semesterList) {
        double totalGradePoint = 0;
        int totalGradeModuleCredit = 0;
        for (SemModulesList sem : semesterList) {
            for (SelectedModule module : sem) {
                if (!module.getDone()) {
                    continue;
                }
                if (module.getGrade().getGrade().equals("F")) {
                    totalGradeModuleCredit += module.getModuleCredit();
                } else if (!module.getGrade().getGrade().equals("CS") || !module.getGrade().getGrade().equals("CU")) {
                    totalGradePoint += module.getModuleCredit() * module.getGrade().getPoint();
                    totalGradeModuleCredit += module.getModuleCredit();
                }
            }
        }
        double cap = totalGradePoint / totalGradeModuleCredit;
        Person.setTotalCap(cap);
    }
}
