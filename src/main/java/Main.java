import commands.ReservationCommand;
import exceptions.InvalidFilePathException;
import exceptions.StockReadWriteException;
import exceptions.ReservationException;
import report.LoadReservation;
import reservation.Reservation;
import sales.Sales;
import menu.Menu;
import reservation.ReservationList;
import stock.Stock;
import stock.LoadStock;
import ui.Ui;
import utils.CommandParser;
import utils.LoggerUtils;

import java.io.IOException;

import static utils.Constants.LOG_FOLDER;

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
    private Sales sales;

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
        // this.reservations = new ReservationList();
        this.ui = new Ui();
        this.sales = new Sales();

        try {
            this.reservations = new ReservationList(LoadReservation.getInstance("report.txt").loadFileReservations());
        } catch (IOException e) {
            ui.showMessage("Fails to load in the list from the file...");
            this.reservations = new ReservationList();
        } catch (ReservationException e) {
            this.reservations = new ReservationList();
        }

        // set up the logger
        LoggerUtils.createLogFolder(LOG_FOLDER);
        
        try {
            Ui.setLogger();
            Reservation.setLogger();
            ReservationCommand.setLogger();
            ReservationList.setLogger();
        } catch (IOException e) {
            ui.displayLoggingSetUpFailMessage();
        }
        
        
        // load data from report.txt
        LoadStock sl = Stock.getStockLoader();
        try {
            sl.loadStockData(stock);
        } catch (InvalidFilePathException | StockReadWriteException e) {
            ui.showMessage(e.getMessage());
        }
        
        ui.showWelcomeMessage();
    }
    
    /** Read user's input, parse it into readable command format and execute it. */
    private void runCommandUntilExit() {
        
        while (true) {
            System.out.println("Input next command:");
            String userInput = ui.getUserCommand();
            new CommandParser().parseCommand(userInput, this.menu, this.stock, this.reservations, this.sales, this.ui);
        }
    }
    
    
    /** Exits the program with a goodbye message. */
    private void exit() {
        System.exit(0);
    }    

}
