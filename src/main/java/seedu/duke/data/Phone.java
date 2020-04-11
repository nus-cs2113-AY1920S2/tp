package seedu.duke.data;

import seedu.duke.exceptions.InvalidFormatException;

//@@author thanhduc2000
/**
 * Class for phone's validation.
 */
public class Phone {

    public static final String MESSAGE_PHONE_CONSTRAINTS = "Patient's phone numbers should contain 8 numbers "
            + "and no whitespaces between digits";
    public static final String PHONE_VALIDATION_REGEX = "\\d+";
    public static final int NUMBER_OF_PHONE_DIGITS = 8;

    public String phone;

    /**
     * Constructor of Phone.
     *
     * @param phone getting from Parser
     * @throws InvalidFormatException when phone is invalid
     */
    public Phone(String phone) throws InvalidFormatException {
        if (!isValidPhone(phone)) {
            throw new InvalidFormatException(MESSAGE_PHONE_CONSTRAINTS);
        }
        this.phone = phone;
    }

    /**
     * Method to check the validation of phone number.
     *
     * @param phone getting from Parser
     * @return true if phone contains 8 digits, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        return (phone.matches(PHONE_VALIDATION_REGEX) && phone.length() == NUMBER_OF_PHONE_DIGITS) || phone.isBlank();
    }

    @Override
    public String toString() {
        return phone;
    }
}
