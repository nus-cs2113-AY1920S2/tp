package seedu.commands;

import seedu.cards.Card;
import seedu.cards.CardList;

/**
 * Command Class for the AddCard command.
 */
public class AddCardCommand extends Command {

    public static final String COMMAND_WORD = "add";

    private String question;
    private String answer;

    /**
     * Initialises the parameters for card creation.
     */
    public AddCardCommand(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Adds a card into the application.
     */
    public void execute(CardList cards) throws Exception {
        Card card = new Card(this.question,this.answer);
        cards.addCard(card);
    }
}
