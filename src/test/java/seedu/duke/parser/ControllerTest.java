package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpingCommand;
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

}