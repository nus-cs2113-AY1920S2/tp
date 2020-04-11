package seedu.duke.data;

import seedu.duke.exception.InputException;
import seedu.duke.module.Grading;
import seedu.duke.module.Module;
import seedu.duke.module.SelectedModule;

import java.util.ArrayList;


public class SemModulesList extends ArrayList<SelectedModule> {
    private String semester;
    private String yearSemester;

    /**
     * Constructor of SemModulesList.
     */
    public SemModulesList(String semester) {
        this.semester = semester;
        setYearSemester();
    }

    public String getSem() {
        return semester;
    }

    /**
     * Converts semester (e.g. semester 5) to specific year semester string (Y3S1).
     */
    public void setYearSemester() {
        StringBuilder yearSemesterBuilder = new StringBuilder();
        int intSemester = Integer.parseInt(semester);
        yearSemesterBuilder.append("Y").append((intSemester + 1) / 2).append("S");
        if (intSemester % 2 == 0) {
            yearSemesterBuilder.append(2);
        } else {
            yearSemesterBuilder.append(1);
        }
        yearSemester = yearSemesterBuilder.toString();
    }

    /**
     * Returns true if the module exists in the SemModulesList.
     * @param moduleIdentifier : String module name or module code.
     */
    public boolean isInList(String moduleIdentifier) {
        for (SelectedModule module: this) {
            boolean hasSameName = module.getName().equalsIgnoreCase(moduleIdentifier) && module.isNameValid();
            boolean hasSameId = module.getId().equalsIgnoreCase(moduleIdentifier) && module.isIdValid();
            if (hasSameName || hasSameId) {
                return true;
            }
        }
        return false;
    }

    /**
     * To retrieve a module from the modules in that semester from the Id or the Name of the module.
     * @param moduleIdentifier Id or Name of module.
     * @return Module that corresponds to the modules identifier inputted.
     */
    public SelectedModule getModule(String moduleIdentifier) throws InputException {
        for (SelectedModule module : this) {
            if (module.getId().equalsIgnoreCase(moduleIdentifier)
                    || module.getName().equalsIgnoreCase(moduleIdentifier)) {
                return module;
            }
        }
        throw new InputException("The module you entered seems incorrect");
    }

    public void deleteModule(String moduleIdentifier) {
        for (SelectedModule module : this) {
            if (module.getName().equals(moduleIdentifier) || module.getId().equals(moduleIdentifier)) {
                if (module.getDone()) {
                    boolean isModuleGradeF = module.getGrade().equals(Grading.F);
                    boolean isModuleGradeCU = module.getGrade().equals(Grading.CU);
                    boolean hasModuleFailed = isModuleGradeCU || isModuleGradeF;
                    if (!hasModuleFailed) {
                        Person.minusTotalModuleCreditCompleted(module.getModuleCredit());
                    }
                }
                this.remove(module);
                break;
            }
        }
    }

    public String getYearSemester() {
        return yearSemester;
    }
}
