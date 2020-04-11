package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user enters an empty string for the description field of a schedulable task.
 */
public class SchedulableTaskEmptyDescriptionException extends CustomException {

    public static final String DESCRIPTION_OF_TASK_SHOULDN_T_BE_EMPTY = "Description of task shouldn't be empty";

    public SchedulableTaskEmptyDescriptionException() {
        super(DESCRIPTION_OF_TASK_SHOULDN_T_BE_EMPTY);
    }
}