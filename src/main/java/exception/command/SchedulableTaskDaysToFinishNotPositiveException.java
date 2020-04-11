package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user doesn't enter an positive integer for the number of days left field of a
 * schedulable task.
 */
public class SchedulableTaskDaysToFinishNotPositiveException extends CustomException {

    public static final String NUMBER_OF_DAYS_TO_FINISH_SHOULD_BE_GREATER_THAN_0 = "Number of days to finish should "
            + "be greater than 0";

    public SchedulableTaskDaysToFinishNotPositiveException() {
        super(NUMBER_OF_DAYS_TO_FINISH_SHOULD_BE_GREATER_THAN_0);
    }
}
