package utils;

import commands.AddDishCommand;
import commands.AddStockCommand;
import commands.AddReservationCommand;
import commands.DeleteDishCommand;
import commands.DeleteStockCommand;
import commands.MarkReservationCommand;
import commands.VoidReservationCommand;
import commands.ListDishCommand;
import commands.ListStockCommand;
import commands.ListReservationCommand;
import commands.ListServedCommand;
import commands.ListUnservedCommand;
import commands.SearchStockCommand;
import commands.SearchReservationCommand;
import commands.HelpCommand;
import commands.QuitCommand;
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

        
        if (command.equals("bye")) {
            try {
                new ReportWriter(stock, reservations, menu).writeToFile();
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
            new QuitCommand().execute();
        }

        String[] commands = command.split(";", 2);
        String[] splitCommands = commands[0].split(" ", 2);

        if (splitCommands[0].equals("add")) {
            if (splitCommands[1].equals("dish")) {
                // Add dish.
                AddDishCommand.addDish(commands[1]);
                successfulCommand();
            } else if (splitCommands[1].equals("stock")) {
                // Add stock.
                try {
                    new AddStockCommand(commands[1]).execute(stock);
                    successfulCommand();

                } catch (IllegalStateException | InvalidStockCommandException e) {

                    errorCommand();
                    printErrorMessage(e.getMessage());
                }
            } else if (splitCommands[1].equals("reservation")) {
                // Add reservation.
                new AddReservationCommand(commands[1]).execute(reservations, ui);
                successfulCommand();
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("delete")) {
            if (splitCommands[1].equals("dish")) {
                // Delete dish.
                String newcomm = commands[1].substring(3, commands[1].length() - 1);
                DeleteDishCommand.deleteDish(newcomm);
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
                new VoidReservationCommand(commands[1]).execute(reservations, ui);
                successfulCommand();
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
                successfulCommand();
            } else if (splitCommands[1].equals("stock")) {
                // List stock.
                new ListStockCommand().execute(stock);
                successfulCommand();
            } else if (splitCommands[1].equals("reservation")) {
                // List all reservation.
                new ListReservationCommand().execute(reservations, ui);
                successfulCommand();
            } else if (splitCommands[1].equals("served reservation")) {
                // List served reservation.
                new ListServedCommand().execute(reservations, ui);
                successfulCommand();
            } else if (splitCommands[1].equals("unserved reservation")) {
                // List unserved reservation.
                new ListUnservedCommand().execute(reservations, ui);
                successfulCommand();
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("search")) {
            if (splitCommands[1].equals("stock")) {
                // Search stock.
                try {
                    new SearchStockCommand(commands[1]).execute(stock);
                    successfulCommand();
                } catch (InvalidStockCommandException e) {
                    errorCommand();
                    printErrorMessage(e.getMessage());
                }
            } else if (splitCommands[1].equals("reservation")) {
                new SearchReservationCommand(commands[1]).execute(reservations, ui);
            } else {
                errorCommand();
            }
        } else if (splitCommands[0].equals("help")) {
            new HelpCommand().execute();
        } else if (splitCommands[0].equals("sell")) {
            sales.addSale(commands[1]);
        } else if (splitCommands[0].equals("profit")) {
            sales.calculateProfit();
        } else if (splitCommands[0].equals("popular")) {
            sales.mostPopularDish();
        } else {
            errorCommand();
        }
    }



    public static void errorCommand() {
        System.out.println("Incorrect command");
    }

    /**
     * Displays an error message to the user.
     */
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

