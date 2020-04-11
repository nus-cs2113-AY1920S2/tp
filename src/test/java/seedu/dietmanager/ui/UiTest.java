package seedu.dietmanager.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UiTest {

    @Test
    void isExitStatus() {
        UI test = new UI();
        assertFalse(test.isExitStatus());
    }

    @Test
    void setExitStatus() {
        UI test = new UI();
        test.setExitStatus(true);
        assertTrue(test.isExitStatus());
    }

}