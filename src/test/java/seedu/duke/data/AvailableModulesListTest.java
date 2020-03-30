package seedu.duke.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.module.NewModule;
import seedu.duke.data.ModuleList;

public class AvailableModulesListTest {

    @Test
    void getModule() {
        AvailableModulesList modulesList = new AvailableModulesList();
        NewModule module = new NewModule("CS1010", "Programming Methodology", 4);
        assertEquals(module, modulesList.getModule("CS1010"));
    }
}
