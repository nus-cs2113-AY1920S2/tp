package command;

/**
 * Represents the result after executing a command. A CommandResult
 * contains a string description of a result that will be used by the
 * Ui to display to the user. As CommandResult is the final stage of 
 * executing a command, an IllegalStateException will be thrown when 
 * executing a CommandResult.
 * 
 */
public class CommandResult extends Command {
    
    /** String representation of a result after executing. */
    public final String commandResult;
    
    /** 
     * Constructor for the CommandResult.
     * 
     * @param commandResult A String description of a command result after
     *                      a command is being executed. 
     */
    public CommandResult(String commandResult) {
        this.commandResult = commandResult;
    }
    
    public String getCommandResult() {
        return this.commandResult;
    }
    
    @Override
    public CommandResult execute() {
        throw new IllegalStateException("Cannot execute a Command Result");
    } 
}