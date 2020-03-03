package seedu.cards;

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
        //method stub
    }

    /**
     * Removes a card from the deck.
     * @param index Index of card to be removed.
     */
    public void removeCard(int index) {
        //method stub
    }

    /**
     * Returns a card based on its index number.
     * @param index Index of card to retrieve.
     * @return card Card corresponding to index.
     */
    public Card getCard(int index) {
        // assuming that our question index starts from 1 and not 0.
        Card card = cards.get(index + 1);
        return card;
    }

}
