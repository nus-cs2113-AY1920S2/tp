package seedu.commands;

import seedu.cards.Card;
import seedu.cards.CardList;

/**
 * Command Class for the AddCard command.
 */
public class AddCardCommand extends AddCommand {

    public static final String COMMAND_WORD = "addcard";

    public static final String MESSAGE_USAGE = "\tTo add card, type command:​addcard s/[SUBJECT INDEX] q/[QUESTION] a/[ANSWER]";

    private Card card;

    private int subjectIndex;

    /**
     * Initialises the parameters for card creation.
     */
    public AddCardCommand(int subjectIndex, Card card) {
        this.card = card;
        this.subjectIndex = subjectIndex;
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
