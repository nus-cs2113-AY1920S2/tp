package common.exception;

/**
 * Exception is thrown whenever user types in unauthorized commands.
 */
public class WfException extends Exception {

    public WfException(String s) {
        super(s);
    }
}
