package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 *  Exception is thrown if the user didn't follow the correct format to enter the start time for an event.
 */
public class InvalidStartTimeException extends CustomException {

    public static final String INVALID_START_TIME = "Start time provided is invalid or in wrong format "
            + "(Should be HH:MM) 24 Hr format";

    public InvalidStartTimeException() {
        super(INVALID_START_TIME);
    }
}