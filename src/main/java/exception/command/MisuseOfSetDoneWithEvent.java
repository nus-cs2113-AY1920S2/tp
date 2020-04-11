package exception.command;

import exception.CustomException;

/**
 *  Exception is thrown if the user tries to mark an event task as done.
 */
public class MisuseOfSetDoneWithEvent extends CustomException {

    public static final String INVALID_DONE_COMMAND = "Task of event type cannot be marked as done";

    public MisuseOfSetDoneWithEvent() {
        super(INVALID_DONE_COMMAND);
    }
}
