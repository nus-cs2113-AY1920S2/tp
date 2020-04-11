package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
/**
 * Exception is thrown if the user didn't follow the correct format to clear the list of tasks.
 */
public class MisuseOfClearCommandException extends CustomException {

    public static final String INVALID_CLEAR_COMMAND = "Wrong command to clear events (Should be :clear )";

    public MisuseOfClearCommandException() {
        super(INVALID_CLEAR_COMMAND);
    }
}
