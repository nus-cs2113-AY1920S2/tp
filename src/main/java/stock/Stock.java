package stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ingredient.Ingredient;
import ingredient.IngredientNotFoundException;
import utils.Pair;

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
    
    public Stock() {
        stock = new HashMap<>();
    }
    
    /**
     * Adds an ingredientName to the current stock. If the ingredient
     * exists previously, update the quantity by adding the current quantity
     * to the previous quantity. Otherwise, add an entirely new entry into
     * the HashMap. Note that the latest price of an ingredient specified by 
     * the user will override the current price of that ingredient in the stock.
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
            stock.put(ingredientName, Pair.of(quantityToAdd, ingredientPrice));
        }
    }
    
    /**
     * Deletes an ingredientName from the current stock. If the ingredient
     * exists previously, update the quantity by subtracting the quantity
     * supplied by the user to the current quantity. If there is no quantity
     * specified, removed the ingredientName from the HashMap. Note that the 
     * quantity for an ingredient to be deleted is optional. Also, there is a 
     * lower 0 bound such that any subtraction of the quantity
     * cannot be lesser than zero. An ingredient with 0 quantity however, is not
     * deleted from the hashMap.
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
        } else {
            throw new IngredientNotFoundException("This ingredient "
                    + "is not in the stock currently!");
        }
    }
    
    /**
     * Lists all current ingredients in the stock.
     */
    public void listIngredient() {
        System.out.println("Here are the ingredients in the stock currently:");
        System.out.println("============================================================"
                + "================================================================");
        printStock();
        System.out.println("============================================================"
                + "================================================================");       
    }
    
    /**
     * Search the current stock against a given keyword and lists all ingredients
     * that contains the keyword.
     */
    public void searchStock(String keyword) {
        System.out.println("Here are the ingredients in the stock that matches the keyword:");
        System.out.println("============================================================"
                + "================================================================");
        
        List<Entry<String, Pair<Integer, Double>>> tempList = new ArrayList<>(stock.entrySet());
        
        int ingredientCounter = 1;

        for (Entry<String, Pair<Integer, Double>> ingredient : tempList) {
            String ingredientName = ingredient.getKey();
            
            if (ingredientName.contains(keyword)) {
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
            } else {
                continue;
            }
        }
        
        System.out.println("============================================================"
                + "================================================================"); 
        
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
    
}
