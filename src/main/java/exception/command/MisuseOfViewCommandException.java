package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
/**
 * Exception is thrown if the user didn't follow the correct format to view the list of tasks.
 */
public class MisuseOfViewCommandException extends CustomException {

    public static final String INVALID_VIEW_COMMAND = "Wrong command used to view events (Should be :view )";

    public MisuseOfViewCommandException() {
        super(INVALID_VIEW_COMMAND);
    }
}