package seedu.dietmanager.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ResultTest {

    @Test
    void showResult() {
        Result test = new Result("Hello");
        assertEquals("Hello", test.showResult());
        assertFalse("Hello!".equals(test.showResult()));
    }
}