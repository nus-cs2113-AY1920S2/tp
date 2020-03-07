package seedu.happypills;

//import seedu.happypills.ui.TextUi;

import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class HappyPills {
    private TextUi ui;

    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public HappyPills() {
        ui = new TextUi();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new HappyPills().run();
    }

    private void run() {
        ui.printWelcomeMessage();
    }
}
