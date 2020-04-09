package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidCaloriesException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CaloriesParserTest {

    @Test
    void parseCalories() {
        try {
            assertEquals(20.0, CaloriesParser.parseCalories("20"));
            assertEquals(40, CaloriesParser.parseCalories("40.00"));
            assertFalse(40 == CaloriesParser.parseCalories("30"));
            assertFalse(20 == CaloriesParser.parseCalories("30"));
            assertThrows(InvalidCaloriesException.class, () -> {
                CaloriesParser.parseCalories("Hello");
            });
            assertThrows(InvalidCaloriesException.class, () -> {
                CaloriesParser.parseCalories(".");
            });
            assertThrows(InvalidCaloriesException.class, () -> {
                CaloriesParser.parseCalories("-0.50");
            });
            assertThrows(InvalidCaloriesException.class, () -> {
                CaloriesParser.parseCalories("-1000");
            });
        } catch (InvalidCaloriesException ignored) {
            return;
        }
    }
}