package commands;

import stock.Stock;

/**
 * Represents a StockCommand. A StockCommand can be executed by
 * consists of methods to parse user's input into acceptable format
 * that can be used by its super classes such as AddStockCommand.
 * 
 */
public class StockCommand {
    
    protected Stock stock;   
    
    public void setData(Stock stock) {
        this.stock = stock;
    }
    
    public void execute(Stock stock) {
        System.out.println("This method is to be implemented by child classes");
    }
    
    /**
     * Extract the name of the ingredient from a string.
     * For example, 'i/tomato' will return 'tomato'.
     * 
     */ 
    protected String parseIngredientName(String ingredientNameInput) {
        String ingredientName = ingredientNameInput.trim()
                .substring(2, ingredientNameInput.length());
        
        return ingredientName;
    }
    
    /**
     * Extract the quantity of the ingredient from a string.
     * For example, 'q/10' will return '10'.
     * 
     */ 
    protected int parseIngredientQuantity(String ingredientQuantityInput) {
        String trimmedInput = ingredientQuantityInput.trim();
        
        int quantity = Integer.parseInt(trimmedInput
                .substring(2, ingredientQuantityInput.length()));
        return quantity;
    }
    
    /**
     * Extract the price of the ingredient from a string.
     * For example, 'p/$0.50' will return '0.5'.
     * Note that the price will not strictly be in 2 decimal 
     * places. This will be handled in the AddCommand.
     * 
     */ 
    protected double parseIngredientPrice(String ingredientPriceInput) {
        double price = Double.parseDouble(ingredientPriceInput.trim()
                .substring(3, ingredientPriceInput.length()));
        
        return price;
    }
    
    /**
     * Convenience constructor for ExitCommand() without parameters.
     */
    public void execute() {
        throw new IllegalStateException("This method is to be implemented by child classes");
    }
}
