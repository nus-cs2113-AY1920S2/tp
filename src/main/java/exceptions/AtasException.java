package exceptions;

//@@author
public class AtasException extends Exception {

    public AtasException(String errorMsg) {
        super(errorMsg);
    }

    /**
     * Formats the string that is return from the exception thrown.
     * @return String with default error message for this error.
     */
    @Override
    public String toString() {
        return "ERROR: " + super.getMessage();
    }
}
