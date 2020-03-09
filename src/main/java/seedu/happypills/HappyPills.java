package seedu.happypills;

import seedu.happypills.commands.Command;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.parser.Parser;
import seedu.happypills.ui.TextUi;

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
        patients = new PatientList();
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
        ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String fullCommand = in.nextLine();
                System.out.println(ui.DIVIDER);
                Command c = Parser.parse(fullCommand);
                c.execute(patients);
            } catch (HappyPillsException hpe) {
                System.out.println(hpe.getMessage());
                System.out.println(ui.DIVIDER);
            }
        }
    }
}