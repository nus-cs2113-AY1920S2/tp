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
    
    /**
     * Extract the name of the ingredient from a string. For example, 
     * 'i/tomato' will return 'tomato'.
     * 
     */ 
    protected String parseIngredientName(String ingredientNameInput) {
        String ingredientName = ingredientNameInput.trim()
                .substring(2, ingredientNameInput.length());
        
        return ingredientName;
    }
    
    /**
     * Extract the quantity of the ingredient from a string. For example, 
     * 'q/10' will return '10'.
     * @throws InvalidStockCommandException If the quantity specified cannot
     *                                      be parsed into an integer or the
     *                                      quantity is a negative value.
     * 
     */ 
    protected int parseIngredientQuantity(String ingredientQuantityInput) 
            throws InvalidStockCommandException {
        
        String trimmedInput = ingredientQuantityInput.trim();
        
        try {
            int quantity = Integer.parseInt(trimmedInput.substring(
                    2, ingredientQuantityInput.length()));
            
            if (quantity < 0) {
                throw new InvalidStockCommandException("Please enter a "
                        + "positive value for the quantity to be added!");
            } 
            return quantity;            
        } catch (NumberFormatException nfe) {
            throw new InvalidStockCommandException("Please ensure that the "
                    + "quantity specified is an integer!");
        }        
    }
    
    /**
     * Extract the price of the ingredient from a string. For example, 
     * 'p/$0.50' will return '0.5'. Note that the price will not strictly 
     * be in 2 decimal places. This will be handled in the AddCommand.
     * @throws InvalidStockCommandException If the price specified does not
     *                                      have a '$' sign, is not a 
     *                                      decimal or is not a positive value.
     * 
     */ 
    protected double parseIngredientPrice(String ingredientPriceInput) 
            throws InvalidStockCommandException {
        
        String trimmedInput = ingredientPriceInput.trim();
        
        try {
            
            double price = Double.parseDouble(trimmedInput.substring(
                    3, ingredientPriceInput.length()));
            
            if (price < 0.0) {
                throw new InvalidStockCommandException("Please enter a"
                        + " positive value for the ingredient's price!");
            } 
            return price;
        } catch (NumberFormatException nfe) {
            throw new InvalidStockCommandException("Please ensure that the "
                    + "price specified has a '$' sign and is a decimal!");
        }
    }
    
    /**
     * Convenience constructor for ExitCommand() without parameters.
     */
    public void execute() {
        throw new IllegalStateException("This method is to be implemented by child classes");
    }
    
    public void execute(Stock stock) {
        System.out.println("This method is to be implemented by child classes");
    }
}
