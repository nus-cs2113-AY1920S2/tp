package exceptions;

import java.io.IOException;

/**
 * Represents an exception that is thrown whenever read or write
 * operation fails.
 * 
 */
public class StockReadWriteException extends IOException {
    
    public StockReadWriteException(String msg) {
        super(msg);
    }
}