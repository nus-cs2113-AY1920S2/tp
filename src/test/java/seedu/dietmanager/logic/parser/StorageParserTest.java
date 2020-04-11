package seedu.dietmanager.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.commons.exceptions.InvalidWeightException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageParserTest {

    @Test
    void parseProfileDataLine() {
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseProfileDataLine("Name: ");
        });
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseProfileDataLine("Name:John Doe");
        });
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseProfileDataLine("John Doe");
        });
        try {
            assertEquals(String[].class, StorageParser.parseProfileDataLine("Name: John Doe").getClass());
            assertEquals("Name", StorageParser.parseProfileDataLine("Name: John Doe")[0]);
            assertEquals("John Doe", StorageParser.parseProfileDataLine("Name: John Doe")[1]);
        } catch (InvalidFormatException e) {
            return;
        }
    }

    @Test
    void parseRecipeDataLine() {
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseRecipeDataLine("Hello  ");
        });
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseRecipeDataLine(" am John");
        });
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseRecipeDataLine("Hello I am");
        });
        try {
            assertEquals(String[].class, StorageParser.parseRecipeDataLine("Hello   I  am John").getClass());
            assertEquals("Hello", StorageParser.parseRecipeDataLine("Hello I am John")[0]);
            assertEquals("I", StorageParser.parseRecipeDataLine("Hello   I  am John")[1]);
        } catch (InvalidFormatException e) {
            return;
        }
    }

    @Test
    void parseWeightListDataLine() {
        assertThrows(InvalidWeightException.class, () -> {
            StorageParser.parseWeightListDataLine("Hello");
        });
        assertThrows(InvalidWeightException.class, () -> {
            StorageParser.parseWeightListDataLine("Hello,John");
        });
        try {
            assertEquals(ArrayList.class, StorageParser.parseWeightListDataLine("50,40,30").getClass());
            assertEquals(50, StorageParser.parseWeightListDataLine("50,40, 30").get(0));
            assertEquals(40, StorageParser.parseWeightListDataLine("50,  40,30  ").get(1));
        } catch (InvalidWeightException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseFoodNutritionRecordDataLine() {
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseFoodNutritionRecordDataLine("Chicken500");
        });
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseFoodNutritionRecordDataLine("Chicken  ");
        });
        assertThrows(InvalidFormatException.class, () -> {
            StorageParser.parseFoodNutritionRecordDataLine("500");
        });
        try {
            assertEquals(String[].class, StorageParser.parseFoodNutritionRecordDataLine("Chicken,500").getClass());
            assertEquals("Chicken", StorageParser.parseFoodNutritionRecordDataLine("Chicken,500")[0]);
            assertEquals("500", StorageParser.parseFoodNutritionRecordDataLine("Chicken,500  ")[1]);
        } catch (InvalidFormatException e) {
            return;
        }
    }
}