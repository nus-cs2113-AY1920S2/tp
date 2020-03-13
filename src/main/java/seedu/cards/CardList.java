package seedu.cards;

import seedu.duke.Storage;
import seedu.exception.EscException;

import java.util.ArrayList;

/**
 * Represents the entire deck of flashcards.
 */
public class CardList {
    private ArrayList<Card> cards;

    public CardList() {
        this.cards = new ArrayList<Card>();
    }

    public CardList(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * Adds a card to the deck.
     * @param card Card to be added.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes a card from the deck.
     * @param index Index of card to be removed.
     */
    public void removeCard(int index) throws EscException {
        if (this.size() == 0) {
            throw new EscException("The card list is empty.");
        }

        try {
            cards.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The card item does not exist.");
        }
    }

    /**
     * Returns a card based on its index number.
     * @param index Index of card to retrieve.
     * @return card Card corresponding to index.
     */
    public Card getCard(int index) throws EscException {
        if (this.size() == 0) {
            throw new EscException("The card list is empty.");
        }

        // assuming that our question index starts from 1 and not 0.
        Card card;
        try {
            card = cards.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The card item does not exist.");
        }
        return card;
    }

    /**
     * Returns size of the cardlist.
     */
    public int size() {
        return this.cards.size();
    }

}
