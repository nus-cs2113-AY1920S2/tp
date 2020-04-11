package report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.StockReadWriteException;
import ingredient.Ingredient;
import stock.Stock;

public class LoadStockTest {
    
    /** 
     * The relative index to start decoding for price. In 'PRICE:$0.11';
     * index 7 is that start of the price to be parsed. 
     */
    private final int startIndexToDecodeForPrice = 7;
    
    /** 
     * The relative index to start decoding for price. In 'Quantity:10';
     * index 9 is that start of the price to be parsed. 
     */   
    private final int startIndexToDecodeForQuantity = 9;
    
    private final String lineFromBufferedReader = ("*1. tomato            "
            + "                                               "
            + "      Price:$0.40 Quantity:10");

    @Test
    public void test_decodeIngredientName_decodeNormally() {
        String ingredientName = "tomato";
        String decodedIngredientName = decodeIngredientName(
                lineFromBufferedReader, startIndexToDecodeForPrice);
        
        assertEquals(ingredientName, decodedIngredientName);
    }
    
    @Test 
    public void test_decodeIngredientPrice_decodeNormally() 
            throws StockReadWriteException {
        
        double ingredientPrice = 0.40;
        double decodedIngredientPrice = decodeIngredientPrice(
                lineFromBufferedReader, 
                getStartIngredientPriceIndex(lineFromBufferedReader));
        
        assertEquals(ingredientPrice, decodedIngredientPrice);
    }
    
    @Test 
    public void test_decodeIngredientPrice_outOfBoundException() {
        
        double ingredientPrice = 0.40;
        String lineWithError = ("*1. tomato            "
                + "                                               "
                + "      Pric$40 Quantity:10");
        try {
            double decodedIngredientPrice = decodeIngredientPrice(
                    lineWithError, 
                    getStartIngredientPriceIndex(lineWithError));
        } catch (StockReadWriteException srwe) {
            assertEquals("The price in report.txt is not indented correctly.", 
                    srwe.getMessage());
        }
    }
    
    @Test 
    public void test_decodeIngredientPrice_priceNotDouble() {

        double ingredientPrice = 0.40;
        String lineWithError = ("*1. tomato            "
                + "                                               "
                + "      Price:$4L Quantity:10");
        try {
            double decodedIngredientPrice = decodeIngredientPrice(
                    lineWithError, 
                    getStartIngredientPriceIndex(lineWithError));
        } catch (StockReadWriteException srwe) {
            assertEquals("The price in report.txt cannot be "
                    + "converted to a double.", srwe.getMessage());
        }
    }
    
    
    @Test 
    public void test_decodeIngredientQuantity_decodeNormally() 
            throws StockReadWriteException {
        
        int ingredientQuantity = 10;
        int decodedIngredientQuantity = decodeIngredientQuantity(
                lineFromBufferedReader, 
                getStartIngredientQuantityIndex(lineFromBufferedReader));
        
        assertEquals(ingredientQuantity, decodedIngredientQuantity);
    }
    
    @Test 
    public void test_decodeIngredientQuantity_outOfBoundException() {
        
        double ingredientPrice = 0.40;
        String lineWithError = ("*1. tomato            "
                + "                                               "
                + "      Price:$40 Quanti:10");
        try {
            double decodedIngredientPrice = decodeIngredientPrice(
                    lineWithError, 
                    getStartIngredientPriceIndex(lineWithError));
        } catch (StockReadWriteException srwe) {
            assertEquals("The quantity in report.txt is not indented correctly.", 
                    srwe.getMessage());
        }
    }
    
    @Test 
    public void test_decodeIngredientQuantity_quantityNotInteger() {

        double ingredientPrice = 0.40;
        String lineWithError = ("*1. tomato            "
                + "                                               "
                + "      Price:$0.40 Quantity:LOL");
        try {
            double decodedIngredientPrice = decodeIngredientPrice(
                    lineWithError, 
                    getStartIngredientPriceIndex(lineWithError));
        } catch (StockReadWriteException srwe) {
            assertEquals("The quantity in report.txt cannot be "
                    + "converted to an integer.", srwe.getMessage());
        }
    }
    
    @Test
    public void load_loadDataIntoStock_loadNormally() 
            throws StockReadWriteException {
        
        Stock stock = new Stock();
        List<String> readlines = new ArrayList<>();
        readlines.add(lineFromBufferedReader);
        
        decodeIngredientFromReportTextFile(readlines, stock);
        
        Stock stockCopy = new Stock();
        stockCopy.addIngredient(new Ingredient("tomato", 
                Optional.of(10), Optional.of(0.40)));
        
        assertTrue(stock.getStock().equals(stockCopy.getStock()));
    }
   
    
    /**
     * Utility functions of similar implementation in the LoadStock class. ===============================.
     */
    
    /**
     * A utility function to get the index position ready for decoding in
     * decodeIngredientPrice().
     */
    private int getStartIngredientPriceIndex(String line) {
        int priceIndexInLineArgs = 0;
        String[] lineArgs = line.split(" ");
        
        for (int i = 0; i < lineArgs.length; i++) {
            if (lineArgs[i].contains("Price:")) {
                priceIndexInLineArgs = i;
            } 
        }
        
        return priceIndexInLineArgs;
    }
    
    /**
     * A utility function to get the index position ready for decoding in
     * decodeIngredientQuantity().
     */
    private int getStartIngredientQuantityIndex(String line) {
        int quantityIndexInLineArgs = 0;
        String[] lineArgs = line.split(" ");
        
        for (int i = 0; i < lineArgs.length; i++) {
            if (lineArgs[i].contains("Quantity")) {
                quantityIndexInLineArgs = i;
            } 
        }
        
        return quantityIndexInLineArgs;
    }
    
    
    /**
     * A utility function of similar implementation to decodeIngredientFromReportTextFile()
     * in the Stock class.
     */
    private void decodeIngredientFromReportTextFile(List<String> lines, Stock stock) 
            throws StockReadWriteException {

        for (String line : lines) {

            String[] lineArgs = line.split(" ");
            if (lineArgs[0].equals("Current")) {
                continue;
            } else {            
                
                int priceIndexInLineArgs = 0;
                int quantityIndexInLineArgs = 0;
                
                for (int i = 0; i < lineArgs.length; i++) {
                    if (lineArgs[i].contains("Price:")) {
                        priceIndexInLineArgs = i;
                    } else if (lineArgs[i].contains("Quantity:")) {
                        quantityIndexInLineArgs = i;
                    }
                }
                
                String ingredientName = decodeIngredientName(line, 
                        priceIndexInLineArgs);
                double ingredientPrice = decodeIngredientPrice(line, 
                        priceIndexInLineArgs);
                int ingredientQuantity = decodeIngredientQuantity(line, 
                        quantityIndexInLineArgs);

                Ingredient ingredientToAdd = new Ingredient(ingredientName, 
                        Optional.of(ingredientQuantity), 
                        Optional.of(ingredientPrice));
                
                stock.addIngredientWithoutMessage(ingredientToAdd);
            }
        }
    }
    
    /**
     * A utility function of similar implementation to decodeIngredientName()
     * in the Stock class.
     */
    private String decodeIngredientName(String line, int priceIndexInLineArgs) {
        String[] lineArgs = line.split(" ");
        
        String ingredientName = ""; 
        for (int j = 1; j < priceIndexInLineArgs; j++) {
            ingredientName += lineArgs[j]
                    + " ";
        }
        return ingredientName.trim();        
    }
    
    /**
     * A utility function of similar implementation to decodeIngredientPrice()
     * in the Stock class.
     */
    private double decodeIngredientPrice(String line, int priceIndexInLineArgs) 
            throws StockReadWriteException {
        
        String[] lineArgs = line.split(" ");

        try {
            String ingredientPrice = lineArgs[priceIndexInLineArgs].substring(
                    startIndexToDecodeForPrice, 
                    (lineArgs[priceIndexInLineArgs].length()));
            
            double parsedIngredientPrice = Double.parseDouble(ingredientPrice);
            return parsedIngredientPrice;
        } catch (NumberFormatException nfe) {
            throw new StockReadWriteException("The price in report.txt cannot be "
                    + "converted to a double.");
        } catch (StringIndexOutOfBoundsException siofbe) {
            throw new StockReadWriteException("The price in report.txt is not"
                    + " indented correctly.");
        }
    }
    
    /**
     * A utility function of similar implementation to decodeIngredientQuantity()
     * in the Stock class.
     */
    private int decodeIngredientQuantity(String line, int quantityIndexInLineArgs) 
            throws StockReadWriteException {
        String[] lineArgs = line.split(" ");

        try {
            String ingredientQuantity = lineArgs[quantityIndexInLineArgs].substring(
                    startIndexToDecodeForQuantity, 
                    (lineArgs[quantityIndexInLineArgs].length()));
            
            int parsedIngredientQuantity = Integer.parseInt(ingredientQuantity);
            return parsedIngredientQuantity;
        } catch (NumberFormatException nfe) {
            throw new StockReadWriteException("The quantity in report.txt cannot be"
                    + "be converted to an integer.");
        } catch (StringIndexOutOfBoundsException siofbe) {
            throw new StockReadWriteException("The quantity in report.txt is not"
                    + " indented correctly.");
        }
    }
}
