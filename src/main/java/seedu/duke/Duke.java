package seedu.duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

public class Duke {
    private static Person currentUser;
    private static Parser parser;
    private static Storage storage;
    private static UI ui;
    private static PersonList personList;
    private static FoodNutritionInfo foodNutritionInfo;

    /**
     * Begins the application and creates the relevant objects required for the application to function.
     */

    public Duke(String filePath) {
        foodNutritionInfo = new FoodNutritionInfo();
        personList = new PersonList();
        ui = new UI();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            storage.loadFileData(personList);
        } catch (FileNotFoundException ignored) {
            System.out.print("");
        }
    }

    public static void main(String[] args) {
        new Duke("data/userInfo.txt").run();
    }

    /**
     * Program shows greetings to user.
     */
    public static void welcome() {
        String logo = "  _____   _        _     __  __                                         \n"
                + " |  __ \\ (_)      | |   |  \\/  |                                        \n"
                + " | |  | | _   ___ | |_  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \n"
                + " | |  | || | / _ \\| __| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\n"
                + " | |__| || ||  __/| |_  | |  | || (_| || | | || (_| || (_| ||  __/| |   \n"
                + " |_____/ |_| \\___| \\__| |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|   \n"
                + "                                                       __/ |            \n"
                + "                                                      |___/             ";
        System.out.println(logo);
        System.out.println("Welcome to Diet Manager! How may I assist you today? ");
    }

    /**
     * Program gets a command and execute it.
     */
    public static void handleCommands() {
        Command command = parser.getCommand();
        while (!command.isExit()) {
            command.execute(personList);
            storage.saveToFile(personList);
            ui.showResult();
            command = parser.getCommand();
        }
    }

    public static void exit() {
        System.out.println("Thank you and see you again soon!");
    }

    /**
     * The program repeats the action in the method until exits.
     */
    public void run() {
        welcome();
        handleCommands();
        exit();
    }
}

