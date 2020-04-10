package common.exception;

/**
 * Exception is thrown when invalid nusmods link is provided.
 */
public class InvalidUrlException extends Exception {

    public InvalidUrlException(String s) {
        super(s);
    }
}
