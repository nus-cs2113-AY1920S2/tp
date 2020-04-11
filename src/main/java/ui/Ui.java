package ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static utils.Constants.LOG_FOLDER;

/**
 * TextUi of the application. 
 *
 */
public class Ui {
    
    private final Scanner in;
    private final PrintStream out;
    private final String ls = System.lineSeparator();

    private static final Logger LOGGER = Logger.getLogger(Ui.class.getName());
    private static final String FILE_PATH = LOG_FOLDER + "Ui.log";
                                          
    public Ui() {
        this(System.in, System.out);
    }
    
    public void showWelcomeMessage() {
        System.out.println("______  ______  _____ ______    ___  __  __ ______        __  _______  ______");
        System.out.println("||    ||||      \\       ||     / ||  ||  || ||    |      / || ||    ||   ||");       
        System.out.println("||    ||||       \\      ||    /  ||  ||  || ||    |     /  || ||    ||   ||");
        System.out.println("||__ /  ||___     \\___  ||   /___||  ||  || ||__ /     /___|| ||    ||   ||");
        System.out.println("||   \\\\ ||           \\  ||  /    ||  ||  || ||   \\\\   /    || ||    ||   ||");
        System.out.println("||    \\\\||____   _____\\ || /     ||   \\__/  ||    \\\\ /     Daily Report v2.1");       
    }
    
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Sets up the logger. 
     * Calls once at the start of the program.
     *
     * @throws IOException When logger set up failed.
     */
    public static void setLogger() throws IOException {
        Locale.setDefault(Locale.UK);
        
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        FileHandler fileHandler = new FileHandler(FILE_PATH, true); // let it append
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setEncoding("UTF-8");
        LOGGER.addHandler(fileHandler);
    }
    
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /** Sends failing messages to user and records into the logger if the logging set up fails. */
    public void displayLoggingSetUpFailMessage() {
        LOGGER.info("Logging set up failed!");
        System.out.println("Logging set up failed!");
    }
}
