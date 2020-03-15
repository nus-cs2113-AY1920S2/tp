package commands;

import ingredient.Ingredient;
import org.junit.jupiter.api.Test;
import stock.Stock;
import utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddStockCommandTest {

    @Test
    public void construct_AddStockCommand_constructNormally() 
            throws InvalidStockCommandException {
        
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        Ingredient tomatoCopy = new AddStockCommand("i/tomato; q/1; p/$0.50;")
                .getIngredientInAddCommand();
        
        assertTrue(tomato.equals(tomatoCopy));
    }
    
    @Test
    public void execute_ExecuteAddStockCommand_executeNormally() 
            throws InvalidStockCommandException {
        
        Stock stock = new Stock();
        Stock stockCopy = new Stock();
        Ingredient tomatoToAdd = new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        stockCopy.addIngredient(tomatoToAdd);
        AddStockCommand addStockCommand = new AddStockCommand("i/tomato; q/10; p/$0.50");
        assertEquals(addStockCommand.printExecuteOutput(stock), executeAdd(stockCopy, tomatoToAdd));
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientTag() {
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("tomato; q/1; p/$0.50;");            
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input given cannot be parsed into ingredient arguments.",
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingQuantityTag() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("i/tomato; 1; p/$0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input given cannot be parsed into ingredient arguments.",
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingPriceTag() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("i/tomato; q/1; $0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input given cannot be parsed into ingredient arguments.",
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingAllTags() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs(" ");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input given cannot be parsed into ingredient arguments.",
                    isce.getMessage());           
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientQuantityAndPrice() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("i/tomato;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input is missing both the ingredient's "
                    + "quantity to be added and the ingredient's price!",
                    isce.getMessage());           
        } 
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientNameAndPrice() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("q/1;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input must specify both the ingredient's "
                    + "name and the ingredient's price!",
                    isce.getMessage());           
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientNameAndQuantity() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("p/$0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input must specify both the ingredient's name "
                    + "and the quantity to be added!",
                    isce.getMessage());           
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientPriceOnly() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("i/tomato; q/1;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input must specify the ingredient's price!",
                    isce.getMessage());           
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientQuantityOnly() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("i/tomato; p/$0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input must specify the quantity of the ingredient to be added!",
                    isce.getMessage());           
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientNameOnly() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("q/1; p/$0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input must specify the ingredient's name!",
                    isce.getMessage());           
        }
    }
    
    @Test
    public void getIngredient_GetIngredientInAddCommand_getNormally() 
            throws InvalidStockCommandException {
        
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        AddStockCommand addStockCommand = new AddStockCommand("i/tomato; q/1; p/$0.50");
        Ingredient tomatoInAddCommand = addStockCommand.getIngredientInAddCommand();
        assertEquals(tomato, tomatoInAddCommand);       
    }
    
    /**
     * Utility functions =====================================================================================.
     */
   
    /**
     * A utility function of similar implementation of parseIntoIngredientArgs() in AddStockCommand.
     */
    private Map<String, Pair<Integer, Double>> parseIntoAddIngredientArgs(
            String fullInputLine) throws InvalidStockCommandException {
        
        Map<String, Pair<Integer, Double>> ingredientInfo = new HashMap<>();
        String[] wordArgs = fullInputLine.split(";");
        
        Optional<String> ingredientName = Optional.empty();
        Optional<Integer> quantity = Optional.empty();
        Optional<Double> price = Optional.empty();
        
        for (String argument : wordArgs) {
            String trimmedArg = argument.trim();
            if (trimmedArg.contains("i/")) {
                ingredientName = Optional.of(parseIngredientName(trimmedArg));
            } else if (trimmedArg.contains("q/")) {
                quantity = Optional.of(parseIngredientQuantity(trimmedArg));
            } else if (trimmedArg.contains("p/")) {
                price = Optional.of(parseIngredientPrice(trimmedArg));
            } else {
                throw new InvalidStockCommandException("The user's input given cannot"
                        + " be parsed into ingredient arguments.");
            }
        }
        
        checkValidParsedIngredientArguments(ingredientName, quantity, price);
        ingredientInfo.put(ingredientName.get(), Pair.of(quantity.get(), price.get()));    
        return ingredientInfo;
    }   
    
    /** 
     * A utility function of similar implementation of parseIntoIngredientName() in AddStockCommand.
     */
    private String parseIngredientName(String ingredientNameInput) {
        String ingredientName = ingredientNameInput.trim()
                .substring(2, ingredientNameInput.length());
        
        return ingredientName;
    }
    
    /** 
     * A utility function of similar implementation of parseIntoIngredientQuantity() in AddStockCommand.
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
     * A utility function of similar implementation of parseIntoIngredientPrice() in AddStockCommand.
     */
    private double parseIngredientPrice(String ingredientPriceInput) 
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
     * A utility function of similar implementation of checkValidParsedIngredientArguments() 
     * in AddStockCommand.
     */
    private void checkValidParsedIngredientArguments(Optional<String> ingredientName, 
            Optional<Integer> quantity, Optional<Double> price) 
                    throws InvalidStockCommandException {
        
        if (ingredientName.isEmpty() && quantity.isEmpty() && price.isEmpty()) {
            throw new InvalidStockCommandException(
                    "The user's input must specify an ingredient's name, "
                    + "quantity to be added and its price!");
        } else if (ingredientName.isPresent() && quantity.isEmpty() && price.isEmpty()) {
            throw new InvalidStockCommandException(
                    "The user's input is missing both the ingredient's "
                    + "quantity to be added and the ingredient's price!");
        } else if (ingredientName.isEmpty() && quantity.isPresent() && price.isEmpty()) {
            throw new InvalidStockCommandException(
                    "The user's input must specify both the ingredient's "
                    + "name and the ingredient's price!");
        } else if (ingredientName.isEmpty() && quantity.isEmpty() && price.isPresent()) {
            throw new InvalidStockCommandException(
                    "The user's input must specify both the ingredient's name "
                    + "and the quantity to be added!");
        } else if (ingredientName.isPresent() && quantity.isPresent() && price.isEmpty()) {
            throw new InvalidStockCommandException(
                    "The user's input must specify the ingredient's price!");
        } else if (ingredientName.isPresent() && quantity.isEmpty() && price.isPresent()) {
            throw new InvalidStockCommandException(
                    "The user's input must specify the quantity of the ingredient to be added!");
        } else if (ingredientName.isEmpty() && quantity.isPresent() && price.isPresent()) {
            throw new InvalidStockCommandException(
                    "The user's input must specify the ingredient's name!");
        } else {
            return;
        }
    }
    
    /**
     * Returns a string representation of output when AddStockCommand is executed.
     */
    private String executeAdd(Stock stock, Ingredient ingredientToAdd) {
        String outputMessage = "";
        stock.addIngredient(ingredientToAdd);
        
        outputMessage += ("Ingredient " 
                + ingredientToAdd.getIngredientName() 
                + " successfully added!");
        
        return outputMessage;
    }
}
