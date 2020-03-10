package seedu.cards;

import seedu.duke.Storage;

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
    public void removeCard(int index) throws Exception {
        try {
            cards.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new Exception("The task item does not exist.");
        }
    }

    /**
     * Returns a card based on its index number.
     * @param index Index of card to retrieve.
     * @return card Card corresponding to index.
     */
    public Card getCard(int index) {
        // assuming that our question index starts from 1 and not 0.
        Card card = cards.get(index - 1);
        return card;
    }

    /**
     * Returns size of the cardlist.
     */
    public int size() {
        return this.cards.size();
    }

}
