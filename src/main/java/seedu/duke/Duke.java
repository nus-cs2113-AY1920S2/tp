package seedu.duke;

import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.commands.Command;
import seedu.exception.EscException;
import seedu.parser.Parser;

import java.util.ArrayList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static UI ui = new UI();
    private Storage storage = new Storage();
    private CardList cards;

    /**
     *  Lists all the cards in the list.
     *   @param cards A list of card to be displayed.
     */
    public static void listCards(ArrayList<Card> cards) {
        if (cards.size() == 0) {
            System.out.println("You haven't added anything yet.");
        } else {
            System.out.println("Here is the list of questions.");
            for (int i = 0; i < cards.size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " + cards.get(i).getQuestion());
            }
        }
    }

    /**
     * Reads the user's commands and executes them until the user issues the exit command.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                cards = new CardList(storage.loadCards());
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(cards);
                storage.saveCards(cards.getCards());
                isExit = c.isExit();
            } catch (EscException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        //assert false : "dummy to fail";
        new Duke().run();
    }
}