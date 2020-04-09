package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidFoodNameException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodNameParserTest {

    @Test
    void parseFoodName() {
        try {
            assertEquals("apple-cider", FoodNameParser.parseFoodName("Apple cider"));
            assertEquals("apple-cider", FoodNameParser.parseFoodName("Apple-Cider"));
            assertEquals("apple-cider", FoodNameParser.parseFoodName("APPLE CIDER"));
        } catch (InvalidFoodNameException e) {
            return;
        }
    }
}