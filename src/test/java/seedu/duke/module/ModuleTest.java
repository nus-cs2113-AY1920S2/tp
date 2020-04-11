package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleTest {

    @Test
    public void getName() {
        SelectedModule selectedModule = new SelectedModule("id", "CS1231",
                "4", 4);
        assertEquals("unnamed", selectedModule.getName());
    }

    @Test
    public void updateName() {
        SelectedModule selectedModule = new SelectedModule("id", "CS1231",
                "4", 4);
        selectedModule.updateName("Discrete Structures");
        assertEquals("Discrete Structures", selectedModule.getName());
    }

    @Test
    public void updateId() {
        SelectedModule selectedModule = new SelectedModule("name", "Discrete Structures",
                "4", 4);
        selectedModule.updateId("CS1231");
        assertEquals("CS1231", selectedModule.getId());
    }

    @Test
    public void getPreRequisiteModules() {
        NewModule newModule = new NewModule("CS2040", "Data Structures",
                4);
        assertEquals("[]", newModule.getPreRequisiteModules().toString());
    }


}
