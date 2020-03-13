package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class StockCommandTest {

    @Test
    public void execute_executeStockCommand_throwsException() {
        try {
            StockCommand stockCommand = new StockCommand();
            stockCommand.execute();
        } catch (IllegalStateException ise) {
            assertEquals("This method is to be implemented by child classes",
                    ise.getMessage());
        }
    }
    
    @Test
    public void parse_parseIngredientName_parseNormally() {
        StockCommand stockCommand = new StockCommand();
        assertEquals("tomato", stockCommand.parseIngredientName("i/tomato"));
        assertFalse(stockCommand.parseIngredientName("i/tomato").equals("potato"));
    }
    
    @Test
    public void parse_parseIngredientQuantity_parseNormally() {
        StockCommand stockCommand = new StockCommand();
        
        try {
            assertEquals(10, stockCommand.parseIngredientQuantity("q/10"));
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the quantity specified is an integer!", 
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please enter a positive value for the quantity to be added!",
                    isce.getMessage());
        }
    }
    
    @Test
    public void parse_parseIngredientQuantity_quantityIsTypeDouble() {
        StockCommand stockCommand = new StockCommand();
        
        try {
            int parsedInvalidQuantity = stockCommand.parseIngredientQuantity("q/10.0");
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the quantity specified is an integer!",
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please ensure that the quantity specified is an integer!",
                    isce.getMessage());
        }
    }
    
    @Test
    public void parse_parseIngredientQuantity_quantityIsNotInteger() {
        StockCommand stockCommand = new StockCommand();
        

        try {
            int parsedInvalidQuantityTwo = stockCommand.parseIngredientQuantity("q/LOL");
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the quantity specified is an integer!",
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please ensure that the quantity specified is an integer!", 
                    isce.getMessage());
        }
    }
    
    @Test
    public void parse_parseIngredientQuantity_quantityIsNegative() {
        StockCommand stockCommand = new StockCommand();
        
        try {
            int parsedInvalidQuantityThree = stockCommand.parseIngredientQuantity("q/-10");
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the quantity specified is an integer!", 
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please enter a positive value for the quantity to be added!",
                    isce.getMessage());
        }
        
    }
    
    @Test
    public void parse_parseIngredientPrice_parseNormally() 
            throws InvalidStockCommandException {
        
        StockCommand stockCommand = new StockCommand();

        try {
            assertEquals(0.5, stockCommand.parseIngredientPrice("p/$0.50"));
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the price specified has a '$' sign and is a decimal!", 
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please enter a positive value for the ingredient's price!",
                    isce.getMessage());
        }
    }
    
    @Test
    public void parse_parseIngredientPrice_priceIsIntger() {
        StockCommand stockCommand = new StockCommand();
        
        try {
            double parsedInvalidPrice = stockCommand.parseIngredientPrice("p/$10");
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the price specified has a '$' sign and is a decimal!", 
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please enter a positive value for the ingredient's price!",
                    isce.getMessage());
        }
    }
    
    @Test
    public void parse_parseIngredientPrice_priceIsDouble() {
        StockCommand stockCommand = new StockCommand();

        try {
            double parsedInvalidPriceTwo = stockCommand.parseIngredientPrice("p/LOL");
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the price specified has a '$' sign and is a decimal!", 
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please ensure that the price specified has a '$' sign and is a decimal!",
                    isce.getMessage());
        }
    }
    
    @Test
    public void parse_parseIngredientPrice_priceMissingDollarSign() {
        StockCommand stockCommand = new StockCommand();
        
        try {
            double parsedInvalidPriceThree = stockCommand.parseIngredientPrice("p/10.0");
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the price specified has a '$' sign and is a decimal!", 
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please ensure that the price specified has a '$' sign and is a decimal!",
                    isce.getMessage());
        }
    }
    
    @Test
    public void parse_parseIngredientPrice_priceIsNegative() {
        StockCommand stockCommand = new StockCommand();

        try {
            double parsedInvalidPriceFour = stockCommand.parseIngredientPrice("p/$-10.0");
        } catch (NumberFormatException nfe) {
            assertEquals("Please ensure that the price specified has a '$' sign and is a decimal!", 
                    nfe.getMessage());
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please enter a positive value for the ingredient's price!", 
                    isce.getMessage());
        }
    }

}
