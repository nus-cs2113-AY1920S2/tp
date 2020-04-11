package seedu.duke.exceptions;

public class UnknownCommandException extends Exception {
    @Override
    public String getLocalizedMessage() {
        return "Unknown command";
    }
}
