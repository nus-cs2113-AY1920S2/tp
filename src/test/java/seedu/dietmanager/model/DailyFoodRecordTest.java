package seedu.dietmanager.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DailyFoodRecordTest {

    @Test
    void setDate() {
        DailyFoodRecord test = new DailyFoodRecord("monday");
        test.setDate("monday");
        assertEquals("monday",test.getDate());
    }

    @Test
    void getDate() {
        DailyFoodRecord test = new DailyFoodRecord("monday");
        test.setDate("monday");
        assertEquals("monday", test.getDate());
    }

}