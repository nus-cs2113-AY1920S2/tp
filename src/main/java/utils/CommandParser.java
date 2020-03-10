package utils;
import commands.*;
import dish.*;
import menu.*;
import report.*;
import stock.*;
import reservation.*;

import java.io.IOException;

public class CommandParser {
    public static void parseCommand(String command,Menu menu, Stock stock, ReservationList reservations) {
        if (command.equals("bye")){
            try {
                new ReportWriter(stock,reservations,menu).writeToFile();
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
            new ExitCommand().execute();
        }
        String[] commands = command.split(";",2);
        String[] split_commands = commands[0].split(" ",2);
        if (split_commands[0].equals("add")) {
            if (split_commands[1].equals("dish")) {
                //Add dish
                menu.addDish(commands[1]);
            } else if (split_commands[1].equals("stock")){
                //Add stock
               new Parser().parseCommand(commands[1]);
            } else if (split_commands[1].equals("reservation")){
                //Add reservation
                new AddReservationCommand(commands[1]).execute(reservations);
            } else {
                errorCommand();
            }
        } else if (split_commands[0].equals("delete")){
            if (split_commands[1].equals("dish")) {
                //delete dish
                menu.deleteDish(commands[1]);
            } else if (split_commands[1].equals("stock")){
                //delete stock
                new Parser().parseCommand(commands[1]);
            } else if (split_commands[1].equals("reservation")){
                //delete reservation
                new VoidReservationCommand(commands[1]).execute(reservations);
            } else {
                errorCommand();
            }
        } else if (split_commands[0].equals("list")) {
            if (split_commands[1].equals("dish")) {
                //list dish
                menu.printDishes();
            } else if (split_commands[1].equals("stock")){
                //list stock
                new Parser().parseCommand(commands[1]);
            } else if (split_commands[1].equals("reservation")){
                //list reservation
                new ListReservationCommand().execute(reservations);
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
    public static void successfulCommand(String string) { System.out.println(string + "has been successfully executed.");}
}
