package seedu.nuke.exception;

public class FileNotFoundException extends NotFoundException {
    public FileNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
