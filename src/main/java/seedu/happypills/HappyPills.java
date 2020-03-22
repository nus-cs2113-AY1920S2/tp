package seedu.happypills;

import seedu.happypills.commands.Command;
import seedu.happypills.data.PatientList;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.parser.Parser;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.TextUi;

import java.io.FileNotFoundException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import java.util.Scanner;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class HappyPills {
    private TextUi ui;
    private PatientList patients;
    private PatientMap tests;
    private static final Logger logger = Logger.getLogger(HappyPills.class.getName());
    private static final String DATA_FILEPATH = "data/data.txt";

    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public HappyPills() {
        ui = new TextUi();
        //patients = new PatientList();
        try {
            logger.info("loading patient data from file.");
            patients = Storage.loadFromFile(DATA_FILEPATH);
        } catch (FileNotFoundException e) {
            logger.info("No patient data file was found.");
            patients = new PatientList();
            tests = new PatientMap();
        }
    }

    /**
     * Sets up the logging configuration for the main program.
     */
    public void logSetup() {
        LogManager.getLogManager().reset();
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        ch.setFormatter(new SimpleFormatter());
        logger.addHandler(ch);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     * @param args I dont know.
     */
    public static void main(String[] args) {
        new HappyPills().run();
    }

    /**
     * Runs the program until termination.
     */
    private void run() {
        logSetup();

        ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            logger.info("going to start processing");
            String fullCommand = in.nextLine();
            System.out.println(TextUi.DIVIDER);
            try {
                Command c = Parser.parse(fullCommand);
                String message = c.execute(tests);
                if (!message.isEmpty()) {
                    System.out.println(message);
                }
            } catch (HappyPillsException hpe) {
                System.out.println(hpe.getMessage());
                System.out.println(TextUi.DIVIDER);
                logger.info(hpe.getMessage());
            }

            logger.info("end of processing");
        }
    }
}