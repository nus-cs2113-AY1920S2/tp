package seedu.pac;

import org.junit.jupiter.api.Test;
import seedu.command.Bye;
import seedu.exception.PacException;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PacTest {
    Pac pac;

    public PacTest() {
        try {
            pac = new Pac();
        } catch (PacException m) {
            System.out.println(m.getMessage());
        }
    }

    @Test
    void checkBye() {
        assertFalse(pac.isNotBye(new Bye()));
    }
}