package command;

/**
 * This class focuses on the 'list' functionality of the application.
 * 
 */
public class ListCommand extends Command {
    
    private final String category;
    
    public ListCommand(String category) {
        this.category = category;
    }
    
    @Override
    public CommandResult execute() {
        switch (category) {
        case ("stock") :
            this.stock.listIngredient();
            return new CommandResult("All ingredients listed successfully!");
        default: 
            throw new IllegalStateException("Invalid category for list command");
        }      
    }
}
