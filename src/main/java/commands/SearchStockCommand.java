package commands;

import exceptions.InvalidStockCommandException;
import stock.Stock;

/**
 * This class focuses on the 'search' functionality 
 * on the stock category.
 *
 */
public class SearchStockCommand extends StockCommand {
    
    private final String keyword;
    
    /** The relative index of 'k' present within the string. */
    private final int indexOfKCharacter = 0;
    
    /** The relative index of '/' present within the string. */
    private final int indexOfSlashCharacter = 1;
    
    /** The length of 'k/' tag. */
    private final int keywordTagLength = 2;
    
    /**
     * A convenience constructor that contains the keyword to be searched
     * against the ingredients in the stock.
     * 
     */
    public SearchStockCommand(String keyword) 
            throws InvalidStockCommandException {
        
        this.keyword = parseIntoSearchKeyword(keyword);
    }
    
    /**
     * Parses the user input into readable keyword. For example, 'search stock; 
     * k/tomato' will return 'tomato' as the keyword. The 
     * @throws InvalidStockCommandException If the user's input does not 
     *                                      meet the required format by 
     *                                      specifying 'k/KEYWORD'.
     */
    private String parseIntoSearchKeyword(String fullInputLine) 
            throws InvalidStockCommandException {
        
        checkValidSearchKeywordArgumentsSupplied(fullInputLine);
        
        String trimmedKeyword = fullInputLine.trim();
        
        if (trimmedKeyword.contains(";")) {
            String trimmedKeywordWithColon = trimmedKeyword
                    .substring(indexAfterSlash, trimmedKeyword.length() - 1);
            return trimmedKeywordWithColon; 
        } else {
            String trimmedKeywordWithoutColon = trimmedKeyword
                .substring(indexAfterSlash, trimmedKeyword.length());
            return trimmedKeywordWithoutColon; 
        }            
    }
    
    /**
     * Checks the parsed ingredients' arguments if it meets the correct format.
     * @throws InvalidStockCommandException If the user's input does not meet the required
     *                                      format by specifying 'k/KEYWORD'.
     *                                      
     */
    private void checkValidSearchKeywordArgumentsSupplied(String fullInputLine) 
            throws InvalidStockCommandException {
        
        String trimmedFullInputLine = fullInputLine.trim();
        checkForBlankArguments(trimmedFullInputLine);
        checkForKAndSlashBeforeKeyword(trimmedFullInputLine);
    }
    
    /**
     * A utility function to check for if the user input supplied is a blank entry.
     * @throws InvalidStockCommandException If the user's input supplied nothing or
     *                                      the KEYWORD from 'k/KEYWORD' is a blank.
     */
    private void checkForBlankArguments(String trimmedFullInputLine) 
            throws InvalidStockCommandException {
        
        if (trimmedFullInputLine.isBlank()) {
            throw new InvalidStockCommandException("Please "
                    + "enter an ingredient's name to be "
                    + "searched against the stock.");
        } else if (trimmedFullInputLine.length() == keywordTagLength) {
            checkForKAndSlashBeforeKeyword(trimmedFullInputLine);
            throw new InvalidStockCommandException("Please "
                    + "enter an ingredient's name to be "
                    + "searched against the stock.");
        } else if (trimmedFullInputLine.length() > keywordTagLength) {
            if (trimmedFullInputLine.substring(indexAfterSlash, 
                    trimmedFullInputLine.length()).isBlank()) {
                throw new InvalidStockCommandException("Please "
                        + "enter an ingredient's name to be "
                        + "searched against the stock.");
            }
        }            
    }
    
    /**
     * A utility function to check if the user input specify 'k/' before the keyword.
     * @throws InvalidStockCommandException If the first two characters from the user's
     *                                      input is not 'k/' or when the length of
     *                                      user's input is less than 2.
     */
    private void checkForKAndSlashBeforeKeyword(String trimmedFullInputLine) 
            throws InvalidStockCommandException {
        if (trimmedFullInputLine.length() < keywordTagLength) {
            throw new InvalidStockCommandException("Please "
                    + "specify the keyword using the format "
                    + "'k/keyword;'");
        } else if (trimmedFullInputLine.length() >= keywordTagLength) {
            if (trimmedFullInputLine.charAt(indexOfKCharacter) != 'k' 
                    || trimmedFullInputLine.charAt(indexOfSlashCharacter) != '/') {
                throw new InvalidStockCommandException("Please "
                        + "specify the keyword using the format "
                        + "'k/keyword;'");
            }
        }
    }    
    
    @Override
    public void execute(Stock stock) {
        stock.searchStock(keyword);
    }
    
    public String getKeyword() {
        return this.keyword;
    }
}
