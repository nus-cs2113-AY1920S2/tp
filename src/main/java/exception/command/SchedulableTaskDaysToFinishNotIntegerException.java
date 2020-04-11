package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user doesn't enter an integer for the number of days finish field of a schedulable task.
 */
public class SchedulableTaskDaysToFinishNotIntegerException extends CustomException {

    public static final String NUMBER_OF_DAYS_TO_FINISH_TASK_SHOULD_BE_AN_INTEGER = "Number of days to finish task "
            + "should be an integer";

    public SchedulableTaskDaysToFinishNotIntegerException() {
        super(NUMBER_OF_DAYS_TO_FINISH_TASK_SHOULD_BE_AN_INTEGER);
    }
}