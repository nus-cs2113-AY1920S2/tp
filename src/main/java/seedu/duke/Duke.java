package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exceptions.FileCorruptedException;
import seedu.duke.exceptions.IndexNotIntegerException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.exceptions.InvalidIndexException;
import seedu.duke.exceptions.NoFieldCommandException;
import seedu.duke.exceptions.PidEmptyException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.parser.Parser;
import seedu.duke.record.Appointment;
import seedu.duke.record.Patient;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.PatientList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static int patientIndexNumber;
    public static int appointmentIndexNumber;

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private static PatientList patientList;
    private static AppointmentList appointmentList;

    /**
     * This constructor initializes the other ui, parser and storage classes to be used in the java.duke.Duke program.
     *
     * @see Ui
     * @see Parser
     * @see Storage
     */
    public Duke() {
        patientIndexNumber = 0;
        appointmentIndexNumber = 0;
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        ui.printHello();
    }


    /**
     * This method loads any previous patient particulars and its respective appointment details
     * from storage if any, or creates a new one if its a new entry.
     *
     * @see PatientList
     * @see Storage
     */
    public void startup() {
        List<Patient> patientListToLoad = null;
        List<Appointment> appointmentListToLoad = null;
        try {
            patientListToLoad = storage.loadSavedPatients();
        } catch (FileNotFoundException | FileCorruptedException e) {
            patientListToLoad = new ArrayList<>();
        } finally {
            patientList = new PatientList(patientListToLoad);
        }
        try {
            appointmentListToLoad = storage.loadSavedAppointments();
        } catch (FileNotFoundException | ParseException | FileCorruptedException e) {
            appointmentListToLoad = new ArrayList<>();
        } finally {
            appointmentList = new AppointmentList(appointmentListToLoad);
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        startup();
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = in.nextLine();

                Command c = parser.parseCommand(fullCommand);
                assert c != null;
                c.execute(ui, storage);
                isExit = c.isExit();

            } catch (UnknownCommandException | InvalidIndexException | IndexNotIntegerException
                    | NoFieldCommandException | PidEmptyException | InvalidFormatException e) {

                ui.showExceptionError(e.getLocalizedMessage());

            } catch (IOException e) {
                ui.showIoExceptionError();

            } catch (NoSuchElementException e) {
                break;

            } catch (ParseException e) {
                System.out.println("Please fill in a valid date in the right format: dd/mm/yyyy and/or "
                        + "a valid time in the 24 hour format: hhmm");
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
