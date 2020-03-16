package seedu.happypills;

import seedu.happypills.commands.Command;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.parser.Parser;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.TextUi;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class HappyPills {
    private TextUi ui;
    private PatientList patients;

    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public HappyPills() {
        ui = new TextUi();
        //patients = new PatientList();
        try {
            patients = Storage.loadFromFile("data/data.txt");
        } catch (FileNotFoundException e) {
            patients = new PatientList();
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new HappyPills().run();
    }

    /**
     * Runs the program until termination.
     */
    private void run() {
        Level logLevel = Level.INFO;
        Logger logger = Logger.getLogger(HappyPills.class.getName());

        ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            logger.log(logLevel, "going to start processing");
            try {
                String fullCommand = in.nextLine();
                System.out.println(TextUi.DIVIDER);
                Command c = Parser.parse(fullCommand);
                String message = c.execute(patients);
                if (message != null) {
                    System.out.println(message);
                }
            } catch (HappyPillsException hpe) {
                System.out.println(hpe.getMessage());
                System.out.println(TextUi.DIVIDER);
            }

            logger.log(logLevel, "end of processing");
        }
    }
}