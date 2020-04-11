package commands;

import exceptions.IngredientNotFoundException;
import exceptions.InvalidStockCommandException;
import ingredient.Ingredient;
import stock.Stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class focuses on the 'delete' functionality of the application.
 * 
 */
public class DeleteStockCommand extends StockCommand {
    
    /** The ingredient to be deleted within the stock. */
    private final Ingredient ingredientToDelete;
    
    private final String ls = System.lineSeparator();
    
    /**
     * A convenience constructor that contains information of an ingredient 
     * stored in a hashMap.
     * 
     */
    public DeleteStockCommand(Map<String, Optional<Integer>> ingredientInfo) {
        
        String ingredientName = ingredientInfo
                .entrySet()
                .stream()
                .map(item -> item.getKey())
                .map(Object::toString)
                .collect(Collectors.joining(""));
        
        Optional<Integer> quantity = ingredientInfo.get(ingredientName);
        
        this.ingredientToDelete = new Ingredient(ingredientName, quantity, Optional.empty());               
    }
    
    /**
     * A convenience constructor that contains information of an ingredient 
     * stored in a hashMap.
     * @throws InvalidStockCommandException If the user's input does not meet 
     *                                      the required format by specifying 
     *                                      i/INGREDIENT; q/QUANTITY;                                    
     * 
     */
    public DeleteStockCommand(String ingredientInput) 
            throws InvalidStockCommandException {
        
        Map<String, Optional<Integer>> ingredientInfo = 
                parseIntoDeleteIngredientArgs(ingredientInput);
        
        String ingredientName = ingredientInfo
                .entrySet()
                .stream()
                .map(item -> item.getKey())
                .map(Object::toString)
                .collect(Collectors.joining(""));
        
        Optional<Integer> quantity = ingredientInfo.get(ingredientName);
        
        this.ingredientToDelete = new Ingredient(ingredientName, quantity, Optional.empty());               
    }
    
    /**
     * Parses the user's input into readable arguments that will be used 
     * to construct an Ingredient object. The arguments are then stored 
     * in a HashMap. For example: ' i/tomato; q/10;' will store 'tomato' 
     * as the ingredient name, '10' as the ingredient quantity to be 
     * deleted. Note that the specification of the ingredient's 
     * quantity is optional.
     * @throws InvalidStockCommandException If the user's input does not 
     *                                      meet the required format by 
     *                                      specifying i/INGREDIENT; 
     *                                      q/QUANTITY;
     * 
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
                throw new InvalidStockCommandException("There is no tag specified."
                        + " Please enter in the following format:"
                        + ls
                        + ls
                        + "`delete stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_DELETED;`"
                        + ls
                        + "               or"
                        + ls
                        + "`delete stock; i/INGREDIENT_NAME;`.");
            }
        }
        
        checkValidParsedIngredientArguments(ingredientName, quantity);
        ingredientInfo.put(ingredientName.get(), quantity);
        return ingredientInfo;
    }
    
    /**
     * Checks the parsed ingredients' arguments if it meets the correct format.
     * @throws InvalidStockCommandException If the user's input does not meet the required
     *                                      format by specifying i/INGREDIENT; q/QUANTITY;
     *                                      
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
            assert (ingredientName.isPresent());
            return;
        }
    }
    
    @Override
    public void execute(Stock stock) {    
        try {
            stock.deleteIngredient(ingredientToDelete);
            System.out.println(createDeleteResultMessage());
        } catch (IngredientNotFoundException infe) {
            System.out.println("Ingredient " 
                    + this.ingredientToDelete.getIngredientName() 
                    + " not found and cannot be deleted!");
        }       
    }
    
    /** Creates a message to notify the user of the deletion. */
    private String createDeleteResultMessage() {
        boolean hasQuantitySpecified = ingredientToDelete.isQuantitySpecified();
        
        String outputMessage = "Ingredient " 
                    + ingredientToDelete.getIngredientName()
                    + (hasQuantitySpecified 
                            ? " reduced by " 
                            + ingredientToDelete.getIngredientQuantity() + "!"
                            : " deleted from the stock!");
        
        return outputMessage;
    }
    
    /** A utility function to facilitate testing of execute(). */
    public String printExecuteOutput(Stock stock) {
        String outputMessage = "";
        
        try {
            stock.deleteIngredient(ingredientToDelete);
            outputMessage += (createDeleteResultMessage());
        } catch (IngredientNotFoundException infe) {
            outputMessage += ("Ingredient " 
                    + ingredientToDelete.getIngredientName() 
                    + " not found and cannot be deleted!");
        }

        return outputMessage;
    }
    
    public Ingredient getIngredientInDeleteCommand() {
        return this.ingredientToDelete;
    }
}
