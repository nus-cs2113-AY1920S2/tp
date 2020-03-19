package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;
import seedu.duke.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Duke {

    private static Budget myBudget = new Budget();
    private static ShoppingList items = new ShoppingList();
    private static Scanner in = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String DEFAULT_FILEPATH = "Logger.log";
    private static final String LOG_FILE_ERROR = "Logging to file unsuccessful";
    private static final String SHOCO_LOGO =
                       "\n      HELLO! I'm SHOCO. Your digital shopping list!"
                    +  "\n                                         __"
                    + "\n                                        /__\\"
                    + "\n                                       /"
                    + "\n        ______________________________/\n"
                    + "       |     |     |     |    |      /\n"
                    + "       |____ |____ |____ |____|_____/\n"
                    + "       |     |     |     |    |    /\n"
                    + "       |____ |____ |____ |____|___/\n"
                    + "       |     |     |     |    |  /\n"
                    + "       |____ |____ |____ |____|_/\n"
                    + "       |____ |____ |____ |___ |/\n"
                    + "                               \\"
                    + "\n"
                    + "                                \\"
                    + "\n"
                    + "                                /"
                    + "\n"
                    + "        _______________________/"
                    + "\n          /\\              /\\"
                    + "\n          \\/              \\/\n";



    /**
     * Main entry-point for the java.duke.Duke application.
     *
     */
    public static void main(String[] args) {
        Duke.setUpLogger();
        new Duke().run();
    }

    /**
     * Sets up the logger to log to the console and a file.
     */
    private static void setUpLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler console = new ConsoleHandler();
        console.setLevel(Level.SEVERE);
        LOGGER.addHandler(console);
        File logFile = new File(DEFAULT_FILEPATH);
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileHandler logHandler = new FileHandler(DEFAULT_FILEPATH);
            logHandler.setLevel(Level.INFO);
            LOGGER.addHandler(logHandler);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,LOG_FILE_ERROR);
        }
    }

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Prints the welcome message.
     */
    private void start() {
        LOGGER.log(Level.INFO,"Application starting.");
        System.out.println(SHOCO_LOGO);
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        System.out.println("BYE");
        LOGGER.log(Level.INFO,"Application shutting down");
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            System.out.println("\nEnter command:");
            String userCommandText = readCommand();
            command = new Parser().parseCommand(userCommandText);
            assert command != null : "Command should have been initialised";
            executeCommand(command);
            System.out.println(command.feedbackToUser);
        } while (!command.isExit);
    }

    /**
     * Reads a non-empty input from the user.
     *
     * @return non-empty input
     */
    public String readCommand() {
        String input = "";
        while (input.isEmpty()) {
            input = in.nextLine();
            input = input.trim();
        }
        return input;
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private void executeCommand(Command command) {
        try {
            command.setData(items,myBudget);
            command.execute();
            assert command.feedbackToUser != null : "Result should have been initialised";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
