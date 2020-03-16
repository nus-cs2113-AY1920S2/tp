package seedu.duke.exception;

public class RuntimeException extends ModuleManagerException {
    public RuntimeException(String message) {
        super("Runtime Error: " + message
                + System.lineSeparator()
                + "Please enter your command again!");
    }
}
