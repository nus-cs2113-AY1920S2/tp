package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidGenderException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenderParserTest {

    @Test
    void parseGender() {
        assertThrows(InvalidGenderException.class, () -> {
            GenderParser.parseGender("Hello");
        });
        assertThrows(InvalidGenderException.class, () -> {
            GenderParser.parseGender("");
        });
        assertThrows(InvalidGenderException.class, () -> {
            GenderParser.parseGender(" ");
        });
        try {
            assertEquals("male", GenderParser.parseGender("male"));
            assertEquals("female", GenderParser.parseGender("Female"));
            assertEquals("male", GenderParser.parseGender("MALE"));
            assertFalse("male".equals(GenderParser.parseGender("female")));
            assertFalse("female".equals(GenderParser.parseGender("Male")));
            assertFalse("male".equals(GenderParser.parseGender("MA LE")));
        } catch (InvalidGenderException e) {
            return;
        }
    }
}