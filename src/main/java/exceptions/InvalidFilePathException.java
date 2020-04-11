package exceptions;

import java.nio.file.InvalidPathException;

/**
 * This class encapsulates all errors pertaining to file path issues.
 */
public class InvalidFilePathException extends InvalidPathException {
    
    public InvalidFilePathException(String input, String reason) {
        super(input, reason);
    }

}
