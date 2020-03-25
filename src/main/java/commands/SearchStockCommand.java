package commands;

import stock.Stock;

/**
 * This class focuses on the 'search' functionality 
 * on the stock category.
 *
 */
public class SearchStockCommand extends StockCommand {
    
    private final String keyword;
    
    public SearchStockCommand(String keyword) 
            throws InvalidStockCommandException {
        
        if (keyword.isBlank()) {
            throw new InvalidStockCommandException("Please "
                    + "enter an ingredient's name to be "
                    + "searched against the stock.");
        } else {
            this.keyword = keyword.trim();
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
