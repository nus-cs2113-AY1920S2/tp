package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 *  Exception is thrown if the user didn't follow the correct format to enter the end time of an event.
 */
public class InvalidEndTimeException extends CustomException {

    public static final String INVALID_END_TIME = "End time provided is invalid or in wrong format (Should be HH:MM)"
            + " 24 Hr format";

    public InvalidEndTimeException() {
        super(INVALID_END_TIME);
    }
}