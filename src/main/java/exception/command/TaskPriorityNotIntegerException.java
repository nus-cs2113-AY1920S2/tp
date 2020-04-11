package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user didn't enter a integer for the priority field when creating/editing a task.
 */
public class TaskPriorityNotIntegerException extends CustomException {

    public static final String PRIORITY_SHOULD_BE_AN_INTEGER = "Priority should be an integer";

    public TaskPriorityNotIntegerException() {
        super(PRIORITY_SHOULD_BE_AN_INTEGER);
    }
}