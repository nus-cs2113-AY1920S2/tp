package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InputException;
import seedu.duke.module.SelectedModule;
import seedu.duke.module.Module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SemModulesListTest {

    @Test
    void getSem() {
        SemModulesList semModulesList = new SemModulesList("7");
        assertEquals("7", semModulesList.getSem());
    }

    @Test
    void getYearSemester() {
        SemModulesList semModulesList = new SemModulesList("8");
        semModulesList.setYearSemester();
        assertEquals("Y4S2", semModulesList.getYearSemester());
    }

    @Test
    void isInList() {
        SemModulesList semModulesList = new SemModulesList("8");
        semModulesList.add(new SelectedModule("Both", "CS1010", "Programming Methodology",
                "8", 4));
        assertFalse(semModulesList.isInList("CS1231"));
        assertFalse(semModulesList.isInList("Discrete Structures"));
        semModulesList.add(new SelectedModule("Both", "CS1231", "Discrete Structures",
                "8", 4));
        assertTrue(semModulesList.isInList("CS1231"));
        assertTrue(semModulesList.isInList("Discrete Structures"));
    }

    @Test
    void deleteModule() {
        SemModulesList semModulesList = new SemModulesList("8");
        semModulesList.add(new SelectedModule("Both", "CS1231", "Discrete Structures",
                "8", 4));
        assertTrue(semModulesList.isInList("CS1231"));
        assertTrue(semModulesList.isInList("Discrete Structures"));
        semModulesList.deleteModule("CS1231");
        assertFalse(semModulesList.isInList("CS1231"));
        assertFalse(semModulesList.isInList("Discrete Structures"));
        semModulesList.add(new SelectedModule("Both", "CS1231", "Discrete Structures",
                "8", 4));
        semModulesList.deleteModule("Discrete Structures");
        assertFalse(semModulesList.isInList("CS1231"));
        assertFalse(semModulesList.isInList("Discrete Structures"));
    }

    @Test
    void getModule() throws InputException {
        SemModulesList semModulesList = new SemModulesList("8");
        SelectedModule selectedModule =
                new SelectedModule("id", "CS1231", "8", 4);
        SelectedModule anotherSelectedModule =
                new SelectedModule("name", "Mathematics of Games", "8", 4);
        semModulesList.add(selectedModule);
        assertEquals(selectedModule, semModulesList.getModule("CS1231"));
        semModulesList.add(anotherSelectedModule);
        assertEquals(anotherSelectedModule, semModulesList.getModule("Mathematics of Games"));
        assertThrows(InputException.class, () -> semModulesList.getModule("Math of Games"));
    }
}
