package exception.command;

/**
 * Exception is thrown if the user tries to mark a deadline already done as done again.
 */
public class IllegalDoneCommandException extends Exception {
    public IllegalDoneCommandException(String message) {
        super(message);
    }
}
