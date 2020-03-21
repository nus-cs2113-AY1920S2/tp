package seedu.subjects;

import seedu.cards.Card;
import seedu.cards.CardList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Flashcard object used to store a question and its answer.
 */
public class Subject implements Serializable {
    private final String name;
    private CardList cardList = new CardList();

    public Subject(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.name;
    }

    public CardList getCardList() {
        return this.cardList;
    }

}
