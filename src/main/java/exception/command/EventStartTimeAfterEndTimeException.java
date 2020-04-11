package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user enters/modifies the event such that its start time is after its end time.
 */
public class EventStartTimeAfterEndTimeException extends CustomException {

    public static final String START_TIME_SHOULD_BE_BEFORE_END_TIME = "Start time should be before End time";

    public EventStartTimeAfterEndTimeException() {
        super(START_TIME_SHOULD_BE_BEFORE_END_TIME);
    }
}