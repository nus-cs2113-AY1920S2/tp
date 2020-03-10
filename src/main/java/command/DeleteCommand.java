package command;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ingredient.Ingredient;
import ingredient.IngredientNotFoundException;

/**
 * This class focuses on the 'delete' functionality of the application.
 * 
 */
public class DeleteCommand extends Command {
    
    private final Ingredient ingredientToDelete;
    
    /**
     * A convenience constructor that contains information of an ingredient 
     * stored in a hashMap.
     * 
     */
    public DeleteCommand(Map<String, Optional<Integer>> ingredientInfo) {
        
        String ingredientName = ingredientInfo
                .entrySet()
                .stream()
                .map(item -> item.getKey())
                .map(Object::toString)
                .collect(Collectors.joining(""));
        
        Optional<Integer> quantity = ingredientInfo.get(ingredientName);
        
        this.ingredientToDelete = new Ingredient(ingredientName, quantity, Optional.empty());               
    }
    
    @Override
    public CommandResult execute() {
             
        
        
        try {
            this.stock.deleteIngredient(ingredientToDelete);
        } catch (IngredientNotFoundException e) {
            return new CommandResult("Ingredient " 
                    + this.ingredientToDelete.getIngredientName() 
                    + " not found and cannot be deleted!");
        }
        return new CommandResult(createDeleteResultMessage());
    }
    
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
}
