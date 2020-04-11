package commands;

import stock.Stock;

/**
 * This class focuses on the 'list' functionality of the 'Stock' category
 * in the application.
 * 
 */
public class ListStockCommand extends StockCommand {
  
    @Override
    public void execute(Stock stock) {
        stock.listIngredient();    
    }
}
