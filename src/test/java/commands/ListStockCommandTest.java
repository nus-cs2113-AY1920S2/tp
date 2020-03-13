package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import ingredient.Ingredient;
import ingredient.IngredientNotFoundException;
import stock.Stock;
import utils.Pair;

class ListStockCommandTest {
    
    private final String ls = System.lineSeparator();

    @Test
    public void execute_executeListCommand_executeNormally() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        Ingredient tomatoToAdd = 
                new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        Ingredient tomatoToAddTwo = 
                new Ingredient("tomato", Optional.of(10), Optional.of(0.50));
        Ingredient tomatoToDelete = 
                new Ingredient("tomato", Optional.of(1), Optional.empty());
        Ingredient rice = 
                new Ingredient("rice", Optional.of(1), Optional.of(0.50));
        Ingredient riceToDelete = 
                new Ingredient("rice", Optional.empty(), Optional.empty());
        Ingredient potato = 
                new Ingredient("potato", Optional.of(1), Optional.of(0.50));
        
        stock.addIngredient(tomatoToAdd);
        stock.addIngredient(tomatoToAddTwo);
        stock.deleteIngredient(tomatoToDelete);
        stock.addIngredient(rice);
        stock.deleteIngredient(riceToDelete);
        stock.addIngredient(potato);
        
        assertEquals(createListIngredientOutputCopy(tomatoToAdd), executeList(stock));
    }

    /**
     * Returns a string representation once a listCommand is executed.
     */
    @Test
    public String executeList(Stock stock) {
        String outputMessage = "";
        outputMessage += printlistIngredientToString(stock);
        outputMessage += ("All ingredients listed successfully!");             
        return outputMessage;
    }
    
    private String createListIngredientOutputCopy(Ingredient ingredient) {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock currently:"
                + ls)
                + ("============================================================"
                + "================================================================"
                + ls);
     
        outputMessage += ("1. [1][$0.50] potato" 
                + ls)
                + ("2. [10][$0.50] tomato" 
                + ls);
                
        outputMessage += ("============================================================"
                + "================================================================"
                + ls
                + "All ingredients listed successfully!");
        
        return outputMessage;
    }
    
    /**
     * Utility functions ===========================================================================
     */
    
    /**
     * A String representation of printing the ingredients in the stock.
     */
    private String printStock(Stock stock) {
        String outputMessage = "";
        int ingredientCounter = 1;
        
        for (Map.Entry<String, Pair<Integer, Double>> ingredient : stock.getStock().entrySet()) {
            String ingredientName = ingredient.getKey();
            int quantity = ingredient.getValue().first();
            double price = ingredient.getValue().second();
            outputMessage += (ls 
                    + ingredientCounter 
                    + ". "
                    + "[" 
                    + quantity 
                    + "]"
                    + "[$" 
                    + String.format("%.2f", price) 
                    + "]"
                    + " " 
                    + ingredientName);
           
            ingredientCounter++;
        }
        
        return outputMessage;
    }
    
    /**
     * A String representation of printing the stock as to when user input 'list stock'.
     */
    private String printlistIngredientToString(Stock stock) {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock currently:"
                + ls)
                + ("============================================================"
                + "================================================================");
        
        outputMessage += printStock(stock) 
                + ls;
        
        outputMessage += ("============================================================"
                + "================================================================"
                + ls);
        
        return outputMessage;
    }
}
