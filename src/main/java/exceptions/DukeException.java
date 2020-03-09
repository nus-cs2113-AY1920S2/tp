package exceptions;

public class DukeException extends Exception{
    public DukeException (String errorMsg) {
        super(errorMsg);
    }

    /**
     * Formats the string that is return from the exception thrown
     * @return String with default error message for this error.
     */
    @Override
    public String toString() {
        return "INVALID CMD ERROR: " + super.getMessage();
    }
}
