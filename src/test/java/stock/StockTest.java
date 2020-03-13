package stock;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ingredient.Ingredient;
import ingredient.IngredientNotFoundException;
import stock.Stock;
import utils.Pair;

class StockTest {
    
    /**
     * Test that the ingredient saves the new price supplied whenever the user inputs it.
     */
    @Test
    public void addIngredient_AddIngredientWithQuantitySupplied_addNormally() {
        Stock stock = new Stock(); 
        stock.addIngredient(new Ingredient("tomato", Optional.of(1), Optional.of(0.40)));
        stock.addIngredient(new Ingredient("tomato", Optional.of(2), Optional.of(0.50)));     
        assertFalse(stock.getStock().get("tomato").second().equals(0.40));
    }
    
    /**
     * Test that the ingredient's quantity is added up.
     */
    @Test
    public void addIngredient_AddIngredientWithQuantitySupplied_sumQuantity() {
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("tomato", Optional.of(1), Optional.of(0.40)));
        stock.addIngredient(new Ingredient("tomato", Optional.of(2), Optional.of(0.50))); 
        assertTrue(stock.getStock().get("tomato").first().equals(3));
        assertFalse(stock.getStock().get("tomato").first().equals(1));
    }
    
    /**
     * Test against the stockCopy. 
     */
    @Test
    public void testAdd_CompareAgainstStockCopy_TrueIfSameContent() {
        Stock stock = new Stock();
        Map<String, Pair<Integer, Double>> stockCopy = createStockCopy();
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        assertEquals(stock.getStock().keySet(), (stockCopy.keySet()));
    }
    
    /**
     * Test for the case where user provides a quantity to be deleted.
     */
    @Test
    public void deleteIngredient_DeleteIngredientWithQuantity_deleteNormally() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("tomato", Optional.of(1), Optional.of(0.50)));
        stock.deleteIngredient(new Ingredient("tomato", Optional.of(1), Optional.empty()));        
        assertEquals(0, stock.getStock().get("tomato").first());
    }
    
    /**
     * Test for the case where user did not provide a quantity to be deleted.
     */
    @Test
    public void deleteIngredient_DeleteIngredientWithoutQuantity_deleteNormally() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("banana", Optional.of(3), Optional.of(0.50)));
        stock.deleteIngredient(new Ingredient("banana", Optional.empty(), Optional.empty()));
        assertFalse(stock.getStock().containsKey("banana"));
    }
    
    @Test
    public void testDelete_CompareAgainstStockCopy_TrueIfSameContent() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("banana", Optional.of(3), Optional.of(0.50)));
        stock.deleteIngredient(new Ingredient("banana", Optional.empty(), Optional.empty()));
        assertFalse(stock.getStock().containsKey("banana"));
        
        Map<String, Pair<Integer, Double>> stockCopy = createStockCopy();
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        assertEquals(stock.getStock().keySet(), (stockCopy.keySet()));
    }
    
    @Test
    public void deleteIngredient_DeleteIngredientNotInStock_deleteNormally() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("banana", Optional.of(3), Optional.of(0.50)));
        stock.deleteIngredient(new Ingredient("banana", Optional.empty(), Optional.empty()));
        assertFalse(stock.getStock().containsKey("banana"));
        
        try {
            stock.deleteIngredient(new Ingredient("milo", Optional.of(1), Optional.of(0.3)));
        } catch (IngredientNotFoundException infe) {
            assertEquals("This ingredient is not in the stock currently!", infe.getMessage());
        }
    }
    
    
    @Test
    public void list_ListIngredient_ListInSameOrder() {
        Stock stock = new Stock();
        Stock stockCopy = new Stock();
        
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        stockCopy.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stockCopy.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stockCopy.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        assertTrue(areEqualMap(stock.getStock(), stockCopy.getStock()));
    }
    
    @Test 
    public void print_printStockWhenIngredientQuantityIsZero_SerialIndexUnchanged() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        stock.deleteIngredient(new Ingredient("tomato", Optional.of(3), Optional.empty()));
        
        String stockCopyOutput = "1. [10][$1.00] chicken\n"
                + "2. [0][$0.50] tomato\n"
                + "3. [1][$0.50] rice\n";
 
        assertEquals(printStockOutput(stock), stockCopyOutput);
    }
    
    @Test 
    public void print_printStockWhenIngredientRemoved_SerialIndexReorder() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        stock.deleteIngredient(new Ingredient("tomato", Optional.of(3), Optional.empty()));

        String stockCopyOutput = "1. [10][$1.00] chicken\n"
                + "2. [0][$0.50] tomato\n"
                + "3. [1][$0.50] rice\n";
 
        assertEquals(printStockOutput(stock), stockCopyOutput);
        
        stock.deleteIngredient(new Ingredient("tomato", Optional.empty(), Optional.empty()));
        String newStockCopyOutput = "1. [10][$1.00] chicken\n"
                + "2. [1][$0.50] rice\n";
        
        assertEquals(printStockOutput(stock), newStockCopyOutput);
    }
    
    /**
     * Utility functions ====================================================================================
     */
    
    private static Map<String, Pair<Integer, Double>> createStockCopy() {
        Map<String, Pair<Integer, Double>> stockCopy = new HashMap<>();
        stockCopy.put("tomato", Pair.of(1, 0.40));
        stockCopy.put("tomato", Pair.of(2, 0.50));
        stockCopy.put("rice", Pair.of(1, 0.50));
        stockCopy.put("chicken", Pair.of(10, 1.00));
        return stockCopy;
    }
    
    /**
     * Returns a string representation of the list of ingredients in the stock.
     */
    private String printStockOutput(Stock stock) {
        String output = "";
        int ingredientCounter = 1;
        
        for (Map.Entry<String, Pair<Integer, Double>> ingredient : stock.getStock().entrySet()) {
            String ingredientName = ingredient.getKey();
            int quantity = ingredient.getValue().first();
            double price = ingredient.getValue().second();
            output += (ingredientCounter 
                    + ". "
                    + "[" 
                    + quantity 
                    + "]"
                    + "[$" 
                    + String.format("%.2f", price) 
                    + "]"
                    + " " 
                    + ingredientName
                    + "\n");
           
            ingredientCounter++;
        }
        
        return output;
    }
    
    /**
     * Returns true if both maps are the same through deep scanning of its internal hashMap values.
     */
    private boolean areEqualMap(Map<String, Pair<Integer, Double>> firstMap, 
            Map<String, Pair<Integer, Double>> secondMap) {
        
        if (firstMap.size() != secondMap.size()) {
            return false;
        }
     
        return firstMap.entrySet().stream()
          .allMatch(e -> e.getValue().equals(secondMap.get(e.getKey())));
    }      
    
}
