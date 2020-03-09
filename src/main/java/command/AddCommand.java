package command;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ingredient.Ingredient;
import utils.Pair;

/**
 * This class focuses on the 'add' functionality of the application.
 * 
 */
public class AddCommand extends Command {
    
    /** The ingredient to be added into the stock. */
    private final Ingredient ingredientToAdd;
    
    public AddCommand(Map<String, Pair<Integer, Double>> ingredientInfo) {  
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
    
    @Override
    public CommandResult execute() {
        this.stock.addIngredient(ingredientToAdd);
        return new CommandResult("Ingredient " 
                + ingredientToAdd.getIngredientName() 
                + " successfully added!");
    }

}
