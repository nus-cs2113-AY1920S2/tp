package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SelectedModuleTest {

    @Test
    public void testMarkAsDone() {
        SelectedModule selectedModule = new SelectedModule("id", "CS1010", "3", 4);
        assertEquals("[x]", selectedModule.getIcon());
        selectedModule.setAsDone(Grading.APLUS);
        assertEquals("[v]", selectedModule.getIcon());
        assertEquals("Y2S1", selectedModule.getYearSemester());
    }

    @Test
    public void setModuleConfig() {
        SelectedModule selectedModule =
                new SelectedModule("CS1231", "Discrete Structures",
                        "6", 4, false, "A+");
        assertEquals("Discrete Structures", selectedModule.getName());
        assertEquals("CS1231", selectedModule.getId());
        selectedModule.setModuleConfig(selectedModule);
    }

    @Test
    public void getDone() {
        SelectedModule selectedModule = new SelectedModule("CS1231", "Discrete Structures",
                "7", 4, false);
        assertFalse(selectedModule.getDone());
    }

    @Test
    public void getSem() {
        SelectedModule selectedModule =
                new SelectedModule("CS1231", "Discrete Structures",
                        "6", 4, false, "A+");
        assertEquals("6", selectedModule.getSem());
    }

    @Test
    public void announceAdded() {
        SelectedModule selectedModule =
                new SelectedModule("CS1231", "Discrete Structures",
                        "6", 4, false, "A+");
        assertEquals("ID: CS1231 Name: Discrete Structures | Module Credit: 4 | Sem: Y3S2",
                selectedModule.announceAdded());
    }

    @Test
    public void getGrade() {
        SelectedModule selectedModule =
                new SelectedModule("/", "Discrete Structures",
                        "6", 4, false, "A+");
        assertEquals(Grading.APLUS, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "A");
        assertEquals(Grading.A, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "Discrete Structures",
                        "6", 4, false, "A-");
        assertEquals(Grading.AMINUS, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "B+");
        assertEquals(Grading.BPLUS, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "B");
        assertEquals(Grading.B, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "B-");
        assertEquals(Grading.BMINUS, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "C+");
        assertEquals(Grading.CPLUS, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "C");
        assertEquals(Grading.C, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "D+");
        assertEquals(Grading.DPLUS, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "D");
        assertEquals(Grading.D, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "F");
        assertEquals(Grading.F, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "CS");
        assertEquals(Grading.CS, selectedModule.getGrade());
        selectedModule =
                new SelectedModule("CS1231", "/",
                        "6", 4, false, "CU");
        assertEquals(Grading.CU, selectedModule.getGrade());
    }

}
