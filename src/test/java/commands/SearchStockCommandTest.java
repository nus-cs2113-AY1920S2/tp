package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import exceptions.InvalidStockCommandException;
import org.junit.jupiter.api.Test;

import ingredient.Ingredient;
import exceptions.IngredientNotFoundException;
import stock.Stock;
import utils.Pair;

class SearchStockCommandTest {
    
    private final String ls = System.lineSeparator();
    
    /** The relative index of 'k' present within the string. */
    private final int indexOfKCharacter = 0;
    
    /** The relative index of '/' present within the string. */
    private final int indexOfSlashCharacter = 1;
    
    /** The relative index after the '/' present within the string. */
    private final int indexAfterSlash = 2;
    
    /** The length of 'k/' tag. */
    private final int keywordTagLength = 2;
    
    @Test
    public void construct_SearchStockCommand_constructNormally() 
            throws InvalidStockCommandException {
    
        String keywordSupplied = "tomato";
        String keywordInCommand = new SearchStockCommand(" k/tomato;").getKeyword();
        assertTrue(keywordSupplied.equals(keywordInCommand));
    }
    
    @Test
    public void parse_parseIntoSearchKeyword_parseNormally() 
            throws InvalidStockCommandException {
        
        String keywordSupplied = "vegetable";
        String parsedKeyword = parseIntoSearchKeyword(" k/vegetable;");
        assertTrue(keywordSupplied.equals(parsedKeyword));
    } 
    
    @Test
    public void check_checkValidSearchKeywordArgumentsSupplied_noExceptionThrown() 
            throws InvalidStockCommandException {
        
        try {
            checkValidSearchKeywordArgumentsSupplied("k/vegetable;");
        } catch (InvalidStockCommandException isce) {
            fail();
        }
    }
    
    @Test
    public void check_checkForBlankArguments_blankKeywordSupplied() 
            throws InvalidStockCommandException {
        
        try {
            new SearchStockCommand(" ").getKeyword();
        } catch (InvalidStockCommandException isce) {
            assertEquals(isce.getMessage(), 
                    "Please enter an ingredient's name to be searched against the stock.");
        }
    }  
    
    @Test
    public void check_checkForBlankArguments_blankKeywordAfterSlashCharacter() 
            throws InvalidStockCommandException {
        
        try {
            new SearchStockCommand(" k/   ").getKeyword();
        } catch (InvalidStockCommandException isce) {
            assertEquals(isce.getMessage(), 
                    "Please enter an ingredient's name to be searched against the stock.");
        }
    }
    
    @Test
    public void check_checkForKAndSlashCharacter_kAndSlashCharacterNotAtTheStart() 
            throws InvalidStockCommandException {
        
        try {
            new SearchStockCommand(" toma/kto").getKeyword();
        } catch (InvalidStockCommandException isce) {
            assertEquals(isce.getMessage(), 
                    "Please specify the keyword using the format 'k/keyword;'");
        }
    }
    
    @Test
    public void check_checkForKAndSlashCharacter_kAndSlashCharacterAtTheEnd() 
            throws InvalidStockCommandException {
        
        try {
            new SearchStockCommand("   tomatok/").getKeyword();
        } catch (InvalidStockCommandException isce) {
            assertEquals(isce.getMessage(), 
                    "Please specify the keyword using the format 'k/keyword;'");
        }
    }
    
    @Test
    public void check_checkForKAndSlashCharacter_kAndSlashCharacterUnspecificed() 
            throws InvalidStockCommandException {
        
        try {
            new SearchStockCommand("   tomato").getKeyword();
        } catch (InvalidStockCommandException isce) {
            assertEquals(isce.getMessage(), 
                    "Please specify the keyword using the format 'k/keyword;'");
        }
    }
    
    @Test
    public void check_checkCorrectKeywordTagUsed_usePriceTagInstead()
            throws InvalidStockCommandException {
        
        try {
            new SearchStockCommand(" p/rice").getKeyword();
        } catch (InvalidStockCommandException isce) {
            assertEquals(isce.getMessage(),
                    "Please specify the keyword using the format 'k/keyword;'");
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
     * A utility function of similar implementation to searchStock() in the Stock class.
     * This method returns a string instead of void.
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
     * A utility function of similar implementation to printSearchResult() in the Stock class.
     * This method returns a string instead of void.
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

        outputMessage += (ls
                + ls);
        
        return outputMessage;
    }
    
    /**
     * A utility function of similar implementation to checkIngredientInStock()
     * in the Stock class. This method returns a string instead of void.
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
     * A utility function of similar implementation of parseIntoSearchKeyword()
     * in SearchStockCommand class. This method returns a string instead of void.                                      
     */
    private String parseIntoSearchKeyword(String fullInputLine) 
            throws InvalidStockCommandException {
        
        checkValidSearchKeywordArgumentsSupplied(fullInputLine);
        
        String trimmedKeyword = fullInputLine.trim();
        
        if (trimmedKeyword.contains(";")) {
            String trimmedKeywordWithColon = trimmedKeyword
                    .substring(indexAfterSlash, trimmedKeyword.length() - 1);
            return trimmedKeywordWithColon; 
        } else {
            String trimmedKeywordWithoutColon = trimmedKeyword
                .substring(indexAfterSlash, trimmedKeyword.length());
            return trimmedKeywordWithoutColon; 
        }            
    }
    
    /**
     * A utility function of similar implementation of checkValidSearchKeywordArgumentsSupplied()
     * in SearchStockCommand class.                                 
     */
    private void checkValidSearchKeywordArgumentsSupplied(String fullInputLine) 
            throws InvalidStockCommandException {
        
        String trimmedFullInputLine = fullInputLine.trim();
        checkForBlankArguments(trimmedFullInputLine);
        checkForKAndSlashBeforeKeyword(trimmedFullInputLine);
    }
    
    /**
     * A utility function of similar implementation of checkForBlankArguments() in SearchStockCommand
     * class. 
     */
    private void checkForBlankArguments(String trimmedFullInputLine) 
            throws InvalidStockCommandException {
        
        if (trimmedFullInputLine.isBlank()) {
            throw new InvalidStockCommandException("Please "
                    + "enter an ingredient's name to be "
                    + "searched against the stock.");
        } else if (trimmedFullInputLine.length() == keywordTagLength) {
            checkForKAndSlashBeforeKeyword(trimmedFullInputLine);
            throw new InvalidStockCommandException("Please "
                    + "enter an ingredient's name to be "
                    + "searched against the stock.");
        } else if (trimmedFullInputLine.length() > keywordTagLength) {
            if (trimmedFullInputLine.substring(indexAfterSlash, 
                    trimmedFullInputLine.length()).isBlank()) {
                throw new InvalidStockCommandException("Please "
                        + "enter an ingredient's name to be "
                        + "searched against the stock.");
            }
        }            
    }
    
    /**
     * A utility function of similar implementation of checkForKAndSlashBeforeKeyword() in 
     * SearchStockCommand class.
     */
    private void checkForKAndSlashBeforeKeyword(String trimmedFullInputLine) 
            throws InvalidStockCommandException {
        if (trimmedFullInputLine.length() < keywordTagLength) {
            throw new InvalidStockCommandException("Please "
                    + "specify the keyword using the format "
                    + "'k/keyword;'");
        } else if (trimmedFullInputLine.charAt(indexOfKCharacter) != 'k' 
                && trimmedFullInputLine.charAt(indexOfSlashCharacter) != '/') {
            throw new InvalidStockCommandException("Please "
                    + "specify the keyword using the format "
                    + "'k/keyword;'");
        }
    }
    
    
    private String createSearchStockOutputCopy() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock that matches the keyword:"
                + ls
                + ls);
        
        outputMessage += ("1. [1][$0.50] potato"
                + ls); 
                
        outputMessage += ls;
        
        return outputMessage;
    }
    
    private String createSearchStockOutputCopyTwo() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock that matches the keyword:"
                + ls
                + ls);
        
        outputMessage += ("1. [1][$0.50] potato"
                + ls
                + "2. [1][$0.50] tomato"
                + ls); 
                
        outputMessage += ls;
        
        return outputMessage;
    }
    
    private String createSearchStockOutputCopyThree() {
        String outputMessage = "";
        outputMessage += ("Here are the ingredients in the stock that matches the keyword:"
                + ls
                + ls);
        
        outputMessage += ("1. [1][$0.30] salt"
                + ls
                + "2. [1][$0.50] potato"
                + ls
                + "3. [1][$0.50] tomato"
                + ls
                + "4. [10][$0.50] vegetable"
                + ls); 
                
        outputMessage += ls;
        
        return outputMessage;
    }
}
