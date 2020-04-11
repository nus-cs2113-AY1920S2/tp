package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user enters an integer for the number of days left field as compared to the number of
 * days to finish field of a schedulable task.
 */
public class SchedulableTaskDaysToFinishMoreThanDaysLeftException extends CustomException {

    public static final String NUMBER_OF_DAYS_LESS_THAN_NUMBER_OF_DAYS_TO_FINISH_THE_TASK = "Number of days left should"
            + " not be lesser than number of days to finish the task";

    public SchedulableTaskDaysToFinishMoreThanDaysLeftException() {
        super(NUMBER_OF_DAYS_LESS_THAN_NUMBER_OF_DAYS_TO_FINISH_THE_TASK);
    }
}