package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user entered a date before the current day when creating/editing tasks.
 */
public class TaskDateBeforeCurrentDateException extends CustomException {

    public static final String DATE_SPECIFIED_MUST_BE_A_CURRENT_OR_A_FUTURE_DATE = "Date specified must be a "
            + "current or a future date";

    public TaskDateBeforeCurrentDateException() {
        super(DATE_SPECIFIED_MUST_BE_A_CURRENT_OR_A_FUTURE_DATE);
    }
}