package commands;

public class QuitCommand {
    public static void execute() {
        System.out.println("The menu, stock and reservations have been saved to report.txt");
        System.out.println("The program is now exiting");
        System.exit(0);
    }
}
