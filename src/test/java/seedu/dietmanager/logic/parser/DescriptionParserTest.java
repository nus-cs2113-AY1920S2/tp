package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DescriptionParserTest {

    @Test
    void parseDescription() {
        assertThrows(InvalidFormatException.class, () -> {
            DescriptionParser.parseDescription("Hello John", 3);
        });
        assertThrows(InvalidFormatException.class, () -> {
            DescriptionParser.parseDescription("Hello John Doe", 4);
        });
        try {
            assertTrue(DescriptionParser.parseDescription(
                    "Hello John Doe", 3).length == 3);
            assertFalse(DescriptionParser.parseDescription(
                    "Hello John", 2).length == 3);
        } catch (InvalidFormatException ignored) {
            return;
        }

    }
}