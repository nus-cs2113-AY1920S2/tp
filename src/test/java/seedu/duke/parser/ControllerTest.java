package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InputException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
    @Test
    public void testIsStandardCodeFormat() {
        assertTrue(Controller.isStandardCodeFormat("CS2113"));
        assertTrue(Controller.isStandardCodeFormat("acc4761h"));
        assertFalse(Controller.isStandardCodeFormat("is001"));
        assertFalse(Controller.isStandardCodeFormat("CS001T"));
        assertFalse(Controller.isStandardCodeFormat("acc4761hh"));
        assertFalse(Controller.isStandardCodeFormat(""));
    }


    @Test
    public void invalidSemesterThrowsInputException() {
        try {
            Controller.control("add id/CS2104 s/11 mc/4");
        } catch (Exception e) {
            assertTrue(e instanceof InputException);
            assertTrue(e.getMessage().equals("Input Exception: Semester should be between 1 - 10."
                    + System.lineSeparator()
                    + "Please enter your command again!"));
        }

        try {
            Controller.control("add id/CS2104 s/0 mc/4");
        } catch (Exception e) {
            assertTrue(e instanceof InputException);
            assertTrue(e.getMessage().equals("Input Exception: Semester should be between 1 - 10."
                    + System.lineSeparator()
                    + "Please enter your command again!"));
        }
    }

    @Test
    public void invalidModuleCreditThrowsInputException() {
        try {
            Controller.control("add id/TES0010 n/TEST1 mc/11");
        } catch (Exception e) {
            assertTrue(e instanceof InputException);
            assertTrue(e.getMessage().equals("Input Exception: Module credit should be between 1 - 10."
                    + System.lineSeparator()
                    + "Please enter your command again!"));
        }

        try {
            Controller.control("add id/TES0011 n/TEST2 mc/0");
        } catch (Exception e) {
            assertTrue(e instanceof InputException);
            assertTrue(e.getMessage().equals("Input Exception: Module credit should be between 1 - 10."
                    + System.lineSeparator()
                    + "Please enter your command again!"));
        }
    }

}