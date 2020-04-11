package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the end time field is missing when the user tried to create an event task.
 */
public class EventCreationEndTimeMissingException extends CustomException {

    public static final String END_TIME_ENTERED_IS_EMPTY_PLEASE_PROVIDE_A_VALID_END_TIME = "End time entered is empty,"
            + " Please provide a valid end time";

    public EventCreationEndTimeMissingException() {
        super(END_TIME_ENTERED_IS_EMPTY_PLEASE_PROVIDE_A_VALID_END_TIME);
    }
}
