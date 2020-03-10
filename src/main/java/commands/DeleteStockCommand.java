package commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ingredient.Ingredient;
import ingredient.IngredientNotFoundException;
import stock.Stock;

/**
 * This class focuses on the 'delete' functionality of the application.
 * 
 */
public class DeleteStockCommand extends StockCommand {
    
    private final Ingredient ingredientToDelete;
    
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
     * 
     */
    public DeleteStockCommand(String ingredientInput) {
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
     * Parses the user's input into readable arguments that 
     * will be used to construct an Ingredient object.
     * The arguments are then stored in a HashMap.
     * For example: ' i/tomato; q/10;' will store 'tomato' 
     * as the ingredient name, '10' as the ingredient quantity 
     * to be deleted. Note that the specification of the ingredient's quantity 
     * is optional.
     * 
     */
    private Map<String, Optional<Integer>> parseIntoDeleteIngredientArgs(
            String fullInputLine) {
        
        Map<String, Optional<Integer>> ingredientInfo = new HashMap<>();
        String[] wordArgs = fullInputLine.split(";");
        
        boolean hasQuantitySpecified = fullInputLine.contains("q/");
        String ingredientName = parseIngredientName(wordArgs[1].trim());

        if (hasQuantitySpecified) {            
            int quantity = parseIngredientQuantity(wordArgs[2].trim());
            ingredientInfo.put(ingredientName, Optional.of(quantity));
        } else {
            ingredientInfo.put(ingredientName, Optional.empty());
        }
        
        return ingredientInfo;
    }
    
    @Override
    public void execute(Stock stock) {    
        try {
            stock.deleteIngredient(ingredientToDelete);
        } catch (IngredientNotFoundException e) {
            System.out.println("Ingredient " 
                    + this.ingredientToDelete.getIngredientName() 
                    + " not found and cannot be deleted!");
        }
        System.out.println(createDeleteResultMessage());
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
