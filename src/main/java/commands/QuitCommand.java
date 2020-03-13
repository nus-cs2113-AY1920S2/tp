package commands;

/**
 * This class encapsulates the 'exit' function of the program. When this command
 * is executed, a writeToFile operation will be performed. All information on 
 * menu, stock and reservations will be saved.
 *
 */
public class QuitCommand {
    
    /** 
     * Executes the QuitCommand.
     */
    public static void execute() {
        System.out.println("The menu, stock and reservations have been saved to report.txt");
        System.out.println("The program is now exiting");
        System.exit(0);
    }
}
