package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {
    @Test
    public void testIsStandardCodeFormat() {
        assertEquals(true, Controller.isStandardCodeFormat("CS2113"));
        assertEquals(true, Controller.isStandardCodeFormat("acc4761h"));
        assertEquals(false, Controller.isStandardCodeFormat("is001"));
        assertEquals(false, Controller.isStandardCodeFormat("CS001T"));
        assertEquals(false, Controller.isStandardCodeFormat("acc4761hh"));
        assertEquals(false, Controller.isStandardCodeFormat(""));
    }

}