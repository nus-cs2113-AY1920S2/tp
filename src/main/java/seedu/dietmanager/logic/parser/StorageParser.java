package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidFormatException;

/**
 * StorageParser is the public class responsible for validating the Storage data and
 * parsing it into a valid description array.
 */

public class StorageParser {

    /**
     * Validates the profile storage data and parsing it into a valid description array.
     *
     * @param profileDataLine the profile data description.
     * @return descriptionArray in standard form.
     * @throws InvalidFormatException if storage data has the wrong description format.
     * @throws NullPointerException   if storage data generates a null value.
     */

    public static String[] parseProfileDataLine(String profileDataLine)
            throws InvalidFormatException, NullPointerException {
        int argumentsRequired = 2;
        String[] descriptionArray = profileDataLine.trim().split(": ", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        testAssertions(descriptionArray, argumentsRequired);
        return descriptionArray;
    }

    /**
     * Validates the food nutrition record storage data and parsing it into a valid description array.
     *
     * @param foodNutritionRecordDataLine the food nutrition record data description.
     * @return descriptionArray in standard form.
     * @throws InvalidFormatException if storage data has the wrong description format.
     * @throws NullPointerException   if storage data generates a null value.
     */

    public static String[] parseFoodNutritionRecordDataLine(String foodNutritionRecordDataLine)
            throws InvalidFormatException, NullPointerException {
        int argumentsRequired = 2;
        String[] descriptionArray = foodNutritionRecordDataLine.trim().split(",", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        testAssertions(descriptionArray, argumentsRequired);
        return descriptionArray;
    }

    /**
     * Assertion testing for Description Array.
     */

    public static void testAssertions(String[] descriptionArray, int argumentsRequired) {
        assert (descriptionArray.length == argumentsRequired);
    }

}