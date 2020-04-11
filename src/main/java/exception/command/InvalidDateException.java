package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user didn't follow the correct format to enter a date.
 */
public class InvalidDateException extends CustomException {

    public static final String INVALID_DATE = "Date provided is invalid or is in wrong format (Should be YYYY-MM-DD)";

    public InvalidDateException() {
        super(INVALID_DATE);
    }
}