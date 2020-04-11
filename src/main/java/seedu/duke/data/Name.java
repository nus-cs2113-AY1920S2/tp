package seedu.duke.data;

import seedu.duke.exceptions.InvalidFormatException;

//@@author thanhduc2000
/**
 * Class for name's validation.
 */
public class Name {

    public static final String MESSAGE_NAME_CONSTRAINTS = "Name to be added/edited should contain spaces optionally "
            + "and alphabetic characters with length of between 1 and 64";
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alpha} ]+";
    public static final int NAME_STRING_LIMIT = 64;

    public String name;

    /**
     * Constructor of Name.
     *
     * @param name getting from Parser
     * @throws InvalidFormatException when name is invalid
     */
    public Name(String name) throws InvalidFormatException {
        if (!isValidName(name)) {
            throw new InvalidFormatException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.name = name;
    }

    /**
     * Method to check the validation of patient's name.
     *
     * @param validatedString name getting from Parser
     * @return true if name is not modified or within the limit, false otherwise
     */
    public static boolean isValidName(String validatedString) {
        return (validatedString.matches(NAME_VALIDATION_REGEX) && validatedString.length() <= NAME_STRING_LIMIT)
                || validatedString.isBlank();
    }

    @Override
    public String toString() {
        return name;
    }
}
