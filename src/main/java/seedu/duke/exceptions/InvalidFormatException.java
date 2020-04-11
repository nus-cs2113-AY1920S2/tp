package seedu.duke.exceptions;

//@@author thanhduc2000
/**
 * Source:
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook
 * /data/exception/IllegalValueException.java
 * Handle with format validation in addp/editp
 */
public class InvalidFormatException extends Exception {

    /**
     * Constructor of InvalidFormatException.
     *
     * @param message should contain relevant information on the failed constraint(s)
     */
    public InvalidFormatException(String message) {
        super(message);
    }
}
