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
    public static UI ui = new UI();
    private static Scanner in = new Scanner(System.in);
    private CardList cards = new CardList();
  
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
        ui.showWelcome();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
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