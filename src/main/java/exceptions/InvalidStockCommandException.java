package exceptions;

public class InvalidStockCommandException extends Exception {
    
    public InvalidStockCommandException(String message) {
        super(message);
    }
}
