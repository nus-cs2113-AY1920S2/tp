package seedu.duke;

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
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints the welcome message for the user.
     */

    public void displayWelcomeMessage() {

    }

    public void showResult(){

    }

}
