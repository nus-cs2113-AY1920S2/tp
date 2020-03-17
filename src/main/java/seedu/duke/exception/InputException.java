package seedu.duke.exception;

/**
 * Signals that some input exceptions when ModuleManager runs.
 */
public class InputException extends ModuleManagerException {

    public InputException(String message, String format) {
        super("Input Error: " + message
                + System.lineSeparator()
                + "Please enter your command again! Format:"
                + System.lineSeparator()
                + format);
    }

    public InputException(String message) {
        super("Input Error: " + message
                + System.lineSeparator()
                + "Please enter your command again!");
    }
}
