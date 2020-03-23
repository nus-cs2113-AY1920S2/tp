package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.command.Bye;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DukeTest {
    Duke duke = new Duke();

    @Test
    void checkBye() {
        assertFalse(duke.isNotBye(new Bye()));
    }
}