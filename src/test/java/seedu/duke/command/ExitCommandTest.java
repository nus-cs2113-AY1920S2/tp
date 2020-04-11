package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitCommandTest {

    @Test
    void isExit() throws InvalidFormatException {
        Command c = new ExitCommand();
        assertEquals(true, c.isExit());
    }
}