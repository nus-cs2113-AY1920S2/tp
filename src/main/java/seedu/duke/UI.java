package seedu.duke;

import java.util.Scanner;

public class UI {
    public static Scanner in = new Scanner(System.in);

    /**
     * Prints the greeting message when ESC is started.
     */
    public static void showWelcome() {
        String greeting = "";
        String logo = " ______  _____  _____\n"
                + "|  ____|/ ____|/ ____|\n"
                + "| |__  | (___ | |\n"
                + "|  __|  \\___ \\| |\n"
                + "| |____ ____) | |____\n"
                + "|______|_____/ \\_____|\n";
        greeting += "Hello from\n" + logo;
        //greeting += "What is your name?";

        System.out.println(greeting);
    }

    public static void showLine() {
        System.out.println("______________________");
    }

    /**
     * Reads the user command.
     * @return User command.
     */
    public static String readCommand() {
        showLine();
        System.out.println("Enter command: ");
        String userInput = in.nextLine();
        System.out.println("[Command entered: " + userInput + "]"); //TODO remove if not necessary
        return userInput;
    }

    /**
     *  Prints a list of commands used in the programme.
     */
    public static void printHelp() {
        String helpMessage = "Here's a list of things you can do:\n"
                + "\t> Create Category:     create n/[CATEGORYNAME]  e.g. create n/Biology.\n"
                + "\t> List Category: ​     list category\n"
                + "\t> Select Category: ​   select [INDEX] e.g. select 1\n"
                + "\t> Delete Category: ​   delete category [INDEX] e.g. delete 1\n"
                + "\t> Add New Card:​       add q/[QUESTION] a/[ANSWER] e.g. add q/Which year was NUS founded? a/1980\n"
                + "\t> List all Cards: ​    list card\n"
                + "\t> Delete a Card: ​     delete [INDEX] e.g. delete 1\n"
                + "\t> Start Quiz: ​        quiz\n"
                + "\t> View Answer: ​       answer [INDEX] e.g. answer 1\n"
                + "\t> Help Page: ​         help\n"
                + "\t> Exit the Program: ​  exit";
        System.out.println(helpMessage);
    }

    /**
     * Exits the ESC program.
     */
    public static void exitEsc() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}
