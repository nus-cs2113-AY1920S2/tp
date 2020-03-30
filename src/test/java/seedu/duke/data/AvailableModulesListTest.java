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
        //AvailableModulesList modulesList = (AvailableModulesList) AvailableModulesList.availableModulesList;
        assertEquals(module, modulesList.getModule("CS1010"));
    }
}
