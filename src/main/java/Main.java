import commands.ReservationCommand;
import exceptions.InvalidLoadException;
import exceptions.ReservationException;
import menu.Menu;
import report.LoadDish;
import report.LoadReservation;

import exceptions.InvalidFilePathException;
import exceptions.StockReadWriteException;
import report.LoadStock;
import reservation.Reservation;
import reservation.ReservationList;
import sales.Sales;
import stock.Stock;
import ui.Ui;
import utils.CommandParser;
import utils.LoggerUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static utils.Constants.LOG_FOLDER;
import static utils.Constants.DEFAULT_STORAGE_FILEPATH;

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
        this.ui = new Ui();
        this.sales = new Sales();

        // load the Reservations from the "report.txt" file
        try {
            this.reservations = new ReservationList(
                    LoadReservation.getInstance(DEFAULT_STORAGE_FILEPATH).loadFileReservations());
        } catch (IOException e) {
            ui.showMessage("Fails to load in the list from the file... Creating a new empty reservation list.");
            this.reservations = new ReservationList();
        } catch (ReservationException e) {
            this.reservations = new ReservationList();
        }

        // load the Dishes from the "report.txt" file
        try {
            this.menu = LoadDish.getInstance("report.txt").readDishes();
        } catch (InvalidLoadException e) {
            ui.showMessage("Error loading from file, creating new menu");
            this.menu = new Menu();
        } catch (FileNotFoundException e) {
            this.menu = new Menu();
        }
        
        // load the Stocks from the "report.txt" file
        LoadStock ls = Stock.getStockLoader();
        try {
            ls.loadStockData(stock);
        } catch (InvalidFilePathException | StockReadWriteException e) {
            ui.showMessage(e.getMessage());
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
        
        ui.showWelcomeMessage();
    }
    
    /** Read user's input, parse it into readable command format and execute it. */
    private void runCommandUntilExit() {
        
        while (true) {
            System.out.println("Input next command:");
            String userInput = ui.getUserCommand();
            new CommandParser().parseCommand(userInput, this.menu, 
                    this.stock, this.reservations, this.sales, this.ui);
        }
    }
    
    
    /** Exits the program with a goodbye message. */
    private void exit() {
        System.exit(0);
    }    

}