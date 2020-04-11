package stock;

import exceptions.IngredientNotFoundException;
import ingredient.Ingredient;
import report.LoadStock;
import utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class encapsulates all information of the ingredient
 * stock. It contains all the ingredients stored, along with 
 * their respective current prices and quantities.
 *
 */
public class Stock {   
    
    /** 
     * A HashMap that maps a ingredient's name to its 
     * quantity and price.
     * Note that quantity is an Integer and price is a Double.
     * 
     */
    private static Map<String, Pair<Integer, Double>> stock = null;
    
    /** A HashSet to keep track of duplicated ingredient names in the stock. */
    private static Set<String> duplicateIngredientNameSet = null;
    
    /** A stock decoder that loads data from the report.txt file. */
    private static LoadStock stockLoader = null;

    private final String ls = System.lineSeparator();
    
    public Stock() {
        stock = new HashMap<>();
        duplicateIngredientNameSet = new HashSet<>();
        stockLoader = new LoadStock();

    }
    
    /**
     * Adds an ingredientName to the current stock. If the ingredient
     * exists previously, update the quantity by adding the current quantity
     * to the previous quantity. Otherwise, add an entirely new entry into
     * the HashMap. Note that the latest price of an ingredient specified by 
     * the user will override the current price of that ingredient in the stock.
     * Before the ingredient is added, a check will be performed to prevent 
     * any duplicates in the stock. 
     * 
     */
    public void addIngredient(Ingredient ingredientToAdd) {
        
        String ingredientName = ingredientToAdd.getIngredientName();
        int quantityToAdd = ingredientToAdd.getIngredientQuantity();
        double ingredientPrice = ingredientToAdd.getIngredientPrice();
              
        if (stock.containsKey(ingredientName)) {
            int currQuantity = stock.get(ingredientName).first();
            int newQuantity = currQuantity + quantityToAdd;
            stock.replace(ingredientName, Pair.of(newQuantity, ingredientPrice));
        } else {
            if (checkForDuplicateIngredientName(ingredientName)) {
                printDuplicateIngredientMessage(ingredientName);
            }
            stock.put(ingredientName, Pair.of(quantityToAdd, ingredientPrice));
            duplicateIngredientNameSet.add(ingredientName.toLowerCase());

        }
    }
    
    /**
     * This method has the same implementation as addIngredient().
     * However, this is mainly used for the LoadStock class. This is
     * to prevent the message from showing when the data from 'report.txt'
     * is loaded when the program starts.
     * 
     */
    public void addIngredientWithoutMessage(Ingredient ingredientToAdd) {
        
        String ingredientName = ingredientToAdd.getIngredientName();
        int quantityToAdd = ingredientToAdd.getIngredientQuantity();
        double ingredientPrice = ingredientToAdd.getIngredientPrice();
              
        if (stock.containsKey(ingredientName)) {
            int currQuantity = stock.get(ingredientName).first();
            int newQuantity = currQuantity + quantityToAdd;
            stock.replace(ingredientName, Pair.of(newQuantity, ingredientPrice));
        } else {
            stock.put(ingredientName, Pair.of(quantityToAdd, ingredientPrice));
            duplicateIngredientNameSet.add(ingredientName.toLowerCase());

        }
    }
    
    /**
     * Deletes an ingredientName from the current stock. If the ingredient
     * exists previously, update the quantity by subtracting the quantity
     * supplied by the user to the current quantity. If there is no quantity
     * specified, removed the ingredientName from the HashMap. Note that the 
     * quantity for an ingredient to be deleted is optional. Also, there is a 
     * lower 0 bound such that any subtraction of the quantity cannot be lesser 
     * than zero. An ingredient with 0 quantity however, is not deleted from the 
     * hashMap.
     * @throws IngredientNotFoundException If the ingredient does not exist in the
     *                                     stock previously.
     * 
     */
    public void deleteIngredient(Ingredient ingredient) 
            throws IngredientNotFoundException {
        
        String ingredientName = ingredient.getIngredientName();
        boolean hasIngredientInStock = stock.containsKey(ingredientName);       
        boolean hasQuantitySpecified = ingredient.isQuantitySpecified();        
        
        if (hasIngredientInStock && hasQuantitySpecified) {
            int currQuantity = stock.get(ingredientName).first();
            int quantityToDelete = ingredient.getIngredientQuantity();
            int newQuantity = currQuantity - quantityToDelete;
            
            newQuantity = ((newQuantity < 0) ? 0 : newQuantity);
            
            double ingredientPrice = stock.get(ingredientName).second(); 
            stock.replace(ingredientName, Pair.of(newQuantity, ingredientPrice));
        } else if (hasIngredientInStock && !hasQuantitySpecified) {    
            stock.remove(ingredientName);
            duplicateIngredientNameSet.remove(ingredientName.toLowerCase());
        } else {
            throw new IngredientNotFoundException("This ingredient "
                    + "is not in the stock currently!");
        }
    }
    
    /**
     * Lists all current ingredients in the stock.
     */
    public void listIngredient() {
        if (stock.isEmpty()) {
            System.out.println("There is nothing in the stock currently.");
        } else {
            System.out.println("Here are the ingredients in the stock currently:");
            System.out.println("");
            
            printStock();
            
            System.out.println("");
            System.out.println("All ingredients listed successfully!"); 
        }
    }
    
    /**
     * Searches the current stock against a given keyword and lists all ingredients
     * that contains the keyword. 
     */
    public void searchStock(String keyword) {       
        boolean hasIngredientWithKeyword = checkIngredientInStock(keyword);
        
        if (!hasIngredientWithKeyword) {
            System.out.println("There is no ingredient that matches the keyword given.");
        } else {
            printSearchResult(keyword);
        }
    }
    
    /**
     * A utility function to print the search results of ingredients within the stock that matches 
     * the keyword given.
     */
    private void printSearchResult(String keyword) {
        System.out.println("Here are the ingredients in the stock that matches the keyword:");
        System.out.println("");
    
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.entrySet());
        
        int ingredientCounter = 1;

        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
            String ingredientName = ingredient.getKey();
            
            if (ingredientName.contains(keyword) || ingredientName
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {
                
                int quantity = ingredient.getValue().first();
                double price = ingredient.getValue().second();
                System.out.println(ingredientCounter 
                        + ". "
                        + "[" 
                        + quantity 
                        + "]"
                        + "[$" 
                        + String.format("%.2f", price) 
                        + "]"
                        + " " 
                        + ingredientName);
                
                ingredientCounter++;
            } 
        }

        System.out.println("");
    }
    
    /** Clears the stock completely. */
    public void clearStock() {
        stock.clear();
        System.out.println("The stock has been cleared.");
    }
    
    /**
     * A utility function to check against stock if any of the ingredient within the stock
     * matches the keyword supplied by the user.
     */
    private boolean checkIngredientInStock(String keyword) {
        boolean hasIngredientWithKeyword = false;
        
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.entrySet());
        
        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
            String ingredientName = ingredient.getKey();
            
            if (ingredientName.contains(keyword) || ingredientName
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {
                
                hasIngredientWithKeyword = true;
            }
        }
        
        return hasIngredientWithKeyword;
    }
    
    /**
     * A utility function to check within the stock if there are ingredient names similar 
     * to the one just added. For example, if the stock contains 'tomato', adding another
     * ingredient named 'TomATo' will return true.
     */
    private boolean checkForDuplicateIngredientName(String ingredientNameToCheck) {
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.entrySet()); 
        boolean hasDuplicateIngredientName = false;
        
        if (tempList.size() < 1) {
            return hasDuplicateIngredientName;
        } else {
            for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
                String ingredientName = ingredient.getKey();
                if (ingredientName.toLowerCase().equals(ingredientNameToCheck.toLowerCase())) {
                    hasDuplicateIngredientName = true;
                    break;
                }
            }
            return hasDuplicateIngredientName;
        }
    }
    
    /**
     * A utility function to inform user that there is an existing ingredient in the stock
     * that has the same name as the one that just added.
     */
    private void printDuplicateIngredientMessage(String duplicateIngredientName) {
        String outputMessage = ls;
        
        outputMessage += ("Please note that there are other similar ingredient names in the stock."
                + ls
                + ls);
        
        outputMessage += ("You are currently adding: '"
                + duplicateIngredientName
                + "'");
        
        outputMessage += (ls
                + ls
                + "Here are the ingredients in the stock with similar names:"
                + ls);
        
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.entrySet());
        
        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
            String ingredientName = ingredient.getKey();
            if (ingredientName.toLowerCase().equals(duplicateIngredientName.toLowerCase())) {
                outputMessage += (ingredientName
                        + ls);
            }
        }
        
        outputMessage += (ls
                + "You may want to remove the unwanted ingredient names if it is a duplicate.");
        
        outputMessage += (ls);
        
        System.out.println(outputMessage);
    }
    
    /**
     * A utility function to print the stock to help to parse the price into 2 decimal places
     * to display as dollars appropriately. Prints ingredients in descending quantity within 
     * the stock.
     */
    private void printStock() {
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.entrySet());
        
        Collections.sort(tempList, new Comparator<Entry<String, Pair<Integer, Double>>>() {
            @Override
            public int compare(Entry<String, Pair<Integer, Double>> firstEntry, 
                    Entry<String, Pair<Integer, Double>> secondEntry) {
                
                int firstEntryQuantity = firstEntry.getValue().first();
                int secondEntryQuantity = secondEntry.getValue().first();
                return secondEntryQuantity - firstEntryQuantity;
            }
        });      
        
        int ingredientCounter = 1;

        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
            String ingredientName = ingredient.getKey();
            int quantity = ingredient.getValue().first();
            double price = ingredient.getValue().second();
            System.out.println(ingredientCounter 
                    + ". "
                    + "[" 
                    + quantity 
                    + "]"
                    + "[$" 
                    + String.format("%.2f", price) 
                    + "]"
                    + " " 
                    + ingredientName);
           
            ingredientCounter++;
        }
    }
        
    public static Map<String, Pair<Integer, Double>> getStock() {
        return stock;
    }
    
    public static Set<String> getDuplicateIngredientNames() {
        return duplicateIngredientNameSet;
    }
    
    public static LoadStock getStockLoader() {
        return stockLoader;
    }
}