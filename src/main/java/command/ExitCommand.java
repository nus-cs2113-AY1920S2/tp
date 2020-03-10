package command;

/** 
 * Represents the 'Exit' functionality of a command. This command
 * serves to exit the program upon executed.  
 * 
 */
public class ExitCommand extends Command {
    
    /** Returns true if a Command is an ExitCommand. */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
    
    @Override
    public CommandResult execute() {
        return new CommandResult("Program Exited");
    }
}

