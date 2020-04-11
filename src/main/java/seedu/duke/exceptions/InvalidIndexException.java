package seedu.duke.exceptions;

public class InvalidIndexException extends Exception {
    private String command;

    public InvalidIndexException(String commandType) {
        this.command = commandType;
    }

    @Override
    public String getLocalizedMessage() {
        return "Please ensure that the index for " + this.command + " is valid";
    }
}
