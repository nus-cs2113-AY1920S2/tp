package seedu.atas;

import common.Messages;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Prints a line divider.
     */
    public void printDividerLine() {
        out.println(Messages.DIVIDER);
    }

    /**
     * Prints welcome messages.
     */
    public void printWelcomeMessage() {
        out.println("Hello from\n" + Messages.LOGO);
        out.println(Messages.HELP_FORMAT_MESSAGE);
        printDividerLine();
    }

    /**
     * Prints a prompt to user and returns the next line of user input.
     * @return String of user input
     */
    public String getUserInput() {
        out.print("> ");
        return in.nextLine().trim();
    }

    /**
     * Prints all messages with a newline in between each message.
     * @param messages strings to be shown to the user
     */
    public void showToUser(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }
}
