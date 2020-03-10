package commands;

import stock.Stock;

/**
 * This class focuses on the 'list' functionality of the application.
 * 
 */
public class ListStockCommand extends StockCommand {
  
    @Override
    public void execute(Stock stock) {
        stock.listIngredient();
        System.out.println("All ingredients listed successfully!");     
    }
}
