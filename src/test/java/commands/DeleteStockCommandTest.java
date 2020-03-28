package commands;

import exceptions.InvalidStockCommandException;
import ingredient.Ingredient;
import ingredient.IngredientNotFoundException;
import org.junit.jupiter.api.Test;
import stock.Stock;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteStockCommandTest {
    
    /**
     * Test when the user input is in the correct format of 'i/INGREDIENT; q/QUANTITY;'
     * with quantity specified.
     */
    @Test
    public void construct_DeleteStockCommandWithQuantity_constructNormally() 
            throws InvalidStockCommandException {
        
        Ingredient tomatoToDelete = new Ingredient("tomato", Optional.of(1), Optional.empty());  

        Ingredient tomatoCopy = new DeleteStockCommand("i/tomato; q/1;")
                .getIngredientInDeleteCommand();
        
        assertTrue(tomatoToDelete.equals(tomatoCopy)); 
    }
    
    /**
     * Test when the user input is in the correct format of 'i/INGREDIENT;' without quantity
     * specified.
     */
    @Test
    public void construct_DeleteStockCommandWithoutQuantity_constructNormally() 
            throws InvalidStockCommandException {
        
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.empty());
        Ingredient tomatoCopyTwo = new DeleteStockCommand("i/tomato;")
                .getIngredientInDeleteCommand();
        
        assertTrue(tomato.equals(tomatoCopyTwo));      
    }
    
    /**
     * Test execution of deleteStockCommand when quantity is specified.
     */
    @Test
    public void execute_ExecuteDeleteStockCommandWithQuantity_executeNormally() 
            throws InvalidStockCommandException, IngredientNotFoundException {
        
        Stock stock = new Stock();
        Stock stockCopy = new Stock();
        Ingredient tomatoToAdd = new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        Ingredient tomatoToDelete = new Ingredient("tomato", Optional.of(1), Optional.empty());
        stockCopy.addIngredient(tomatoToAdd);
        stockCopy.deleteIngredient(tomatoToDelete);
        DeleteStockCommand deleteStockCommand = new DeleteStockCommand("i/tomato; q/1");
        
        assertEquals(deleteStockCommand.printExecuteOutput(stockCopy),
                executeDelete(stockCopy, tomatoToDelete));
    }
    
    /**
     * Test execution of deleteStockCommand when quantity is not specified.
     */
    @Test
    public void excute_ExecuteDeleteStockCommandWithoutQuantity_executeNormally() 
            throws InvalidStockCommandException, IngredientNotFoundException {
        
        Stock stock = new Stock();
        Stock stockCopy = new Stock();
        Ingredient tomatoToAdd = new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        Ingredient tomatoToDelete = new Ingredient("tomato", Optional.empty(), Optional.empty());
        stockCopy.addIngredient(tomatoToAdd);
        stockCopy.deleteIngredient(tomatoToDelete);
        DeleteStockCommand deleteStockCommand = new DeleteStockCommand("i/tomato;");
        assertEquals(deleteStockCommand.printExecuteOutput(stock),
                executeDelete(stockCopy, tomatoToDelete));
    }
    
    @Test
    public void excute_ExecuteDeleteStockCommand_ingredientNotFoundInStock() 
            throws InvalidStockCommandException {
        
        Stock stock = new Stock();
        Stock stockCopy = new Stock();
        Ingredient tomatoToDelete = new Ingredient("tomato", Optional.empty(), Optional.empty());
        DeleteStockCommand deleteStockCommand = new DeleteStockCommand("i/tomato;");
        
        try {
            stockCopy.deleteIngredient(tomatoToDelete);
        } catch (IngredientNotFoundException infe) {
            assertEquals(deleteStockCommand.printExecuteOutput(stock),
                    executeDelete(stockCopy, tomatoToDelete));
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoDeleteIngredientsArgsWithQuantity_parseNormally() 
            throws InvalidStockCommandException {
        
        Map<String, Optional<Integer>> parsedIngredientInfo = 
                parseIntoDeleteIngredientArgs("i/tomato; q/1;"); 
        
        String parsedIngredientName = parsedIngredientInfo
                .entrySet()
                .stream()
                .map(item -> item.getKey())
                .map(Object::toString)
                .collect(Collectors.joining(""));
        
        int parsedIngredientQuantity = parsedIngredientInfo.get("tomato").get();
        assertEquals("tomato", parsedIngredientName);
        assertEquals(1, parsedIngredientQuantity);
    }   
    
    @Test
    public void parse_ParseUserInputIntoDeleteIngredientsArgsWithoutQuantity_parseNormally() 
            throws InvalidStockCommandException {
        
        Map<String, Optional<Integer>> parsedIngredientInfo = 
                parseIntoDeleteIngredientArgs("i/tomato;"); 
        
        String parsedIngredientName = parsedIngredientInfo
                .entrySet()
                .stream()
                .map(item -> item.getKey())
                .map(Object::toString)
                .collect(Collectors.joining(""));
                
        assertEquals("tomato", parsedIngredientName);
        assertThrows(NoSuchElementException.class, () -> {
            int parsedIngredientQuantity = parsedIngredientInfo.get("tomato").get();
        });
    }
    
    @Test
    public void parse_ParseUserInputIntoDeleteIngredientsArgs_missingIngredientNameOnly() 
            throws InvalidStockCommandException {

        try {
            Map<String, Optional<Integer>> parsedIngredientInfo = 
                    parseIntoDeleteIngredientArgs("q/$0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("Please ensure that the quantity specified is an integer!",
                    isce.getMessage());           
        }         
    }
    
    @Test
    public void parse_ParseUserInputIntoDeleteIngredientsArgs_missingIngredientNameAndQuantity() {
        try {
            Map<String, Optional<Integer>> parsedIngredientInfo = 
                    parseIntoDeleteIngredientArgs("");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input given cannot be parsed into ingredient arguments.",
                    isce.getMessage());           
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoDeleteIngredientsArgs_missingIngredientTag() {
        try {
            Map<String, Optional<Integer>> parsedIngredientInfo = 
                    parseIntoDeleteIngredientArgs("tomato");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input given cannot be parsed into ingredient arguments.",
                    isce.getMessage());           
        }
    }
      
    @Test
    public void parse_ParseUserInputIntoDeleteIngredientsArgs_missingQuantityTag() {
        try {
            Map<String, Optional<Integer>> parsedIngredientInfo = 
                    parseIntoDeleteIngredientArgs("i/tomato; 10");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input given cannot be parsed into ingredient arguments.",
                    isce.getMessage());           
        }
    }

    @Test
    public void getIngredient_GetIngredientInDeleteCommand_getNormally() 
            throws InvalidStockCommandException {
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.empty());
        DeleteStockCommand deleteStockCommand = new DeleteStockCommand("i/tomato; q/1;");
        Ingredient tomatoInDeleteCommand = deleteStockCommand.getIngredientInDeleteCommand();
        
        assertEquals(tomato, tomatoInDeleteCommand);
    }
    
    /**
     * Utility functions ================================================================================.
     */  
    
    /**
     * A utility function of similar implementation of parseIntoIngredientArgs() in DeleteStockCommand.
     */
    private Map<String, Optional<Integer>> parseIntoDeleteIngredientArgs(
            String fullInputLine) throws InvalidStockCommandException {
        
        Map<String, Optional<Integer>> ingredientInfo = new HashMap<>();
        String[] wordArgs = fullInputLine.split(";");       

        Optional<String> ingredientName = Optional.empty();
        Optional<Integer> quantity = Optional.empty();
        
        for (String argument : wordArgs) {
            String trimmedArg = argument.trim();
            if (trimmedArg.contains("i/")) {
                ingredientName = Optional.of(parseIngredientName(trimmedArg));
            } else if (trimmedArg.contains("q/")) {
                quantity = Optional.of(parseIngredientQuantity(trimmedArg));
            } else {
                throw new InvalidStockCommandException("The user's input given cannot"
                        + " be parsed into ingredient arguments.");
            }
        }
        
        checkValidParsedIngredientArguments(ingredientName, quantity);
        ingredientInfo.put(ingredientName.get(), quantity);
        return ingredientInfo;
    }  
    
    /** 
     * A utility function of similar implementation of parseIngredientName() in DeleteStockCommand.
     */
    private String parseIngredientName(String ingredientNameInput) {
        String ingredientName = ingredientNameInput.trim()
                .substring(2, ingredientNameInput.length());
        
        return ingredientName;
    }
    
    /** 
     * A utility function of similar implementation of parseIngredientQuantity() in DeleteStockCommand.
     */
    private int parseIngredientQuantity(String ingredientQuantityInput) 
            throws InvalidStockCommandException {
        
        String trimmedInput = ingredientQuantityInput.trim();
        
        try {
            int quantity = Integer.parseInt(trimmedInput.substring(
                    2, ingredientQuantityInput.length()));
            
            if (quantity < 0) {
                throw new InvalidStockCommandException("Please enter a"
                        + " positive value for the quantity to be added!");
            } 
            return quantity;            
        } catch (NumberFormatException nfe) {
            throw new InvalidStockCommandException("Please ensure that the "
                    + "quantity specified is an integer!");
        }        
    }
    
    /**
     * A utility function of similar implementation of checkValidParsedArguments() in DeleteStockCommand.
     */
    private void checkValidParsedIngredientArguments(Optional<String> ingredientName,
            Optional<Integer> quantity) throws InvalidStockCommandException {
        
        if (ingredientName.isEmpty() && quantity.isEmpty()) {
            throw new InvalidStockCommandException("The user's input must specify"
                    + " an ingredient name.");
        } else if (ingredientName.isEmpty() && quantity.isPresent()) {
            throw new InvalidStockCommandException("The user's input must specify"
                    + " an ingredient name.");
        } else {
            return;
        }
    }
    
    /**
     * Returns a string representation of output when DeleteStockCommand is executed.
     */
    private String executeDelete(Stock stock, Ingredient ingredientToDelete) {
        String outputMessage = "";
        
        try {
            stock.deleteIngredient(ingredientToDelete);
            
        } catch (IngredientNotFoundException e) {
            outputMessage += ("Ingredient " 
                    + ingredientToDelete.getIngredientName() 
                    + " not found and cannot be deleted!");
        }

        outputMessage += (createDeleteResultMessage(ingredientToDelete));
        
        return outputMessage;
    }
    
    /**
     * A utility function to return a string representation. The implementation is
     * similar to createDeleteResultMessage() in DeleteStockCommand.
     */
    private String createDeleteResultMessage(Ingredient ingredientToDelete) {
        boolean hasQuantitySpecified = ingredientToDelete.isQuantitySpecified();
        
        String outputMessage = "Ingredient " 
                    + ingredientToDelete.getIngredientName()
                    + (hasQuantitySpecified 
                            ? " reduced by " 
                            + ingredientToDelete.getIngredientQuantity() + "!"
                            : " deleted from the stock!");
        
        return outputMessage;
    }
}
