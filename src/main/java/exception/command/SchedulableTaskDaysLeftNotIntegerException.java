package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user doesn't enter an integer for the number of days left field of a schedulable task.
 */
public class SchedulableTaskDaysLeftNotIntegerException extends CustomException {

    public static final String NUMBER_OF_DAYS_LEFT_SHOULD_BE_AN_INTEGER = "Number of days left should be an integer";

    public SchedulableTaskDaysLeftNotIntegerException() {
        super(NUMBER_OF_DAYS_LEFT_SHOULD_BE_AN_INTEGER);
    }
}