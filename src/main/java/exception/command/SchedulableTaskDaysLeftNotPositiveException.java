package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user doesn't enter an positive integer for the number of days left field of a
 * schedulable task.
 */
public class SchedulableTaskDaysLeftNotPositiveException extends CustomException {

    public static final String NUMBER_OF_DAYS_LEFT_SHOULD_BE_GREATER_THAN_0 = "Number of days left should be greater"
            + " than 0";

    public SchedulableTaskDaysLeftNotPositiveException() {
        super(NUMBER_OF_DAYS_LEFT_SHOULD_BE_GREATER_THAN_0);
    }
}
