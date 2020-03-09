package seedu.nuke.exception;

public class ModuleNotFoundException extends NotFoundException {
    public ModuleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
