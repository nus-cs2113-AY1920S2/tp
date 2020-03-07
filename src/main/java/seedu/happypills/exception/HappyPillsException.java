package seedu.happypills.exception;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class HappyPillsException extends Exception {
    /**
     * Updates Exception message.
     * @param message Contain relevant information on the failed constraint(s)
     */
    public HappyPillsException(String message) {
        super(message);
    }
}
