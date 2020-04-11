package seedu.duke.exceptions;

public class PidEmptyException extends Exception {
    @Override
    public String getLocalizedMessage() {
        return "Please ensure that pid is not empty.";
    }
}
