package seedu.happypills;

import seedu.happypills.logic.commands.Command;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.logic.parser.Parser;
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
    private PatientMap patients;
    private AppointmentMap appointments;
    private PatientRecordMap patientRecords;
    private static final Logger logger = Logger.getLogger(HappyPills.class.getName());


    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public HappyPills() {
        ui = new TextUi();
        appointments = new AppointmentMap();
        patients = new PatientMap();
        patientRecords = new PatientRecordMap();
        try {
            logger.info("loading patient data from file.");
            patients = Storage.loadPatientsFromFile(Storage.PATIENT_FILEPATH);
        } catch (FileNotFoundException e) {
            logger.info("No patient data file was found.");
        }
        try {
            logger.info("loading appointment data from file.");
            appointments = Storage.loadAppointmentFromFile(Storage.APPOINTMENT_FILEPATH, patients);
        } catch (FileNotFoundException e) {
            logger.info("No appointment data file was found.");
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
            String message = getCommandType(fullCommand);
            if (!message.isEmpty()) {
                System.out.println(message);
            }
            logger.info("end of processing");
        }
    }

    private String getCommandType(String fullCommand) {
        String message = "";
        try {
            Command c = Parser.parse(fullCommand);
            message = c.execute(patients, appointments, patientRecords);
        } catch (HappyPillsException hpe) {
            System.out.println(hpe.getMessage());
            System.out.println(TextUi.DIVIDER);
            logger.info(hpe.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    Command is incomplete. Please use the help command.\n"
                    + TextUi.DIVIDER);
        }
        return message;
    }
}