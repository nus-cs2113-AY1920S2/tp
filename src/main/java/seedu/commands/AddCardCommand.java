package seedu.commands;

import seedu.cards.Card;
import seedu.cards.CardList;

/**
 * Command Class for the AddCard command.
 */
public class AddCardCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "\tTo add card, type command:​ add q/[QUESTION] a/[ANSWER]";

    private Card card;

    /**
     * Initialises the parameters for card creation.
     */
    public AddCardCommand(Card card) {
        this.card = card;
    }

    /**
     * Returns card to be added.
     */
    public Card getCard() {
        return card;
    }

    /**
     * Adds a card into the application.
     */
    public void execute(CardList cards) {
        cards.addCard(card);
    }
}
