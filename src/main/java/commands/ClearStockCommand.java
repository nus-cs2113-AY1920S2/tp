package commands;

import stock.Stock;

/**
 * This class focuses on the 'clear' functionality of the 'Stock' category
 * in the application.
 * 
 */
public class ClearStockCommand extends StockCommand {

    @Override
    public void execute(Stock stock) {
        stock.clearStock();    
    }
}
