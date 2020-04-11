package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
/**
 * Exception is thrown if the user didn't follow the correct format to view the list of tasks sorted by
 * their priority.
 */
public class MisuseOfPriorityCommandException extends CustomException {

    public static final String INVALID_PRIORITY_VIEW_COMMAND = "Wrong command used to view events sorted "
            + "by priority (Should be :priority_view )";

    public MisuseOfPriorityCommandException() {
        super(INVALID_PRIORITY_VIEW_COMMAND);
    }
}