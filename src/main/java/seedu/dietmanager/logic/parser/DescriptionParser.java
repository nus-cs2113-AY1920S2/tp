package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidFormatException;

/**
 * Parser is the public class responsible for parsing user input and generating the relevant commands.
 */

public class DescriptionParser {

    /**
     * Parses the user input and prepares it to be analysed and used to generate commands.
     *
     * @param description       the command description.
     * @param argumentsRequired the number of arguments required by the command.
     * @throws InvalidFormatException if user input has the wrong format.
     */

    public static String[] parseDescription(String description, int argumentsRequired) throws InvalidFormatException,
            NullPointerException {
        String[] descriptionArray = description.trim().split("\\s+", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        return descriptionArray;
    }

}