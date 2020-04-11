package report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.InvalidFilePathException;
import exceptions.StockReadWriteException;
import ingredient.Ingredient;
import stock.Stock;

import static utils.Constants.DEFAULT_STORAGE_FILEPATH;

/**
 * This class encapsulates the functionality of loading data related to
 * the 'Stock' category in the report.txt file.
 *
 */
public class LoadStock {
        
    /** Constructor of a Storage using the default file path. */
    public final Path path;
    
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

    /** 
     * Constructor of a LoadStock retrieved from Path.
     */
    public LoadStock() {
        this(DEFAULT_STORAGE_FILEPATH);
    }
    
    
    public LoadStock(String filePath) {
        this.path = Paths.get(filePath);
        
        if (!isValidPath(this.path)) {
            throw new InvalidFilePathException(this.path.toString(),
                 "The current file does not end with a .txt");
        }
    }
    
    /** 
     * Silently loads all data from the report.txt file.
     * @throws StockReadWriteException If the current storage file cannot be
     *                                 loaded.  
     */
    public void loadStockData(Stock stock) throws StockReadWriteException {        
        List<String> lines = new ArrayList<>();

        try {
            String line;                       
            BufferedReader br = new BufferedReader(
                    new FileReader(this.path.toString()));
            
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                } else {
                    
                    String[] lineArgs = line.split(" ");
                    if (line.indexOf('*') == -1) {
                        continue;
                    } else {
                        lines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            throw new StockReadWriteException(
                    "The data under 'Stock' category appears to be corrupted and "
                    + "cannot be loaded.");
        }
        
        decodeIngredientFromReportTextFile(lines, stock);
    }
    
    /**
     * Decodes each line from the BufferedReader into readable ingredient arguments 
     * format to be added into the Stock class.
     * @throws StockReadWriteException If the ingredient quantity or price cannot be
     *                                 parsed into an integer or a double respectively.
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
     * Decodes the ingredient name from the line read from the BufferedReader.
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
     * Decodes the ingredient price from the line read from the BufferedReader.
     * @throws StockReadWriteException If the ingredient price cannot be parsed
     *                                 into a double.
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
     * Decodes the ingredient quantity from the line read from the BufferedReader.
     * @throws StockReadWriteException If the ingredient quantity cannot be parsed
     *                                 into an integer.
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
    
    public String getFilePath() {
        return this.path.toString();
    }
    
    /** Returns true if a given file has a .txt extension. */
    public boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    } 
}