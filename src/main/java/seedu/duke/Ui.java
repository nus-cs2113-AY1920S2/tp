package seedu.duke;

import java.util.Scanner;

public class Ui {
    Scanner in;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints a line divider.
     */
    public void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints welcome messages.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What is your name?");
        System.out.println(getUserInput());
        printDividerLine();
    }

    /**
     * Prints exit messages.
     */
    public void printGoodbyeMessage() {
        System.out.println("Exiting DUKE\n" + LOGO);
    }

    /**
     * Prints a prompt to user and returns the next line of user input.
     * @return String of user input
     */
    public String getUserInput() {
        System.out.println(">");
        return in.nextLine().trim();
    }
}
