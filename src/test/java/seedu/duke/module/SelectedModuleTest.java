package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectedModuleTest {

    @Test
    public void testMarkAsDone() {
        SelectedModule selectedModule = new SelectedModule("id", "CS1010", "19/20 Sem2", 4);
        assertEquals("[✗]", selectedModule.getIcon());
        selectedModule.setAsDone(Grading.APLUS);
        assertEquals("[✓]", selectedModule.getIcon());
    }
}
