package seedu.duke.exceptions;

public class IndexNotIntegerException extends Exception {
    private String command;

    public IndexNotIntegerException(String commandType) {
        this.command = commandType;
    }

    @Override
    public String getLocalizedMessage() {
        return "Please input a valid integer that is within range of the list as the index for the command " + command;
    }
}
