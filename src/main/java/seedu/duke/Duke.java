package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    /**
     * Prints the greeting message when ESC is started.
     */
    public static void printGreeting() {
        String greeting = "";
        String logo = " ______  _____  _____\n"
                    + "|  ____|/ ____|/ ____|\n"
                    + "| |__  | (___ | |\n"
                    + "|  __|  \\___ \\| |\n"
                    + "| |____ ____) | |____\n"
                    + "|______|_____/ \\_____|\n";
        greeting += "Hello from\n" + logo;
        greeting += "What is your name?";

        System.out.println(greeting);
    }

    /**
    * Main method.
    */
    public static void main(String[] args) {
        printGreeting();

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
