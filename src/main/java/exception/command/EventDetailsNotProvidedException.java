package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user didn't enter the details of the event when the user tried to create it.
 */
public class EventDetailsNotProvidedException extends CustomException {

    public static final String EVENT_DETAILS_ARE_NOT_PROVIDED = "Event details are not provided";

    public EventDetailsNotProvidedException() {
        super(EVENT_DETAILS_ARE_NOT_PROVIDED);
    }
}
