package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidNameException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NameParserTest {

    @Test
    void parseName() {
        try {
            assertEquals("John-Doe", NameParser.parseName("John Doe"));
            assertEquals("John-Doe", NameParser.parseName("John-Doe"));
            assertEquals("John-Doe", NameParser.parseName(" John Doe  "));
            assertFalse("John-Doe".equals(NameParser.parseName("John  Doe")));
            assertFalse("JohnDoe".equals(NameParser.parseName("John Doe")));
        } catch (InvalidNameException e) {
            return;
        }
    }
}