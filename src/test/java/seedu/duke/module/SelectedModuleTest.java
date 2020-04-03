package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectedModuleTest {

    @Test
    public void testMarkAsDone() {
        SelectedModule selectedModule = new SelectedModule("id", "CS1010", "3", 4);
        assertEquals("[x]", selectedModule.getIcon());
        selectedModule.setAsDone(Grading.APLUS);
        assertEquals("[v]", selectedModule.getIcon());
        assertEquals("Y2S1", selectedModule.getYearSemester());
    }
}
