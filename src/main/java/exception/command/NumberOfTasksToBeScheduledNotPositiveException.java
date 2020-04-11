package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user doesn't enter a positive integer for the  number of tasks to be scheduled.
 */
public class NumberOfTasksToBeScheduledNotPositiveException extends CustomException {

    public static final String NUMBER_OF_TASKS_TO_BE_SCHEDULED_AS_MENTIONED_IS_NOT_A_POSITIVE_INTEGER = "Number of "
            + "tasks to be scheduled as mentioned is not a positive integer";

    public NumberOfTasksToBeScheduledNotPositiveException() {
        super(NUMBER_OF_TASKS_TO_BE_SCHEDULED_AS_MENTIONED_IS_NOT_A_POSITIVE_INTEGER);
    }
}