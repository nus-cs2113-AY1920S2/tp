package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ListCommand;

/**
 * This class is used to parse user's input into readable format
 * that can be constructed into commands to be executed.
 *
 */
public class Parser {
    
    /** 
     * Parses the user's input into specific command type 
     * based on the command word used. 
     * For instance: 'add stock; i/bacon; q/10; p/$0.50;' 
     * will be parse an AddCommand due to the word 'add'.
     * 
     */
    public Command parseCommand(String fullInputLine) {
        Command command;
        String commandType = parseCommandType(fullInputLine);
        
        switch (commandType) {
        case "add": 
            command = parseAddCommand(fullInputLine);         
            break;
        case "list":
            command = parseListCommand(fullInputLine);
            break;
        case "delete":
            command = parseDeleteCommand(fullInputLine);
            break;
        default:
            throw new IllegalStateException("Invalid command");
        } 
        
        return command;   
    }
    
    /** 
     * Parses the user's input into specific AddCommand  
     * based on the category. 
     * For instance: 'add stock; i/bacon; q/10; p/$0.50;' 
     * will be parse an AddCommand for the category 'stock' 
     * due to the word 'stock' used.
     * 
     */
    private Command parseAddCommand(String fullInputLine) {
        String category = parseCategory(fullInputLine);
        Command command;
        
        switch (category) {
        case "stock":
            command = new AddCommand(parseIntoAddIngredientArgs(fullInputLine));
            break;  
        default:
            throw new IllegalStateException("Invalid add command");
        }
        
        return command;
    }
    
    /** 
     * Parses the user's input into specific DeleteCommand  
     * based on the category. 
     * For instance: 'delete stock; i/bacon; q/10;' 
     * will be parse a DeleteCommand for the category 'stock' 
     * due to the word 'stock' used.
     * 
     */
    private Command parseDeleteCommand(String fullInputLine) {
        String category = parseCategory(fullInputLine);
        Command command;
        
        switch (category) {
        case "stock":
            command = new DeleteCommand(parseIntoDeleteIngredientArgs(fullInputLine));
            break;
        default:
            throw new IllegalStateException("Invalid delete command");
        }      
        
        return command;
    }
    
    /** 
     * Parses the user's input into specific ListCommand  
     * based on the category. 
     * For instance: 'list stock' will be parse a ListCommand 
     * for the category 'stock' due to the word 'stock' used.
     * 
     */
    private Command parseListCommand(String fullInputLine) {
        String category = parseCategory(fullInputLine);
        Command command;
        
        switch (category) {
        case "stock":
            command = new ListCommand(category);
            break;
        default:
            throw new IllegalStateException("Invalid list command");
        }                 
        
        return command;
    }    
    
    /** 
     * Extract the commandType word from the user's input.
     * For example: 'add stock; i/tomato; q/10; p/$0.50;' 
     * will extract the commandType of 'add' 
     * 
     */
    private String parseCommandType(String fullInputLine) {
        boolean hasSemiColon = fullInputLine.contains(";");
        String[] wordArgs;
        
        if (hasSemiColon) {
            wordArgs = fullInputLine.split("; ");
        } else {
            wordArgs = fullInputLine.split(" ");
        }
        
        String zeroIndexWords = wordArgs[0];
        String[] zeroIndexWordsArgs = zeroIndexWords.split(" ");
        String commandType = zeroIndexWordsArgs[0].trim();
        
        return commandType;
    }


    /** 
     * Extract the category word from the user's input.
     * For example: 'add stock; i/tomato; q/10; p/$0.50;' 
     * will extract the category of 'stock'. 
     * 
     */
    private String parseCategory(String fullInputLine) {        
        boolean hasSemiColon = fullInputLine.contains(";");
        String[] wordArgs;
        String category;
        
        if (hasSemiColon) {
            wordArgs = fullInputLine.split("; ");
            String zeroIndexWords = wordArgs[0];
            String[] zeroIndexWordsArgs = zeroIndexWords.split(" ");       
            category = zeroIndexWordsArgs[1].trim();
        } else {
            wordArgs = fullInputLine.split(" ");
            category = wordArgs[1].trim();
        }
        
        return category;
    }
    
    /** 
     * Parses the user's input into readable arguments that 
     * will be used to construct an Ingredient object.
     * The arguments are then stored in a HashMap.
     * For example: 'add stock; i/tomato; q/10; p/$0.50;' 
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
        
        String ingredientName = parseIngredientName(wordArgs[1].trim());
        int quantity = parseIngredientQuantity(wordArgs[2].trim());
        double price = parseIngredientPrice(wordArgs[3].trim());
        
        ingredientInfo.put(ingredientName, Pair.of(quantity, price));
        return ingredientInfo;
    }
    
    /**
     * Parses the user's input into readable arguments that 
     * will be used to construct an Ingredient object.
     * The arguments are then stored in a HashMap.
     * For example: 'delete stock; i/tomato; q/10;' 
     * will store 'tomato' as the ingredient name, '10' as
     * the ingredient quantity to be deleted. Note that the 
     * specification of the ingredient's quantity 
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
    
    /**
     * Extract the name of the ingredient from a string.
     * For example, 'i/tomato' will return 'tomato'.
     * 
     */ 
    private String parseIngredientName(String ingredientNameInput) {
        String ingredientName = ingredientNameInput.trim()
                .substring(2, ingredientNameInput.length());
        
        return ingredientName;
    }
    
    /**
     * Extract the quantity of the ingredient from a string.
     * For example, 'q/10' will return '10'.
     * 
     */ 
    private int parseIngredientQuantity(String ingredientQuantityInput) {
        int quantity = Integer.parseInt(ingredientQuantityInput.trim()
                .substring(2, ingredientQuantityInput.length()));
        
        return quantity;
    }
    
    /**
     * Extract the price of the ingredient from a string.
     * For example, 'p/$0.50' will return '0.5'.
     * Note that the price will not strictly be in 2 decimal 
     * places. This will be handled in the AddCommand.
     * 
     */ 
    private double parseIngredientPrice(String ingredientPriceInput) {
        double price = Double.parseDouble(ingredientPriceInput.trim()
                .substring(3, ingredientPriceInput.length()));
        
        return price;
    }
}
