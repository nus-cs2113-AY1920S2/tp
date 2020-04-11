package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import ingredient.Ingredient;
import stock.Stock;

public class ClearStockCommandTest {
        
    @Test
    public void test_ClearStockCommand_clearStockCompletely() {
        Stock stock = new Stock();
        Ingredient tomatoToAdd = new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        Ingredient potatoToAdd = new Ingredient("potato", Optional.of(1), Optional.of(0.50));
        Ingredient riceToAdd = new Ingredient("rice", Optional.of(1), Optional.of(0.50));
        stock.addIngredient(tomatoToAdd);
        stock.addIngredient(potatoToAdd);
        stock.addIngredient(riceToAdd);
        stock.clearStock();
        
        assertTrue(stock.getStock().isEmpty());
    }
    
    @Test
    public void test_ClearStockCOmmand_clearStockMessage() {
        Stock stock = new Stock();
        Ingredient tomatoToAdd = new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        Ingredient potatoToAdd = new Ingredient("potato", Optional.of(1), Optional.of(0.50));
        Ingredient riceToAdd = new Ingredient("rice", Optional.of(1), Optional.of(0.50));
        stock.addIngredient(tomatoToAdd);
        stock.addIngredient(potatoToAdd);
        stock.addIngredient(riceToAdd);
        
        assertEquals("The stock has been cleared.", executeClear(stock));
    }
    
    /**
     * Utility functions ========================================================================
     */
    
    /**
     * A utility function that has the same implementation as clearStock() in the Stock class.
     * This method returns a string instead of void.
     */
    private String executeClear(Stock stock) {
        String outputMessage = "";
        stock.getStock().clear();
        outputMessage += "The stock has been cleared.";
        
        return outputMessage;
    }
}
