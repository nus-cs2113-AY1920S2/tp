package seedu.duke.exception;

/**
 * Signals that some exceptions when ModuleManager runs.
 */
public class ModuleManagerException extends Exception {
    private String message;

    public ModuleManagerException(String message) {
        super(message);
        this.message = message;
    }

}
