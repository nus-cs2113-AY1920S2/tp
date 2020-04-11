package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
/**
 * Exception is thrown if the user didn't follow the correct format to view the list of tasks in the countdown format.
 */
public class MisuseOfCountdownCommandException extends CustomException {

    public static final String INVALID_COUNTDOWN_COMMAND = "Wrong command used to view countdown of events"
            + " (Should be :countdown )";

    public MisuseOfCountdownCommandException() {
        super(INVALID_COUNTDOWN_COMMAND);
    }
}
