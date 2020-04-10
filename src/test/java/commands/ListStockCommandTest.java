package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;

import ingredient.Ingredient;
import exceptions.IngredientNotFoundException;
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
        Ingredient vegetable = 
                new Ingredient("vegetable", Optional.of(5), Optional.of(0.20));
        Ingredient toxicGreens = 
                new Ingredient("toxic greens", Optional.of(3), Optional.of(10.00));
        
        stock.addIngredient(tomatoToAdd);
        stock.addIngredient(tomatoToAddTwo);
        stock.deleteIngredient(tomatoToDelete);
        stock.addIngredient(rice);
        stock.deleteIngredient(riceToDelete);
        stock.addIngredient(potato);
        stock.addIngredient(vegetable);
        stock.addIngredient(toxicGreens);
        
        assertEquals(createListIngredientOutputCopy(), executeList(stock));
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
    
    /**
     * Utility functions ===========================================================================.
     */
    
    /**
     * A utility function of similar implementation to printStock() in the Stock class.
     * This method returns a string instead of void.
     */
    private String printStock(Stock stock) {
        String outputMessage = "";
        
        List<Entry<String, Pair<Integer, Double>>> tempList = 
                new ArrayList<>(Stock.getStock().entrySet());
        
        Collections.sort(tempList, 
                new Comparator<Entry<String, Pair<Integer, Double>>>() {
            
                @Override
                public int compare(Entry<String, Pair<Integer, Double>> firstEntry, 
                        Entry<String, Pair<Integer, Double>> secondEntry) {
                    
                    int firstEntryQuantity = firstEntry.getValue().first();
                    int secondEntryQuantity = secondEntry.getValue().first();
                    return secondEntryQuantity - firstEntryQuantity;
                }
            });      
        
        int ingredientCounter = 1;
        
        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
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
     * A utility function of similar implementation to listIngredient() in the stock class.
     * This method returns a string instead of void.
     */
    private String printlistIngredientToString(Stock stock) {
        String outputMessage = "";
        
        if (Stock.getStock().isEmpty()) {
            outputMessage += ("There is nothing in the stock currently.");
        } else {
            outputMessage += ("Here are the ingredients in the stock currently:"
                    + ls);
            
            outputMessage += printStock(stock) 
                    + ls;
            
            outputMessage += (ls
                    + ls);
        }
        
        return outputMessage;
    }
    
    private String createListIngredientOutputCopy() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock currently:"
                + ls
                + ls);
     
        outputMessage += ("1. [10][$0.50] tomato" 
                + ls
                + "2. [5][$0.20] vegetable"
                + ls
                + "3. [3][$10.00] toxic greens"
                + ls
                + "4. [1][$0.50] potato"
                + ls);
                
        outputMessage += (ls
                + ls
                + "All ingredients listed successfully!");
        
        return outputMessage;
    }
}
