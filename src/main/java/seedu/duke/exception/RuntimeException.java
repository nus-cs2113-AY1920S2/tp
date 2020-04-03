package seedu.duke.exception;

public class RuntimeException extends ModuleManagerException {
    public RuntimeException(String message) {
        super("Runtime Exception: " + message
                + System.lineSeparator()
                + "Please enter your command again!");
    }
}
