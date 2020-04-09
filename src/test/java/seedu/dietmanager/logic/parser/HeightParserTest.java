package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidHeightException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeightParserTest {

    @Test
    void parseHeight() {
        assertThrows(InvalidHeightException.class, () -> {
            HeightParser.parseHeight("");
        });
        assertThrows(InvalidHeightException.class, () -> {
            HeightParser.parseHeight("Hello");
        });
        assertThrows(InvalidHeightException.class, () -> {
            HeightParser.parseHeight(".");
        });
        assertThrows(InvalidHeightException.class, () -> {
            HeightParser.parseHeight("-1.50");
        });
        assertThrows(InvalidHeightException.class, () -> {
            HeightParser.parseHeight("10000.00");
        });
        try {
            assertEquals(2.00, HeightParser.parseHeight("2.0"));
            assertEquals(2.00, HeightParser.parseHeight("2"));
            assertFalse(1.00 == HeightParser.parseHeight("2"));
            assertFalse(2.00 == HeightParser.parseHeight("2.01"));
        } catch (InvalidHeightException e) {
            return;
        }
    }
}