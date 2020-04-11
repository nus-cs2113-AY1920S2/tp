package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user didn't enter a date when creating/editing a task.
 */
public class TaskCreationDateMissingException extends CustomException {

    public static final String DATE_ENTERED_IS_EMPTY = "Date entered is empty, Please provide a valid date";

    public TaskCreationDateMissingException() {
        super(DATE_ENTERED_IS_EMPTY);
    }
}
