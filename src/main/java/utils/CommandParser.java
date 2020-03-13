package utils;

import java.io.IOException;

import commands.AddReservationCommand;
import commands.AddStockCommand;
import commands.DeleteStockCommand;
import commands.InvalidStockCommandException;
import commands.ListReservationCommand;
import commands.ListStockCommand;
import commands.QuitCommand;
import commands.VoidReservationCommand;

import menu.Menu;
import report.ReportWriter;
import reservation.ReservationList;
import stock.Stock;

public class CommandParser {
    
    /** Pass the parsed user input into readable formats to be processed
     * by either a menu, stock or a reservation.
     *
     */
    public void parseCommand(String command, Menu menu, 
            Stock stock, ReservationList reservations) {
        
        if (command.equals("bye")) {
            try {
                new ReportWriter(stock,reservations,menu).writeToFile();
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
            new QuitCommand().execute();
        }
        
        String[] commands = command.split(";",2);
        String[] splitCommands = commands[0].split(" ",2);
        
        if (splitCommands[0].equals("add")) {
            if (splitCommands[1].equals("dish")) {
                // Add dish.
                menu.addDish(commands[1]);
                successfulCommand();
            } else if (splitCommands[1].equals("stock")) {
                // Add stock.
                try {
                    new AddStockCommand(commands[1]).execute(stock);
                    successfulCommand();
                } catch (InvalidStockCommandException | IllegalStateException e) {                    
                    errorCommand();
                    printErrorMessage(e.getMessage());
                }
            } else if (splitCommands[1].equals("reservation")) {
                // Add reservation.
                new AddReservationCommand(commands[1]).execute(reservations);
                successfulCommand();
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("delete")) {
            if (splitCommands[1].equals("dish")) {
                // Delete dish.
                String newcomm = commands[1].substring(3, commands[1].length() - 1);
                menu.deleteDish(newcomm);
                successfulCommand();
            } else if (splitCommands[1].equals("stock")) {
                // Delete stock.
                try {
                    new DeleteStockCommand(commands[1]).execute(stock);
                    successfulCommand();
                } catch (InvalidStockCommandException | IllegalStateException e) {                    
                    errorCommand();
                    printErrorMessage(e.getMessage());
                }
            } else if (splitCommands[1].equals("reservation")) {
                // Delete reservation.
                new VoidReservationCommand(commands[1]).execute(reservations);
                successfulCommand();
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("list")) {
            if (splitCommands[1].equals("dish")) {
                // List dish.
                menu.printDishes();
                successfulCommand();
            } else if (splitCommands[1].equals("stock")) {
                // List stock.
                new ListStockCommand().execute(stock);
                successfulCommand();
            } else if (splitCommands[1].equals("reservation")) {
                // List reservation.
                new ListReservationCommand().execute(reservations);
                successfulCommand();
            } else {
                errorCommand();
            }
        } else {
            errorCommand();
        }
    }
    
    public static void errorCommand() {
        System.out.println("Incorrect command");
    }
    
    /** 
     * Displays an error message to the user. 
     * */
    public static void printErrorMessage(String message) {
        System.out.println("============================================================"
                + "================================================================");
        
        System.out.println(message);
        
        System.out.println("============================================================"
                + "================================================================");
    }
    
    public static void successfulCommand() {
        System.out.println("The command has been successfully executed.");
    }
}
