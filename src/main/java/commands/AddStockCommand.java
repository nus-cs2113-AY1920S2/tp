package commands;

import exceptions.InvalidStockCommandException;
import ingredient.Ingredient;
import stock.Stock;
import utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class focuses on the 'add' functionality of the application
 * on the stock category.
 * 
 */
public class AddStockCommand extends StockCommand {
    
    /** The ingredient to be added into the stock. */
    private final Ingredient ingredientToAdd;
    
    private final int defaultNumOfIngredientArgs = 3;
    
    private final String ls = System.lineSeparator();
    
    /** 
     * A convenience constructor that contains information of an ingredient stored in a 
     * hashMap.
     * @throws InvalidStockCommandException If the user's input does not meet the required
     *                                      format by specifying i/INGREDIENT; q/QUANTITY;
     *                                      p/PRICE;
     * 
     */
    public AddStockCommand(String ingredientInput) throws InvalidStockCommandException {  
        Map<String, Pair<Integer, Double>> ingredientInfo = 
                parseIntoAddIngredientArgs(ingredientInput);
        
        String ingredientName = ingredientInfo
                .entrySet()
                .stream()
                .map(item -> item.getKey())
                .map(Object::toString)
                .collect(Collectors.joining(""));
        
        int quantity = ingredientInfo.get(ingredientName).first();
        double price = ingredientInfo.get(ingredientName).second();
        
        this.ingredientToAdd = new Ingredient(ingredientName, 
                Optional.of(quantity), Optional.of(price));               
    }
    
    /** 
     * Parses the user's input into readable arguments that will be used to construct an 
     * Ingredient object. The arguments are then stored in a HashMap. For example: 
     * ' i/tomato; q/10; p/$0.50;' will store 'tomato' as the ingredient name, '10' as
     * the ingredient quantity and '$0.50' as the ingedient's price. Note that the 
     * constructor of an Ingredient is new Ingredient(NAME, QUANTITY, PRICE).
     * Two checks are ENFORCED here. The first check checks if there are missing tags in
     * the user's input. The second check checks if there are any blanks after parsing
     * the various arguments.
     * @throws InvalidStockCommandException If the user's input does not meet the required
     *                                      format by specifying i/INGREDIENT; q/QUANTITY;
     *                                      p/PRICE STRICTLY;
     * 
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
                throw new InvalidStockCommandException("The user's input did not meet the required format."
                        + " Please enter in the following format:"
                        + ls
                        + ls
                        + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; "
                        + "p/PRICE;`");
            }
        }     
        
        // Second check.
        checkValidParsedIngredientArguments(parsedIngredientName, parsedQuantity, parsedPrice);
        
        Map<String, Pair<Integer, Double>> ingredientInfo = new HashMap<>();
        ingredientInfo.put(parsedIngredientName.get(), Pair.of(parsedQuantity.get(), parsedPrice.get()));    
        return ingredientInfo;
    }    
    
    /**
     * Checks the parsed ingredients' arguments if all information of the ingredient are 
     * being tag accordingly. For example, the ingredient's name must be tagged with 'i/',
     * the ingredient's quantity must be tagged with 'q/' and the ingredient's price must
     * be tagged with 'p/'.
     * @throws InvalidStockCommandException If the user's input does not meet the required
     *                                      format by specifying i/INGREDIENT; q/QUANTITY;
     *                                      p/PRICE;
     *                                      
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
                    + " Please enter in the following format:"
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;`");
        } else if (hasIngredientNameTag && !hasQuantityTag && hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the 'q/' tag"
                    + " before the ingredient's quantity."
                    + " Please enter in the following format:"
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;`");
        } else if (hasIngredientNameTag && hasQuantityTag && !hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the 'p/' tag"
                    + " before the ingredient's price."
                    + " Please enter in the following format:"
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;`");
        } else if (!hasIngredientNameTag && !hasQuantityTag && hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the "
                    + "'i/' tag and 'q/' tag"
                    + " before the ingredient's name and quantity."
                    + ls
                    + " Please enter in the following format:"
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;`");
        } else if (!hasIngredientNameTag && hasQuantityTag && !hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the "
                    + "'i/' tag and 'p/' tag"
                    + " before the ingredient's name and price."
                    + ls
                    + " Please enter in the following format:"
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;`");
        } else if (hasIngredientNameTag && !hasQuantityTag && !hasPriceTag) {
            throw new InvalidStockCommandException("The user's input did not specify the "
                    + "'q/' tag and 'p/' tag"
                    + " before the ingredient's quantity and price."
                    + ls
                    + " Please enter in the following format:"
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;`");
        } else if (!hasIngredientNameTag && !hasQuantityTag && !hasPriceTag)  {
            throw new InvalidStockCommandException("The user's input did not specify the "
                    + "'i/', 'q/' tag and 'p/' tag"
                    + " before the ingredient's name, quantity and price."
                    + ls
                    + " Please enter in the following format:"
                    + ls
                    + ls
                    + "`add stock; i/INGREDIENT_NAME; q/QUANTITY_TO_BE_ADDED; p/PRICE;`");
        } else {
            assert (hasIngredientNameTag && hasQuantityTag && hasPriceTag);
            return;
        }
    }
    
    /**
     * Checks the parsed ingredients' arguments if it meets the correct format.
     * @throws InvalidStockCommandException If the user's input does not meet the required
     *                                      format by specifying i/INGREDIENT; q/QUANTITY;
     *                                      p/PRICE;
     *                                      
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
            assert (ingredientName.isPresent() && quantity.isPresent() && price.isPresent());
            return;
        }
    }
    
    @Override
    public void execute(Stock stock) {
        stock.addIngredient(ingredientToAdd);
        System.out.println("Ingredient " 
                + ingredientToAdd.getIngredientName() 
                + " successfully added!");
        
    }
    
    /** A utility function to facilitate testing of execute(). */
    public String printExecuteOutput(Stock stock) {
        String outputMessage = "";
        stock.addIngredient(ingredientToAdd);
        outputMessage += ("Ingredient " 
                + ingredientToAdd.getIngredientName() 
                + " successfully added!");
        
        return outputMessage;
    }
    
    public Ingredient getIngredientInAddCommand() {
        return this.ingredientToAdd;
    }
}
