package seedu.dietmanager.ui;

import java.util.Scanner;

/**
 * UI is the public class responsible for managing all user interface functions.
 */

public class UI {

    /**
     * The exit status determining whether to close the application.
     */

    private boolean exitStatus;

    /**
     * Constructs the UI object.
     */

    public UI() {
        this.exitStatus = false;
    }

    /**
     * Prints the welcome message for the user.
     */

    public void displayWelcomeMessage() {
        System.out.println(MessageBank.LOGO);
        System.out.println(MessageBank.WELCOME_MESSAGE);
    }

    public void displayExitMessage() {
        System.out.println(MessageBank.EXIT_APP_MESSAGE);
    }

    public void displayFileErrorMessage() {
        System.out.println(MessageBank.FILE_ERROR_MESSAGE);
    }

    public void displayInvalidCommandMessage() {
        System.out.println(MessageBank.INVALID_COMMAND_MESSAGE);
    }

    public void displayInvalidFormatMessage() {
        System.out.println(MessageBank.INVALID_FORMAT_MESSAGE);
    }

    public void displayIndexOutOfBoundMessage() {
        System.out.println(MessageBank.INVALID_INDEX);
    }


    public void showExecutionResult(String result) {
        System.out.println(result);
    }

    /**
     * Returns the exit status.
     * @return the exit status.
     */

    public boolean isExitStatus() {
        return this.exitStatus;
    }

    /**
     * Updates the exit status.
     * @param update the updated exit status.
     */

    public void setExitStatus(boolean update) {
        this.exitStatus = update;
    }

    /**
     * Returns the next line of user input.
     * @return the next line of user input.
     */

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Show messages from executing commands.
     * @param message String fo message to be shown to user
     */
    public void showCommandMessage(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

}
