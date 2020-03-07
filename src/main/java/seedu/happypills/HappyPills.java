package seedu.happypills;

import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;
import java.util.Scanner;

public class HappyPills {
    private TextUi ui;
    public HappyPills() {
        try{
            ui = new TextUi;
        } catch (HappyPillsException hpe) {
            //patients = new PatientsList();
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new HappyPills().run();
    }

    private void run() {
        ui.showWelcomeMessage();
    }
}
