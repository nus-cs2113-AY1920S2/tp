package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskTest {

    @Test
    void asRow_taskNotDone() throws Exception {
        assertEquals("read book :-(", new Task("read book", false).getAsRow(" "));
    }

    @Test
    void asRow_taskDone() throws Exception {
        assertEquals("return book | :-)",
                new Task("return book", true).getAsRow(" | "));
    }

    @Test
    void asRow_emptyDivider_throwsException() {
        try {
            new Task("abc", true).getAsRow("");
            fail("empty divider should have thrown an exception");
        } catch (Exception e) {
            assertEquals("empty divider not allowed", e.getMessage());
        }
    }
}
