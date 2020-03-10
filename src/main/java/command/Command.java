package command;

import stock.Stock;

/**
 * Represents a command. A command can be executed by
 * specifying Duke as a parameter, which will return a CommandResult.
 * 
 */
public class Command {
    
    protected Stock stock;   
    
    public void setData(Stock stock) {
        this.stock = stock;
    }
        
    public CommandResult execute() {
        throw new IllegalStateException("This method is to be implemented by child classes");
    }
}
