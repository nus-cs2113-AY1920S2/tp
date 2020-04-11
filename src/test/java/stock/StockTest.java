package stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import ingredient.Ingredient;
import exceptions.IngredientNotFoundException;
import utils.Pair;

class StockTest {
    
    private final String ls = System.lineSeparator();
    
    /**
     * Test that the ingredient saves the new price supplied whenever the user inputs it.
     */
    @Test
    public void addIngredient_AddIngredientWithQuantitySupplied_addNormally() {
        
        Stock stockA = new Stock(); 
        stockA.addIngredient(new Ingredient("tomato", Optional.of(1), Optional.of(0.40)));
        stockA.addIngredient(new Ingredient("tomato", Optional.of(2), Optional.of(0.50)));     
        
        assertFalse(stockA.getStock().get("tomato").second().equals(0.40));
    }
    
    /**
     * Test that the ingredient's quantity is added up.
     */
    @Test
    public void addIngredient_AddIngredientWithQuantitySupplied_sumQuantity() {
        
        Stock stockB = new Stock();
        stockB.addIngredient(new Ingredient("tomato", Optional.of(1), Optional.of(0.40)));
        stockB.addIngredient(new Ingredient("tomato", Optional.of(2), Optional.of(0.50))); 
        
        assertTrue(stockB.getStock().get("tomato").first().equals(3));
        assertFalse(stockB.getStock().get("tomato").first().equals(1));
    }
    
    /**
     * Test against the stock by adding an ingredient of similar name that exists w
     * within the stock.
     */
    @Test
    public void addIngredient_AddIngredientOfSimilarName_DuplicateIngredientName() {
        
        Stock stockC = new Stock();
        stockC.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stockC.addIngredient(new Ingredient("RiCe", Optional.of(1), Optional.of(0.50)));
        stockC.addIngredient(new Ingredient("RICE", Optional.of(1), Optional.of(0.50)));
        stockC.addIngredient(new Ingredient("tomato", Optional.of(2), Optional.of(0.50))); 
            
        assertEquals(createAddStockOutputCopyOne(), printDuplicateIngredientMessage("RiCe", stockC));
    }
    
    /**
     * Test against the stockCopy. 
     */
    @Test
    public void testAdd_CompareAgainstStockCopy_TrueIfSameContent() {
        
        Stock stockD = new Stock();
        final Map<String, Pair<Integer, Double>> stockCopy = createStockCopy();
        stockD.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stockD.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        stockD.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        
        assertEquals(stockD.getStock().keySet(), (stockCopy.keySet()));
    }
    
    /**
     * Test for the case where user provides a quantity to be deleted.
     */
    @Test
    public void deleteIngredient_DeleteIngredientWithQuantity_deleteNormally() 
            throws IngredientNotFoundException {
        
        Stock stockE = new Stock();
        stockE.addIngredient(new Ingredient("tomato", Optional.of(1), Optional.of(0.50)));
        stockE.deleteIngredient(new Ingredient("tomato", Optional.of(1), Optional.empty()));        
        
        assertEquals(0, stockE.getStock().get("tomato").first());
    }
    
    /**
     * Test for the case where user did not provide a quantity to be deleted.
     */
    @Test
    public void deleteIngredient_DeleteIngredientWithoutQuantity_deleteNormally() 
            throws IngredientNotFoundException {
        
        Stock stockF = new Stock();
        stockF.addIngredient(new Ingredient("banana", Optional.of(3), Optional.of(0.50)));
        stockF.deleteIngredient(new Ingredient("banana", Optional.empty(), Optional.empty()));
        
        assertFalse(stockF.getStock().containsKey("banana"));
    }
    
    @Test
    public void testDelete_CompareAgainstStockCopy_TrueIfSameContent() 
            throws IngredientNotFoundException {
        
        Stock stockG = new Stock();
        stockG.addIngredient(new Ingredient("banana", Optional.of(3), Optional.of(0.50)));
        stockG.deleteIngredient(new Ingredient("banana", Optional.empty(), Optional.empty()));
       
        assertFalse(stockG.getStock().containsKey("banana"));
        
        final Map<String, Pair<Integer, Double>> stockCopy = createStockCopy();
        stockG.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stockG.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stockG.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        assertEquals(stockG.getStock().keySet(), (stockCopy.keySet()));
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
    public void test_equalMap_bothMapsAreEqual() {
        Stock stock = new Stock();
        final Stock stockCopyH = new Stock();
        
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        stockCopyH.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stockCopyH.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stockCopyH.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        assertTrue(areEqualMap(stock.getStock(), stockCopyH.getStock()));
    }
    
    @Test
    public void listStock_listIngredientInDescendingQuantity_listNormally() {
        
        Stock stock = new Stock();
        
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        assertEquals(createListStockOutputCopyOne(), printlistIngredientToString(stock));
    }
    
    @
    Test
    public void listStock_ListIngredientInDescendingQuantity_noIngredientFound() {
        Stock stock = new Stock();
        
        assertEquals(createListStockOutputCopyTwo(), printlistIngredientToString(stock));
    }
    
    @Test
    public void searchStock_searchIngredientInStock_ingredientIsFoundOutput() {
        
        Stock stock = new Stock();
        
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        assertEquals(createSearchStockOutputCopyOne(), executeSearch(stock, "tomato"));
    }
    
    @Test
    public void searchStock_searchIngredientInStock_ingredientNotFoundOutput() {
        
        Stock stock = new Stock();
        
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        assertEquals(createSearchStockOutputCopyTwo(), executeSearch(stock, "apple"));
    }
    
    @Test
    public void searchStock_searchIngredientInStock_testCaseSensitivity() {
        
        Stock stock = new Stock();
        
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        assertEquals(createSearchStockOutputCopyOne(), executeSearch(stock, "TOMATO"));
    }
    
    @Test
    public void clearStock_clearAllIngredientsInStock_emptyStock() {
        Stock stock = new Stock();
        
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        
        stock.clearStock();
        
        assertTrue(stock.getStock().isEmpty());
    }
    
    @Test 
    public void print_printStockWhenIngredientQuantityIsZero_SerialIndexUnchanged() 
            throws IngredientNotFoundException {
        
        Stock stock = new Stock();
        stock.addIngredient(new Ingredient("tomato", Optional.of(3), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("rice", Optional.of(1), Optional.of(0.50)));
        stock.addIngredient(new Ingredient("chicken", Optional.of(10), Optional.of(1.00)));
        stock.deleteIngredient(new Ingredient("tomato", Optional.of(3), Optional.empty()));
        
        String stockCopyOutput = "1. [10][$1.00] chicken"
                + ls
                + "2. [1][$0.50] rice"
                + ls
                + "3. [0][$0.50] tomato"
                + ls;
 
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

        String stockCopyOutput = "1. [10][$1.00] chicken"
                + ls
                + "2. [1][$0.50] rice"
                + ls
                + "3. [0][$0.50] tomato"
                + ls;
 
        assertEquals(printStockOutput(stock), stockCopyOutput);
        
        stock.deleteIngredient(new Ingredient("tomato", Optional.empty(), Optional.empty()));
        String newStockCopyOutput = "1. [10][$1.00] chicken"
                + ls
                + "2. [1][$0.50] rice"
                + ls;
        
        assertEquals(printStockOutput(stock), newStockCopyOutput);
    }
    
    /**
     * Utility functions ====================================================================================.
     */
    
    private String createAddStockOutputCopyOne() {
        String outputMessage = "";
                
        outputMessage += ls
                + "Please note that there are other similar ingredient names in the stock."
                + ls
                + ls;

        outputMessage += "You are currently adding: 'RiCe'"
                + ls
                + ls;

        outputMessage += "Here are the ingredients in the stock with similar names:"
                + ls
                + "rice"
                + ls
                + "RiCe"
                + ls
                + "RICE"
                + ls
                + ls;

        outputMessage += "You may want to remove the unwanted ingredient names if it is a duplicate."
                + ls;
        
        return outputMessage;
    }
    
    private String createListStockOutputCopyOne() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock currently:"
                + ls
                + ls);
        
        outputMessage += ("1. [10][$1.00] chicken"
                + ls
                + "2. [3][$0.50] tomato"
                + ls 
                + "3. [1][$0.50] rice"
                + ls); 
                
        outputMessage += ls;
        
        return outputMessage;
    }
    
    private String createListStockOutputCopyTwo() {
        String outputMessage = "";
        outputMessage += ("There is nothing in the stock currently.");
        
        return outputMessage;
    }
    
    private String createSearchStockOutputCopyOne() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock that matches the keyword:"
                + ls
                + ls);
        
        outputMessage += ("1. [3][$0.50] tomato"
                + ls); 
        
        return outputMessage;
    }
    
    private String createSearchStockOutputCopyTwo() {
        String outputMessage = "";
        outputMessage += ("There is no ingredient that matches the keyword given.");
        
        return outputMessage;
    }
    
    /**
     * Returns a string representation of output when a search function is executed.
     */
    private String executeSearch(Stock stock, String keyword) {  
        String outputMessage = "";
        boolean hasIngredientWithKeyword = checkIngredientInStock(keyword, stock);
        
        if (!hasIngredientWithKeyword) {
            outputMessage += ("There is no ingredient that matches the keyword given.");
        } else {
            outputMessage += printSearchResult(stock, keyword);
        }
        
        return outputMessage;
    }
    
    /**
     * Returns a string representation of the search results of ingredients within the stock that matches 
     * the keyword given.
     */
    private String printSearchResult(Stock stock, String keyword) {
        String outputMessage = ("Here are the ingredients in the stock that matches the keyword:"
                + ls);
    
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(
                stock.getStock().entrySet());
        
        int ingredientCounter = 1;

        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
            String ingredientName = ingredient.getKey();
            
            if (ingredientName.contains(keyword) || ingredientName
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {
                
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
        }

        outputMessage += ls;
        
        return outputMessage;
    }
    
    /**
     * A utility function to check against stock if any of the ingredient within the stock
     * matches the keyword supplied by the user.
     */
    private boolean checkIngredientInStock(String keyword, Stock stock) {
        boolean hasIngredientWithKeyword = false;
        
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(
                stock.getStock().entrySet());
        
        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
            String ingredientName = ingredient.getKey();
            
            if (ingredientName.contains(keyword) || ingredientName
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {
                
                hasIngredientWithKeyword = true;
            }
        }
        
        return hasIngredientWithKeyword;
    }
    
    /**
     * A utility function to similar implementation to printDuplicateIngredientMessage() in
     * Stock class.
     */
    private boolean checkForDuplicateIngredientName(String ingredientNameToCheck, Stock stock) {
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.getStock().entrySet()); 
        boolean hasDuplicateIngredientName = false;
        
        if (tempList.size() < 1) {
            return hasDuplicateIngredientName;
        } else {
            for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
                String ingredientName = ingredient.getKey();
                if (ingredientName.toLowerCase().equals(ingredientNameToCheck.toLowerCase())) {
                    hasDuplicateIngredientName = true;
                    break;
                }
            }
            return hasDuplicateIngredientName;
        }
    }
    
    /** 
     * A utility function of similar implementation to printDuplicateIngredientMessage() in Stock
     * class.
     */
    private String printDuplicateIngredientMessage(String duplicateIngredientName, Stock stock) {
        String outputMessage = ls;
        
        outputMessage += ("Please note that there are other similar ingredient names in the stock."
                + ls
                + ls);
        
        outputMessage += ("You are currently adding: '"
                + duplicateIngredientName
                + "'");
        
        outputMessage += (ls
                + ls
                + "Here are the ingredients in the stock with similar names:"
                + ls);
        
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.getStock().entrySet());
        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
            String ingredientName = ingredient.getKey();
            if (ingredientName.toLowerCase().equals(duplicateIngredientName.toLowerCase())) {
                outputMessage += (ingredientName
                        + ls);
            }
        }
        
        outputMessage += (ls
                + "You may want to remove the unwanted ingredient names if it is a duplicate.");
        
        outputMessage += (ls);
        
        return outputMessage;
    }
    
    private Map<String, Pair<Integer, Double>> createStockCopy() {
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
        
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.getStock().entrySet());
        
        Collections.sort(tempList, new Comparator<Entry<String, Pair<Integer, Double>>>() {
            @Override
            public int compare(Entry<String, Pair<Integer, Double>> firstEntry, 
                    Entry<String, Pair<Integer, Double>> secondEntry) {
                
                int firstEntryQuantity = firstEntry.getValue().first();
                int secondEntryQuantity = secondEntry.getValue().first();
                return secondEntryQuantity - firstEntryQuantity;
            }
        }); 
        
        for (Map.Entry<String, Pair<Integer, Double>> ingredient : tempList) {
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
                    + ls);
           
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
    
    /**
     * A String representation of printing the stock as to when user input 'list stock'.
     */
    private String printlistIngredientToString(Stock stock) {
        String outputMessage = "";
        
        if (stock.getStock().isEmpty()) {
            outputMessage += ("There is nothing in the stock currently.");
        } else {
            outputMessage += ("Here are the ingredients in the stock currently:"
                    + ls
                    + ls);
            
            outputMessage += printStockOutput(stock);
            
            outputMessage += ls;
        }
        
        return outputMessage;
    }
}