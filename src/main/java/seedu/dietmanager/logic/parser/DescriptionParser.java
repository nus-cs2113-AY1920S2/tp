package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidFormatException;

/**
 * DescriptionParser is the public class responsible for validating the user input and
 * parsing it into a valid description array.
 */

public class DescriptionParser {

    /**
     * Description Array parsed from user input.
     */

    private static String[] descriptionArray;

    /**
     * Validates the user input and parsing it into a valid description array.
     *
     * @param description       the command description.
     * @param argumentsRequired the number of arguments required by the command.
     * @return descriptionArray in standard form.
     * @throws InvalidFormatException if input has the wrong description format.
     * @throws NullPointerException   if input generates a null value.
     */

    public static String[] parseDescription(String description, int argumentsRequired)
            throws InvalidFormatException, NullPointerException {
        descriptionArray = description.trim().split("\\s+", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        for (String arg : descriptionArray) {
            arg.trim();
        }
        testAssertions(argumentsRequired);
        return descriptionArray;
    }

    /**
     * Assertion testing for Description Array.
     */

    public static void testAssertions(int argumentsRequired) {
        assert (descriptionArray.length == argumentsRequired);
    }

}