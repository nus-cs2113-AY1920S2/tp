package seedu.cards;

import seedu.exception.EscException;
import seedu.subjects.Subject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the entire deck of flashcards.
 */
public class CardList implements Serializable {
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
     * @throws EscException if checkRepeat fails when question has already been added previously.
     */
    public void addCard(Card card, Subject subject) throws EscException {
        checkRepeat(card);
        cards.add(card);
        System.out.println("Q: " + card.getQuestion());
        System.out.println("A: " + card.getAnswer());
        System.out.println("A new card has been added to subject {" + subject.getSubject() + "}\n");
        listCards();
    }

    /**
     * Checks if the question has already been previously added to this subject.
     * @param card Card to be checked before it is added.
     * @throws EscException if question has already been added previously.
     */
    public void checkRepeat(Card card) throws EscException {
        for (Card existingCard : cards) {
            String existingQuestion =  existingCard.getQuestion().toLowerCase();
            String questionToAdd = card.getQuestion().toLowerCase();
            if (existingQuestion.equals(questionToAdd)) {
                throw new EscException("This question has already been added.");
            }
        }
    }

    /**
     * Removes a card from the deck.
     * @param index Index of card to be removed.
     * @throws EscException if the card list is empty or card index does not exist.
     */
    public void removeCard(int index) throws EscException {
        if (this.size() == 0) {
            throw new EscException("The card list is empty.");
        }

        try {
            System.out.println("The card [Q: " + cards.get(index).getQuestion() + " A: "
                            + cards.get(index).getAnswer() + "] has been deleted");
            cards.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The card item does not exist.");
        }
    }

    /**
     * Returns a card based on its index number.
     * @param index Index of card to retrieve.
     * @return card Card corresponding to index.
     * @throws EscException if the card list is empty or card index does not exist.
     */
    public Card getCard(int index) throws EscException {
        if (this.size() == 0) {
            throw new EscException("The card list is empty.");
        }

        // assuming that our question index starts from 1 and not 0.
        Card card;
        try {
            card = cards.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The card item does not exist.");
        }
        return card;
    }

    /**
     *  Lists all the cards in the list.
     */
    public void listCards() {
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
     * Returns size of the cardlist.
     */
    public int size() {
        return this.cards.size();
    }

    /**
     * Removes a card without a return message. For EditCommand usage.
     * @param index index of card.
     * @throws EscException if the card list is empty or card index does not exist.
     */
    public void removeCardSilent(int index) throws EscException {
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
     * Adds a card at the given index. For EditCommand usage.
     * @param card the card to add.
     * @param subject the given subject.
     * @param index index of the original card.
     */
    public void addCardSilent(Card card, Subject subject, int index) {
        cards.add(index,card);
    }
}
