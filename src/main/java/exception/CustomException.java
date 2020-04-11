package exception;

//@@author terrytay

/**
 * Used as a base class from which other exceptions are derived from.
 */
public abstract class CustomException extends Exception {
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}
