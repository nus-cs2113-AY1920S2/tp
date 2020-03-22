package seedu.subjects;

import seedu.cards.Card;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Flashcard object used to store a question and its answer.
 */
public class Subject implements Serializable {
    private final String name;
    public ArrayList<Card> cards;

    public Subject(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.name;
    }




}
