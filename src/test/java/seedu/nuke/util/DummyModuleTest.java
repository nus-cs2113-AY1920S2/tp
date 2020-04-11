package seedu.nuke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DummyModuleTest {

    private DummyModule dummyModule = new DummyModule();

    @Test
    void setModuleCode() {
        dummyModule.setModuleCode("CS2113T");
        assertEquals("CS2113T", dummyModule.getModuleCode());
    }


    @Test
    void setTitle() {
        dummyModule.setTitle("Computer Organisation");
        assertEquals("Computer Organisation", dummyModule.getTitle());
    }
}