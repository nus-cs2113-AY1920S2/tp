package commands;

/** 
 * Represents the 'Exit' functionality of a command. This command
 * serves to exit the program upon executed.  
 * 
 */
public class ExitCommand extends StockCommand {
    
    /** Returns true if a Command is an ExitCommand. */
    public static boolean isExit(StockCommand command) {
        return command instanceof ExitCommand;
    }
    
    @Override
    public CommandResult execute() {
        return new CommandResult("Program Exited");
    }
}

