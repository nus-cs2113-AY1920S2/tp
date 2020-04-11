package seedu.dietmanager.ui;

import java.util.Scanner;

import static seedu.dietmanager.commons.core.MessageBank.CREATE_PROFILE_MESSAGE;
import static seedu.dietmanager.commons.core.MessageBank.EXIT_APP_MESSAGE;
import static seedu.dietmanager.commons.core.MessageBank.FILE_ERROR_MESSAGE;
import static seedu.dietmanager.commons.core.MessageBank.FUNCTION_LIST;
import static seedu.dietmanager.commons.core.MessageBank.INVALID_COMMAND_MESSAGE;
import static seedu.dietmanager.commons.core.MessageBank.INVALID_FORMAT_MESSAGE;
import static seedu.dietmanager.commons.core.MessageBank.INVALID_GENDER_MESSAGE;
import static seedu.dietmanager.commons.core.MessageBank.INVALID_INDEX;
import static seedu.dietmanager.commons.core.MessageBank.LOGO;
import static seedu.dietmanager.commons.core.MessageBank.WELCOME_MESSAGE;

/**
 * UI is the public class responsible for managing all user interface functions.
 */

public class UI {

    private Scanner sc;

    /**
     * The exit status determining whether to close the application.
     */

    private boolean exitStatus;

    /**
     * Constructs the UI object.
     */

    public UI() {
        this.exitStatus = false;
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message for the user.
     */

    public void displayWelcomeMessage() {
        showMessage(LOGO, WELCOME_MESSAGE);
    }

    public void displayHelpMenu() {
        showMessage(FUNCTION_LIST);
    }

    public void displayExitMessage() {
        showMessage(EXIT_APP_MESSAGE);
    }

    public void displayFileErrorMessage() {
        showMessage(FILE_ERROR_MESSAGE);
    }

    public void displayInvalidCommandMessage() {
        showMessage(INVALID_COMMAND_MESSAGE);
    }

    public void displayInvalidFormatMessage() {
        showMessage(INVALID_FORMAT_MESSAGE);
    }

    public void displayIndexOutOfBoundMessage() {
        showMessage(INVALID_INDEX);
    }

    public void displayInvalidGenderMessage() {
        showMessage(INVALID_GENDER_MESSAGE);
    }

    public void displayCreateProfileMessage() {
        showMessage(CREATE_PROFILE_MESSAGE);
    }

    /**
     * Returns the exit status.
     *
     * @return the exit status.
     */

    public boolean isExitStatus() {
        return this.exitStatus;
    }

    /**
     * Updates the exit status.
     *
     * @param update the updated exit status.
     */

    public void setExitStatus(boolean update) {
        this.exitStatus = update;
    }

    /**
     * Returns the next line of user input.
     *
     * @return the next line of user input.
     */

    public String readInput() {
        return this.sc.nextLine();
    }

    public boolean hasInput() {
        return this.sc.hasNextLine();
    }

    /**
     * Printer to print out strings of messages to user.
     *
     * @param message String of message to be shown to user
     */

    public void showMessage(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

}
