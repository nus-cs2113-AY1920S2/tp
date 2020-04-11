package seedu.duke.exception;

/**
 * Signals that some exceptions when ModuleManager store and load.
 */
public class StorageException extends ModuleManagerException {
    private String message;

    public StorageException(String message) {
        super(message);
    }
}
