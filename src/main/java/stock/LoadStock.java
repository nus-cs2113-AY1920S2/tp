package stock;

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

public class LoadStock {
    
    /** The default file name that is to be read or written by the program. */
    public static final String DEFAULT_STORAGE_FILEPATH = "report.txt";
    
    public final Path path;

    
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
        
        decodeStockFromReportTextFile(lines, stock);
    }
    
    private void decodeStockFromReportTextFile(List<String> lines, Stock stock) {

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
                
                String ingredientName = ""; 
                for (int j = 1; j < priceIndexInLineArgs; j++) {
                    ingredientName += lineArgs[j];
                }
    
                String ingredientPrice = lineArgs[priceIndexInLineArgs].substring(7, (lineArgs[priceIndexInLineArgs].length()));
                double parsedIngredientPrice = Double.parseDouble(ingredientPrice);
                
                String ingredientQuantity = lineArgs[quantityIndexInLineArgs].substring(9, (lineArgs[quantityIndexInLineArgs].length()));
                int parsedIngredientQuantity = Integer.parseInt(ingredientQuantity);
                
                Ingredient ingredientToAdd = new Ingredient(ingredientName, Optional.of(parsedIngredientQuantity), Optional.of(parsedIngredientPrice));
                
                stock.addIngredient(ingredientToAdd);
            }

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
