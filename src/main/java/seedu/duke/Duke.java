package seedu.duke;

import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.commands.Command;
import seedu.parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static Scanner in = new Scanner(System.in);
    private CardList cards = new CardList();

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
        //greeting += "What is your name?";

        System.out.println(greeting);
    }

    /**
     * Exit method.
     * Terminates the programme.
     */
    public static void exitEsc() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     *  Prints a list of commands used in the programme.
     */
    public static void printHelp() {
        String helpMessage = " Welcome to ESC.\n"
                + "Create: ​create n/[CATEGORYNAME] \n"
                + "E.g. ​create n/Biology.\n"
                + "List Category: ​list category\n"
                + "Select Category: ​select [INDEX]\n"
                + "Eg. ​select 1\n"
                + "Delete Category: ​delete category [INDEX]\n"
                + "Eg. ​delete 1\n"
                + "Add Card:​ add q/[QUESTION] a/[ANSWER]\n"
                + "Eg. ​add q/Which year was NUS founded? a/1980\n"
                + "List Card: ​list card\n"
                + "Delete Card: ​delete [INDEX]\n"
                + "Eg. ​delete 1\n"
                + "Quiz: ​quiz\n"
                + "Answer: ​answer​ / ​answer [INDEX]\n"
                + "Eg. ​answer 1\n"
                + "Help: ​help\n"
                + "Exit: ​exit\n";

        System.out.println(helpMessage);
    }

    /**
     *  Lists all the cards in the list.
     *   @param cards A list of card to be displayed.
     */
    public static void listCards(ArrayList<Card> cards) {
        System.out.println("Here is the list of questions.");
        for (int i = 0; i < cards.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". " + cards.get(i).getQuestion());
        }
    }

    /**
    * Main method.
    */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Reads the user command.
     * @return User command.
     */
    private static String readCommand() {
        System.out.println("Enter command: ");
        String userInput = in.nextLine();

        System.out.println("[Command entered: " + userInput + "]");
        return userInput;
    }

    /**
     * Reads the user's commands and executes them until the user issues the exit command.
     */
    private void run() {
        printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(cards);
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println("Invalid command");
            }
        }
    }
}