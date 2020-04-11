package commands;

import exceptions.InvalidStockCommandException;
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

    private final String ls = System.lineSeparator();
    
    private final int defaultNumOfIngredientArgs = 3;
    
    @Test
    public void construct_AddStockCommand_constructNormally() 
            throws InvalidStockCommandException {
        
        Ingredient tomato = new Ingredient("tomato", Optional.of(1), Optional.of(0.50));
        Ingredient tomatoCopy = new AddStockCommand("i/tomato; q/1; p/0.50;")
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
        AddStockCommand addStockCommand = new AddStockCommand("i/tomato; q/10; p/0.50");
        assertEquals(addStockCommand.printExecuteOutput(stock), executeAdd(stockCopy, tomatoToAdd));
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientNameTagOnly() {
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("tomato; q/1; p/0.50;");            
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input did not specify the 'i/' tag before the ingredient's name. "
                    + "Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ", 
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingQuantityTagOnly() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("i/tomato; 1; p/0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input did not specify the 'q/' tag before the ingredient's quantity. "
                    + "Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ", 
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingPriceTagOnly() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("i/tomato; q/1; 0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input did not specify the 'p/' tag before the ingredient's price."
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ", 
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingPriceAndQuantityTag() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("i/tomato; 1; 0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input did not specify the "
                    + "'q/' tag and 'p/' tag"
                    + " before the ingredient's quantity and price."
                    + ls
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ", 
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientNameAndQuantityTag() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("tomato; 1; p/0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input did not specify the "
                    + "'i/' tag and 'q/' tag"
                    + " before the ingredient's name and quantity."
                    + ls
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ", 
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingIngredientNameAndPriceTag() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs("tomato; q/1; 0.50;");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input did not specify the "
                    + "'i/' tag and 'p/' tag"
                    + " before the ingredient's name and price."
                    + ls
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ", 
                    isce.getMessage());          
        }
    }
    
    @Test
    public void parse_ParseUserInputIntoAddIngredientsArgs_missingAllTags() { 
        try {
            Map<String, Pair<Integer, Double>> parsedIngredientInfo = 
                    parseIntoAddIngredientArgs(" ");
        } catch (InvalidStockCommandException isce) {
            assertEquals("The user's input did not meet the required"
                    + " format."
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ", 
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
        AddStockCommand addStockCommand = new AddStockCommand("i/tomato; q/1; p/0.50");
        Ingredient tomatoInAddCommand = addStockCommand.getIngredientInAddCommand();
        assertEquals(tomato, tomatoInAddCommand);       
    }
    
    /**
     * Utility functions =====================================================================================.
     */
   
    /**
     * A utility function of similar implementation of parseIntoIngredientArgs() in AddStockCommand class.
     */
    private Map<String, Pair<Integer, Double>> parseIntoAddIngredientArgs(
            String fullInputLine) throws InvalidStockCommandException {
       
        String[] wordArgs = fullInputLine.split(";");
        
        Optional<String> parsedIngredientName = Optional.empty();
        Optional<Integer> parsedQuantity = Optional.empty();
        Optional<Double> parsedPrice = Optional.empty();
        
        // First check.
        checkMissingTagInUserInput(fullInputLine);
        
        for (String argument : wordArgs) {
            String trimmedArg = argument.trim();
            if (trimmedArg.contains("i/")) {
                parsedIngredientName = Optional.of(parseIngredientName(trimmedArg));
            } else if (trimmedArg.contains("q/")) {
                parsedQuantity = Optional.of(parseIngredientQuantity(trimmedArg));
            } else if (trimmedArg.contains("p/")) {
                parsedPrice = Optional.of(parseIngredientPrice(trimmedArg));
            } else {
                throw new InvalidStockCommandException("The user's input did not meet the required"
                        + " format."
                        + " Please enter in the following format: "
                        + ls
                        + ls
                        + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ");
            }
        }     
        
        // Second check.
        checkValidParsedIngredientArguments(parsedIngredientName, parsedQuantity, parsedPrice);
        
        Map<String, Pair<Integer, Double>> ingredientInfo = new HashMap<>();
        ingredientInfo.put(parsedIngredientName.get(), Pair.of(parsedQuantity.get(), parsedPrice.get()));    
        return ingredientInfo;
    }  
    
    /** 
     * A utility function of similar implementation of parseIntoIngredientName() in AddStockCommand class.
     */
    private String parseIngredientName(String ingredientNameInput) {
        String ingredientName = ingredientNameInput.trim()
                .substring(2, ingredientNameInput.length());
        
        return ingredientName;
    }
    
    /** 
     * A utility function of similar implementation of parseIntoIngredientQuantity() in AddStockCommand class.
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
     * A utility function of similar implementation of parseIntoIngredientPrice() in AddStockCommand class.
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
     * in AddStockCommand class.
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
     * A utility function of similar implementation to checkMissingTagInUserInput() in
     * AddStockCommand class. 
     */
    private void checkMissingTagInUserInput(String fullInputLine) throws InvalidStockCommandException {
        String[] wordArgs = fullInputLine.split(";");
        
        if (wordArgs.length < defaultNumOfIngredientArgs) {
            return;
        }
        
        String ingredientName = "";
        String quantity = "";
        String price = "";
        
        boolean hasIngredientNameTag = false;
        boolean hasQuantityTag = false;
        boolean hasPriceTag = false;
        
        for (String argument : wordArgs) {
            String trimmedArg = argument.trim();
            if (trimmedArg.contains("i/")) {
                hasIngredientNameTag = true;
            } else if (trimmedArg.contains("q/")) {
                hasQuantityTag = true;
            } else if (trimmedArg.contains("p/")) {
                hasPriceTag = true;
            } 
        }
            
        if (!hasIngredientNameTag && hasQuantityTag && hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the 'i/' tag"
                    + " before the ingredient's name."
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ");
        } else if (hasIngredientNameTag && !hasQuantityTag && hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the 'q/' tag"
                    + " before the ingredient's quantity."
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ");
        } else if (hasIngredientNameTag && hasQuantityTag && !hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the 'p/' tag"
                    + " before the ingredient's price."
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ");
        } else if (!hasIngredientNameTag && !hasQuantityTag && hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the "
                    + "'i/' tag and 'q/' tag"
                    + " before the ingredient's name and quantity."
                    + ls
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ");
        } else if (!hasIngredientNameTag && hasQuantityTag && !hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the "
                    + "'i/' tag and 'p/' tag"
                    + " before the ingredient's name and price."
                    + ls
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ");
        } else if (hasIngredientNameTag && !hasQuantityTag && !hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the "
                    + "'q/' tag and 'p/' tag"
                    + " before the ingredient's quantity and price."
                    + ls
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ");
        } else if (!hasIngredientNameTag && !hasQuantityTag && !hasPriceTag)  {
            throw new InvalidStockCommandException("The user's input did not specify the "
                    + "'i/', 'q/' tag and 'p/' tag"
                    + " before the ingredient's name, quantity and price."
                    + ls
                    + " Please enter in the following format: "
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;` ");
        } else {
            assert (hasIngredientNameTag && hasQuantityTag && hasPriceTag);
            return;
        }
    }
    
    /**
     * A utility function similar to execute() in AddStockCommand class.
     * This method returns a string instead of void.
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
