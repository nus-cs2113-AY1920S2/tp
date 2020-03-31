package seedu.pac;

import org.junit.jupiter.api.Test;
import seedu.command.Bye;
import seedu.exception.PACException;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PACTest {
    PAC PAC;

    public PACTest() {
        try {
            PAC = new PAC();
        } catch (PACException m) {
            System.out.println(m.getMessage());
        }
    }

    @Test
    void checkBye() {
        assertFalse(PAC.isNotBye(new Bye()));
    }
}