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
    private final static String logo =
            "  _____   _        _     __  __                                         \n"
            + " |  __ \\ (_)      | |   |  \\/  |                                        \n"
            + " | |  | | _   ___ | |_  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \n"
            + " | |  | || | / _ \\| __| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\n"
            + " | |__| || ||  __/| |_  | |  | || (_| || | | || (_| || (_| ||  __/| |   \n"
            + " |_____/ |_| \\___| \\__| |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|   \n"
            + "                                                       __/ |            \n"
            + "                                                      |___/             ";;

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
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!! Already create a new one for you!!!");
        }
    }

//    public static void main(String[] args) {
//
//        Logger logger = Logger.getLogger(Duke.class.getName());
//
//        LogManager.getLogManager().reset();
//        logger.setLevel(Level.ALL);
//
//        //Console Handler - What appears in the console
//        ConsoleHandler consoleHandler = new ConsoleHandler();
//        consoleHandler.setLevel(Level.INFO);
//        logger.addHandler(consoleHandler);
//
//        //File Handler - What appears in log file
//        try {
//            FileHandler fileHandler = new FileHandler("DukeLogger.log");
//            fileHandler.setLevel(Level.FINE);
//            logger.addHandler(fileHandler);
//        } catch (IOException e) {
//            logger.log(Level.SEVERE, "File logger error.", e);
//        }
//
//        logger.log(Level.INFO, "Starting Diet Manager");
//
//        FoodNutritionInfo foodNutritionInfo = new FoodNutritionInfo();
//        Profile profile = new Profile();
//        UI ui = new UI();
//
//        //Conduct checks using assert
//        assert !ui.isExitStatus();
//        assert !profile.isProfileExist();

    public static void main(String[] args) {
        new Duke("data/user info.txt").run();
    }

    public static void welcome() {
        System.out.println(logo);
        System.out.println("Welcome to Diet Manager! How may I assist you today?");
    }

    public static void handleCommands() {
        Command command = parser.getCommand();
        while(!command.isExit()){
            command.execute(personList);
            storage.saveToFile(personList);
            ui.showResult();
            command = parser.getCommand();
        }
//        logger.log(Level.INFO, "Exiting Diet Manager");
    }

    public static void exit() {
        System.out.println("Thank you and see you again soon!");
    }

    public void run() {
        welcome();
        handleCommands();
        exit();
    }
}

