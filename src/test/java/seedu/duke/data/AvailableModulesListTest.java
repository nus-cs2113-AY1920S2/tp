package seedu.duke.data;

import org.junit.jupiter.api.Test;

import seedu.duke.module.NewModule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AvailableModulesListTest {

    @Test
    void getModule() {
        NewModule module = new NewModule("CS1010", "Programming Methodology", 4);
        assertEquals(module, new AvailableModulesList().getModule("CS1010"));
        assertEquals(module, new AvailableModulesList().getModule("Programming Methodology"));
    }

    @Test
    void isModuleNameInList() {
        AvailableModulesList modulesList = new AvailableModulesList();
        NewModule module = new NewModule("CS1010", "Programming Methodology", 4);
        modulesList.add(module);
        assertTrue(modulesList.isModuleNameInList("Programming Methodology"));
        assertFalse(modulesList.isModuleNameInList("Program Methodology"));
    }

    @Test
    void isModuleIdInList() {
        AvailableModulesList modulesList = new AvailableModulesList();
        NewModule module = new NewModule("CS1010", "Programming Methodology", 4);
        modulesList.add(module);
        assertTrue(modulesList.isModuleIdInList("CS1010"));
        assertFalse(modulesList.isModuleIdInList("CS2040C"));
    }

    @Test
    void remove() {
        AvailableModulesList modulesList = new AvailableModulesList();
        NewModule module = new NewModule("CS1231", "Discrete Structures", 4);
        modulesList.add(module);
        assertTrue(modulesList.isModuleIdInList("CS1231"));
        modulesList.remove(module);
        assertFalse(modulesList.isModuleIdInList("CS1231"));
    }

}
