package seedu.exception;

/**
 * The general exception message
 */
public class EscException extends Exception{
    public EscException(String message){
        super("An error has occurred! \n\t" +message);
    }
}
