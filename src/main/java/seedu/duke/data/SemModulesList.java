package seedu.duke.data;

import seedu.duke.module.Module;
import seedu.duke.module.SelectedModule;

import java.util.ArrayList;


public class SemModulesList extends ArrayList<SelectedModule> {
    private String semester;
    private String yearSemester;

    public SemModulesList(String semester) {
        this.semester = semester;
        setYearSemester();
    }

    public String getSem() {
        return semester;
    }

    /**
     * Converts semester (e.g. semester 5) to specific year semester string (Y3S1)
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

    public boolean isInList(String moduleIdentifier) {
        for (SelectedModule module: this) {
            if (module.getName().equals(moduleIdentifier) || module.getId().equals(moduleIdentifier)) {
                return true;
            }
        }
        return false;
    }


    /**
     * To retrieve a module from the modules in that semester from the Id or the Name of the module.
     * Assumes that the user knows that the module already exists in the list of modules.
     * @param moduleIdentifier Id or Name of module.
     * @return Module that corresponds to the modules identifier inputted.
     */
    public Module getModule(String moduleIdentifier) {
        for (Module module : this) {
            if (module.getId().equals(moduleIdentifier) || module.getName().equals(moduleIdentifier)) {
                return module;
            }
        }
        return null;
    }

    public void deleteModule(String moduleIdentifier) {
        for (Module module : this) {
            if (module.getName().equals(moduleIdentifier) || module.getId().equals(moduleIdentifier)) {
                this.remove(module);
                break;
            }
        }
    }

    public String getYearSemester() {
        return yearSemester;
    }
}
