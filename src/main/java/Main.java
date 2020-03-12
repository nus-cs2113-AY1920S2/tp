import menu.Menu;
import reservation.ReservationList;
import stock.Stock;
import ui.Ui;
import utils.CommandParser;

/**
 * Entry point of the application.
 * Initializes the program and takes command from the user.
 * 
 */
public class Main {
    
    private Stock stock;
    private Ui ui;
    private ReservationList reservations;
    private Menu menu;

    /** Driver code for the program. */
    public static void main(String... args) {
        new Main().run(args);
    }
    
    /** Runs the program until the user terminates it. */
    public void run(String[] args) {
        start(args);
        runCommandUntilExit();
        exit();
    }
    
    /** Sets up the required objects and shows a welcome message. */
    public void start(String[] args) {
        this.stock = new Stock();
        this.menu = new Menu();
        this.reservations = new ReservationList();
        this.ui = new Ui();
        ui.showWelcomeMessage();
    }
    
    /** Read user's input, parse it into readable command format and execute it. */
    private void runCommandUntilExit() {                     

        while(true){
            System.out.println("Input next command:");
            String userInput = ui.getUserCommand();
            new CommandParser().parseCommand(userInput,this.menu,this.stock,this.reservations);
            //CommandResult result = executeCommand(command);
        }
    }
    
    /** Executes a command. */
//    private CommandResult executeCommand(Command command) {
//        try {
//            command.setData(this.stock);
//            CommandResult output = command.execute();
//            ui.showMessage(output.getCommandResult());
//            return output;
//        } catch (Exception e) {
//            ui.showMessage(e.getMessage());
//            throw new RuntimeException(e);
//        }
//
//    }
    
    /** Exits the program with a goodbye message. */
    private void exit() {
        System.exit(0);
    }    

}
