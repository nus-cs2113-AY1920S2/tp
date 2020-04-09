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

/**
 * This class encapsulates the functionality of loading data related to
 * the 'Stock' category in the report.txt file.
 *
 */
public class LoadStock {
    
    /** The default file name that is to be read or written by the program. */
    public static final String DEFAULT_STORAGE_FILEPATH = "report.txt";
    
    /** Constructor of a Storage using the default file path. */
    public final Path path;

    /** 
     * Constructor of a LoadStock retrieved from Path.
     * 
     * @param filePath
     * @throws InvalidStorageFilePathException If the report data file does not match
     *                                         a .txt extension.
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

                String[] lineArgs = line.split(" ");
                if (line.indexOf('$') == -1) {
                    continue;
                } else {
                    lines.add(line);
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
     */
    private void decodeIngredientFromReportTextFile(List<String> lines, Stock stock) {

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
                
                stock.addIngredient(ingredientToAdd);
            }
        }
    }
    
    private String decodeIngredientName(String line, int priceIndexInLineArgs) {
        String[] lineArgs = line.split(" ");
        
        String ingredientName = ""; 
        for (int j = 1; j < priceIndexInLineArgs; j++) {
            ingredientName += lineArgs[j]
                    + " ";
        }
        return ingredientName;        
    }
    
    private double decodeIngredientPrice(String line, int priceIndexInLineArgs) {
        String[] lineArgs = line.split(" ");
        
        String ingredientPrice = lineArgs[priceIndexInLineArgs].substring(7, 
                (lineArgs[priceIndexInLineArgs].length()));
        double parsedIngredientPrice = Double.parseDouble(ingredientPrice);
        
        return parsedIngredientPrice;
    }
    
    private int decodeIngredientQuantity(String line, int quantityIndexInLineArgs) {
        String[] lineArgs = line.split(" ");
        
        String ingredientQuantity = lineArgs[quantityIndexInLineArgs].substring(9, 
                (lineArgs[quantityIndexInLineArgs].length()));
        int parsedIngredientQuantity = Integer.parseInt(ingredientQuantity);
        
        return parsedIngredientQuantity;
    }
    
    public String getFilePath() {
        return this.path.toString();
    }
    
    /** Returns true if a given file has a .txt extension. */
    public boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    } 
}
