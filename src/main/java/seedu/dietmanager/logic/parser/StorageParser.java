package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.commons.exceptions.InvalidWeightException;

import java.util.ArrayList;
import java.util.List;

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
     * Validates the recipe storage data and parsing it into a valid description array.
     *
     * @param recipeDataLine the recipe data description.
     * @return description in standard form.
     * @throws InvalidFormatException if storage data has the wrong description format.
     * @throws NullPointerException   if storage data generates a null value.
     */

    public static String[] parseRecipeDataLine(String recipeDataLine)
            throws InvalidFormatException, NullPointerException {
        int argumentsRequired = 4;
        String[] descriptionArray = recipeDataLine.trim().split("\\s+", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        testAssertions(descriptionArray, argumentsRequired);
        return descriptionArray;
    }

    /**
     * Validates the recipe storage data and parsing it into a valid description array.
     *
     * @param foodRecordDataLine the daily food record data description.
     * @return description in standard form.
     * @throws InvalidFormatException if storage data has the wrong description format.
     * @throws NullPointerException   if storage data generates a null value.
     */

    public static String[] parseFoodRecordDataLine(String foodRecordDataLine)
            throws InvalidFormatException, NullPointerException {
        int argumentsRequired = 2;
        String[] descriptionArray = foodRecordDataLine.trim().split(": ", 2);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        testAssertions(descriptionArray, argumentsRequired);
        return descriptionArray;
    }

    /**
     * Validates the recipe storage data and parsing it into a valid description array.
     *
     * @param timeDescription the time description of a meal record.
     * @return description in standard form.
     * @throws InvalidFormatException if storage data has the wrong description format.
     * @throws NullPointerException   if storage data generates a null value.
     */

    public static String[] parseTimeDescription(String timeDescription)
            throws InvalidFormatException, NullPointerException {
        int argumentsRequired = 2;
        String[] descriptionArray = timeDescription.trim().split("\\s+", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        testAssertions(descriptionArray, argumentsRequired);
        return descriptionArray;
    }

    /**
     * Validates the weightList storage data and parsing it into a valid weight list.
     *
     * @param weightListDataLine the weightList data description.
     * @return weightList in standard form.
     * @throws NullPointerException if storage data generates a null value.
     */

    public static List<Double> parseWeightListDataLine(String weightListDataLine)
            throws NullPointerException, InvalidWeightException {
        String[] descriptionArray = weightListDataLine.trim().split(",");
        List<Double> weightList = new ArrayList<>();
        for (String arg : descriptionArray) {
            double weight = WeightParser.parseWeight(arg);
            weightList.add(weight);
        }
        return weightList;
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