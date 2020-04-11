package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the description entered by the user is empty.
 */
public class EmptyDescriptionException extends CustomException {

    public static final String DESCRIPTION_SHOULDN_T_BE_EMPTY = "Description shouldn't be empty";

    public EmptyDescriptionException() {
        super(DESCRIPTION_SHOULDN_T_BE_EMPTY);
    }
}
