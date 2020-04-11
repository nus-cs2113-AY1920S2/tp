package exception;

//@@author terrytay

/**
 * Exception is thrown if the user didn't specify a message when adding one.
 */
public class NotesInvalidAddMessageException extends CustomException {

    public static final String SPECIFY_MESSAGE_TO_ADD = "Please specify a message to add i.e. add [message here]";

    public NotesInvalidAddMessageException() {
        super(SPECIFY_MESSAGE_TO_ADD);
    }
}
