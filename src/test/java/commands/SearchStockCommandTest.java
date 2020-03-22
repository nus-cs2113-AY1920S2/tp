package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import ingredient.Ingredient;
import ingredient.IngredientNotFoundException;
import stock.Stock;
import utils.Pair;

class SearchStockCommandTest {
    
    private final String ls = System.lineSeparator();
    
    @Test
    public void construct_SearchStockCommand_constructNormally() 
            throws InvalidStockCommandException {
    
        String keywordSupplied = "tomato";
        String keywordInCommand = new SearchStockCommand(" tomato").getKeyword();
        assertTrue(keywordSupplied.equals(keywordInCommand));
    }
    
    @Test
    public void construct_SearchStockCommand_blankKeywordSupplied() 
            throws InvalidStockCommandException {
        
        try {
            new SearchStockCommand(" ").getKeyword();
        } catch (InvalidStockCommandException isce) {
            assertEquals(isce.getMessage(), 
                    "Please enter an ingredient's name to be searched against the stock.");
        }
    }   

    @Test
    public void execute_executeSearchCommand_executeNormally() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        Ingredient tomato = 
                new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        Ingredient vegetable = 
                new Ingredient("vegetable", Optional.of(10), Optional.of(0.50));
        Ingredient salt = 
                new Ingredient("salt", Optional.of(1), Optional.of(0.30));
        Ingredient rice = 
                new Ingredient("rice", Optional.of(1), Optional.of(0.50));
        Ingredient flour = 
                new Ingredient("flour", Optional.of(10), Optional.of(0.30));
        Ingredient potato = 
                new Ingredient("potato", Optional.of(1), Optional.of(0.50));
        
        stock.addIngredient(tomato);
        stock.addIngredient(vegetable);
        stock.addIngredient(salt);
        stock.addIngredient(flour);
        stock.addIngredient(rice);
        stock.addIngredient(potato);
        
        assertEquals(createSearchStockOutputCopy(), executeSearch(stock, "potato"));
        assertEquals(createSearchStockOutputCopyTwo(), executeSearch(stock, "to"));
        assertEquals(createSearchStockOutputCopyThree(), executeSearch(stock, "t"));       
    }
    
    /**
     * Utility functions ===========================================================================
     */
    
    /**
     * Returns a string representation once a StockCommand is executed.
     */
    @Test
    public String executeSearch(Stock stock, String keyword) {
        String outputMessage = "";
        outputMessage += printSearchStockToString(stock, keyword);           
        return outputMessage;
    }
    
    /**
     * A String representation of printing the ingredients in the stock that contains
     * a supplied keyword. This method has the same implementation of searchStock() in 
     * the Stock class, except that this returns a string instead of void.
     */
    private String printStock(Stock stock, String keyword) {
        String outputMessage = "";
        int ingredientCounter = 1;
        
        for (Map.Entry<String, Pair<Integer, Double>> ingredient : stock.getStock().entrySet()) {
            String ingredientName = ingredient.getKey();
            if (ingredientName.contains(keyword)) {
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
            } else {
                continue;
            }           
            ingredientCounter++;
        }
        
        return outputMessage;
    }
    
    private String createSearchStockOutputCopy() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock that matches the keyword:"
                + ls)
                + ("============================================================"
                + "================================================================"
                + ls);
        
        outputMessage += ("1. [1][$0.50] potato"
                + ls); 
                
        outputMessage += ("============================================================"
                + "================================================================"
                + ls);
        
        return outputMessage;
    }
    
    private String createSearchStockOutputCopyTwo() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock that matches the keyword:"
                + ls)
                + ("============================================================"
                + "================================================================"
                + ls);
        
        outputMessage += ("1. [1][$0.50] potato"
                + ls
                + "2. [1][$0.50] tomato"
                + ls); 
                
        outputMessage += ("============================================================"
                + "================================================================"
                + ls);
        
        return outputMessage;
    }
    
    private String createSearchStockOutputCopyThree() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock that matches the keyword:"
                + ls)
                + ("============================================================"
                + "================================================================"
                + ls);
        
        outputMessage += ("1. [1][$0.30] salt"
                + ls
                + "2. [1][$0.50] potato"
                + ls
                + "3. [1][$0.50] tomato"
                + ls
                + "4. [10][$0.50] vegetable"
                + ls); 
                
        outputMessage += ("============================================================"
                + "================================================================"
                + ls);
        
        return outputMessage;
    }
    
    /**
     * A String representation of printing the stock as to when user input 'search stock'.
     */
    private String printSearchStockToString(Stock stock, String keyword) {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock that matches the keyword:"
                + ls)
                + ("============================================================"
                + "================================================================");
        
        outputMessage += printStock(stock, keyword) 
                + ls;
        
        outputMessage += ("============================================================"
                + "================================================================"
                + ls);
        
        return outputMessage;
    }
}
