package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidFormatException;

/**
 * Parser is the public class responsible for parsing user input and generating the relevant commands.
 */

public class StorageParser {

    /**
     * Parses the user input and prepares it to be analysed and used to generate commands.
     *
     * @param profileDataLine       the command description.
     * @throws InvalidFormatException if user input has the wrong format.
     */

    public static String[] parseProfileDataLine(String profileDataLine) throws InvalidFormatException,
            NullPointerException {
        int argumentsRequired = 2;
        String[] descriptionArray = profileDataLine.trim().split(": ", argumentsRequired);
        if (descriptionArray.length != argumentsRequired) {
            throw new InvalidFormatException();
        }
        return descriptionArray;
    }

}