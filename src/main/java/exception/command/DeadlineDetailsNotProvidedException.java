package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user didn't enter the details of the deadline when the user tried to create it.
 */
public class DeadlineDetailsNotProvidedException extends CustomException {

    public static final String DEADLINE_DETAILS_ARE_NOT_PROVIDED = "Deadline details are not provided";

    public DeadlineDetailsNotProvidedException() {
        super(DEADLINE_DETAILS_ARE_NOT_PROVIDED);
    }
}
