package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectedModuleTest {

    @Test
    public void testMarkAsDone() {
        Module module = new Module("id", "CS1010", "19/20 Sem2");
        assertEquals("[✗]", module.getIcon());
        module.setAsDone();
        assertEquals("[✓]", module.getIcon());
    }
}
