package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidAgeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AgeParserTest {

    @Test
    void parseAge() {
        try {
            assertEquals(20, AgeParser.parseAge("20"));
            assertEquals(40, AgeParser.parseAge("40"));
            assertFalse(40 == AgeParser.parseAge("30"));
            assertFalse(20 == AgeParser.parseAge("30"));
            assertThrows(InvalidAgeException.class, () -> {
                AgeParser.parseAge("Hello");
            });
            assertThrows(InvalidAgeException.class, () -> {
                AgeParser.parseAge("0.10");
            });
            assertThrows(InvalidAgeException.class, () -> {
                AgeParser.parseAge("-10");
            });
            assertThrows(InvalidAgeException.class, () -> {
                AgeParser.parseAge("1000");
            });
        } catch (InvalidAgeException ignored) {
            return;
        }
    }
}