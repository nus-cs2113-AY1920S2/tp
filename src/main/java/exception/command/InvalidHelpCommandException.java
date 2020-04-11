package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 *  Exception is thrown if the user didn't follow the correct format to view the list of commands supported.
 */
public class InvalidHelpCommandException extends CustomException {

    public static final String INVALID_CALL_TO_HELP_COMMAND = "Wrong command used to view command "
            + "list (Should be :help )";

    public InvalidHelpCommandException() {
        super(INVALID_CALL_TO_HELP_COMMAND);
    }
}