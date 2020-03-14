package commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import commands.InvalidStockCommandException;
import ingredient.Ingredient;
import stock.Stock;
import utils.Pair;

/**
 * This class focuses on the 'add' functionality of the application
 * on the stock category.
 * 
 */
public class AddStockCommand extends StockCommand {
    
    /** The ingredient to be added into the stock. */
    private final Ingredient ingredientToAdd;
    
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
     * @throws InvalidStockCommandException If the user's input does not meet the required
     *                                      format by specifying i/INGREDIENT; q/QUANTITY;
     *                                      p/PRICE;
     * 
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
