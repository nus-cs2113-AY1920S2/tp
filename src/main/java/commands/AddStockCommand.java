package commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * A convenience constructor that contains information of an ingredient 
     * stored in a hashMap.
     * 
     */
    public AddStockCommand(String ingredientInput) {  
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
     * Parses the user's input into readable arguments that 
     * will be used to construct an Ingredient object.
     * The arguments are then stored in a HashMap.
     * For example: ' i/tomato; q/10; p/$0.50;' 
     * will store 'tomato' as the ingredient name, '10' as
     * the ingredient quantity and '$0.50' as the ingedient's 
     * price. Note that the constructor of an Ingredient is
     * new Ingredient(NAME, QUANTITY, PRICE).
     * 
     */
    private Map<String, Pair<Integer, Double>> parseIntoAddIngredientArgs(
            String fullInputLine) {
        
        Map<String, Pair<Integer, Double>> ingredientInfo = new HashMap<>();
        String[] wordArgs = fullInputLine.split(";");
        String ingredientName = parseIngredientName(wordArgs[0].trim());
        int quantity = parseIngredientQuantity(wordArgs[1].trim());
        double price = parseIngredientPrice(wordArgs[2].trim());
        
        ingredientInfo.put(ingredientName, Pair.of(quantity, price));
        return ingredientInfo;
    }
    
    @Override
    public void execute(Stock stock) {
        stock.addIngredient(ingredientToAdd);
        System.out.println("Ingredient " 
                + ingredientToAdd.getIngredientName() 
                + " successfully added!");
    }

}
