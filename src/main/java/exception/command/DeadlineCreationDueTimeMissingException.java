package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the due time field is missing when the user tries to create a new deadline.
 */
public class DeadlineCreationDueTimeMissingException extends CustomException {

    public static final String DUE_TIME_ENTERED_IS_EMPTY_PLEASE_PROVIDE_A_VALID_DUE_TIME = "Due time entered is empty, "
            + "Please provide a valid due time";

    public DeadlineCreationDueTimeMissingException() {
        super(DUE_TIME_ENTERED_IS_EMPTY_PLEASE_PROVIDE_A_VALID_DUE_TIME);
    }
}
