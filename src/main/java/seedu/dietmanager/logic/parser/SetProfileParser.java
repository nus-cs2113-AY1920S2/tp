package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidFormatException;

public class SetProfileParser {

    private static final int ARGUMENTS_REQUIRED = 6;

    /**
     * Parses the user input and prepares it to be analysed and used to generate commands.
     *
     * @param description the command description.
     * @throws InvalidFormatException if user input has the wrong format.
     */

    public static String[] parseProfileDescription(String description) throws InvalidFormatException {
        String[] descriptionArray = description.trim().split("\\s+", ARGUMENTS_REQUIRED);
        if (descriptionArray.length != ARGUMENTS_REQUIRED) {
            throw new InvalidFormatException();
        }
        testAssertions(descriptionArray);
        return descriptionArray;
    }

    public static void testAssertions(String[] descriptionArray) {
        assert (descriptionArray.length == ARGUMENTS_REQUIRED);
    }

}
