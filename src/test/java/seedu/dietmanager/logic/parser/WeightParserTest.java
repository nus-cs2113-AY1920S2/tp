package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidWeightException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeightParserTest {

    @Test
    void parseWeight() {
        assertThrows(InvalidWeightException.class, () -> {
            WeightParser.parseWeight("");
        });
        assertThrows(InvalidWeightException.class, () -> {
            WeightParser.parseWeight("Hello");
        });
        assertThrows(InvalidWeightException.class, () -> {
            WeightParser.parseWeight(".");
        });
        assertThrows(InvalidWeightException.class, () -> {
            WeightParser.parseWeight("-1.50");
        });
        assertThrows(InvalidWeightException.class, () -> {
            WeightParser.parseWeight("10000.00");
        });
        try {
            assertEquals(2.00, WeightParser.parseWeight("2.0"));
            assertEquals(2.00, WeightParser.parseWeight("2"));
            assertFalse(1.00 == WeightParser.parseWeight("2"));
            assertFalse(2.00 == WeightParser.parseWeight("2.01"));
        } catch (InvalidWeightException e) {
            return;
        }
    }
}