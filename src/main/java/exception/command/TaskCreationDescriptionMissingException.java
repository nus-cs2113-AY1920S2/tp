package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji


/**
 * Exception is thrown if the user didn't enter a description when creating/editing a task.
 */
public class TaskCreationDescriptionMissingException extends CustomException {

    public static final String DESCRIPTION_IS_EMPTY = "Description entered is empty, Please provide a "
            + "valid description";

    public TaskCreationDescriptionMissingException() {
        super(DESCRIPTION_IS_EMPTY);
    }
}
