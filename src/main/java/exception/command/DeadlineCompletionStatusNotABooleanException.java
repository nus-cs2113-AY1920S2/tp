package exception.command;

import exception.CustomException;
import ui.Constants;

//@@author GanapathySanathBalaji

/**
 * Exception thrown if the value entered by the user for the completion status isn't a valid boolean value.
 */
public class DeadlineCompletionStatusNotABooleanException extends CustomException {

    public DeadlineCompletionStatusNotABooleanException() {
        super(Constants.COMPLETION_STATUS_ERROR_MESSAGE);
    }
}
