package seedu.happypills;

import seedu.happypills.logic.commands.Command;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.logic.parser.Parser;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.Messages;
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
    public static Scanner scanner;
    private TextUi ui;
    private PatientMap patients;
    private AppointmentMap appointments;
    private PatientRecordMap patientRecords;
    private static final Logger logger = Logger.getLogger(HappyPills.class.getName());


    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public HappyPills() {
        patients = new PatientMap();
        appointments = new AppointmentMap();
        patientRecords = new PatientRecordMap();
        scanner = new Scanner(System.in);
        logSetup();
        try {
            patients = Storage.loadPatientsFromFile(Storage.PATIENT_FILEPATH);
            logger.info("    Patient loaded from file.\n");
        } catch (FileNotFoundException e) {
            logger.info("   No patients in file.\n");
        }
        try {
            appointments = Storage.loadAppointmentFromFile(Storage.APPOINTMENT_FILEPATH, patients);
            logger.info("    Appointment loaded from file.\n");
        } catch (FileNotFoundException e) {
            logger.info("   No appointment in file.\n");
        }
        try {
            patientRecords = Storage.loadPatientRecordFromFile(Storage.PATIENT_RECORD_FILEPATH,patients);
            logger.info("    Patient Records loaded from file.\n");
        } catch (FileNotFoundException e) {
            logger.info("   No patient record in file.\n");
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
     * @param args arguments
     */
    public static void main(String[] args) {
        new HappyPills().run();
    }

    /**
     * Runs the program until termination.
     */
    private void run() {
        TextUi.printWelcomeMessage();

        while (scanner.hasNextLine()) {
            logger.info("going to start processing");
            String fullCommand = scanner.nextLine();
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
            System.out.println(Messages.MESSAGE_INCOMPLETE_COMMAND);
            System.out.println(ui.DIVIDER);
        }
        return message;
    }
}