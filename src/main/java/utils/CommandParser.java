package utils;

import commands.AddDishCommand;
import commands.AddReservationCommand;
import commands.AddStockCommand;
import commands.ClearReservationCommand;
import commands.ClearStockCommand;
import commands.DeleteDishCommand;
import commands.DeleteStockCommand;
import commands.HelpCommand;
import commands.ListDishCommand;
import commands.ListReservationCommand;
import commands.ListServedCommand;
import commands.ListStockCommand;
import commands.ListUnservedCommand;
import commands.MarkReservationCommand;
import commands.QuitCommand;
import commands.SearchDishCommand;
import commands.SearchReservationCommand;
import commands.SearchStockCommand;
import commands.VoidReservationCommand;
import exceptions.CommandFormatException;
import exceptions.InvalidStockCommandException;
import menu.Menu;
import report.ReportWriter;
import reservation.ReservationList;
import sales.Sales;
import stock.Stock;
import ui.Ui;

import java.io.IOException;

public class CommandParser {

    /**
     * Pass the parsed user input into readable formats to be processed
     * by either a menu, stock or a reservation.
     */
    public void parseCommand(String command, Menu menu,
                             Stock stock, ReservationList reservations, Sales sales, Ui ui) {
        System.out.println("");
        String[] commands = command.split(";", 2);
        String[] splitCommands = commands[0].split(" ", 2);
        
        if (command.equals("bye")) {
            try {
                new ReportWriter(stock, reservations, menu).writeToFile();
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
            new QuitCommand().execute();
        } else if (command.equals("help")) {
            new HelpCommand().execute();

        } else if (command.equals("profit")) {
            sales.calculateProfit();

        } else if (command.equals("popular")) {
            sales.mostPopularDish();

        } else if (commands.length < 2 || splitCommands.length < 2) {
            errorCommand();
        } else if (splitCommands[0].equals("add")) {
            if (splitCommands[1].equals("dish")) {
                // Add dish.
                AddDishCommand.addDish(commands[1]);
            } else if (splitCommands[1].equals("stock")) {
                // Add stock.
                try {
                    new AddStockCommand(commands[1]).execute(stock);
                } catch (IllegalStateException | InvalidStockCommandException e) {

                    errorCommand();
                    printErrorMessage(e.getMessage());
                }
            } else if (splitCommands[1].equals("reservation")) {
                // Add reservation.
                new AddReservationCommand(commands[1]).execute(reservations, ui);
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("delete")) {
            if (splitCommands[1].equals("dish")) {
                // Delete dish.
                DeleteDishCommand.deleteDish(commands[1]);
            } else if (splitCommands[1].equals("stock")) {
                // Delete stock.
                try {
                    new DeleteStockCommand(commands[1]).execute(stock);
                } catch (InvalidStockCommandException | IllegalStateException e) {
                    errorCommand();
                    printErrorMessage(e.getMessage());
                }
            } else if (splitCommands[1].equals("reservation")) {
                // Delete reservation.
                new VoidReservationCommand(commands[1]).execute(reservations, ui);
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("mark")) {
            if (splitCommands[1].equals("reservation")) {
                // Mark reservation as served.
                new MarkReservationCommand(commands[1]).execute(reservations, ui);
            }
        } else if (splitCommands[0].equals("list")) {
            if (splitCommands[1].equals("dish")) {
                // List dish.
                ListDishCommand.printDishes();
            } else if (splitCommands[1].equals("stock")) {
                // List stock.
                new ListStockCommand().execute(stock);
            } else if (splitCommands[1].equals("reservation")) {
                // List all reservation.
                new ListReservationCommand().execute(reservations, ui);
            } else if (splitCommands[1].equals("served reservation")) {
                // List served reservation.
                new ListServedCommand().execute(reservations, ui);
            } else if (splitCommands[1].equals("unserved reservation")) {
                // List unserved reservation.
                new ListUnservedCommand().execute(reservations, ui);
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("search")) {
            if (splitCommands[1].equals("stock")) {
                // Search stock.
                try {
                    new SearchStockCommand(commands[1]).execute(stock);
                } catch (InvalidStockCommandException e) {
                    errorCommand();
                    printErrorMessage(e.getMessage());
                }
            } else if (splitCommands[1].equals("reservation")) {
                // Search reservation
                new SearchReservationCommand(commands[1]).execute(reservations, ui);
            } else if (splitCommands[1].equals("dish")) {
                // Search dish
                SearchDishCommand.searchDish(commands[1]);
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("sell") && splitCommands[1].equals("dish")) {
            sales.addSale(commands[1]);
        } else if (splitCommands[0].equals("clear")) {
            if (splitCommands[1].equals("reservation")) {
                new ClearReservationCommand().execute(reservations, ui);
            } else if (splitCommands[1].equals("stock")) {
                new ClearStockCommand().execute(stock);
            }
        } else {
            errorCommand();
        }
        System.out.println("");
    }



    public static void errorCommand() {
        new CommandFormatException().getMessage();
    }

    /**
     * Displays an error message to the user.
     */
    public static void printErrorMessage(String message) {
        System.out.println("");

        System.out.println(message);

        System.out.println("");
    }

}

