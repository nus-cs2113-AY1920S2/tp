package seedu.subjects;

import seedu.cards.CardList;
import seedu.score.ScoreList;

import java.io.Serializable;

/**
 * Flashcard object used to store a question and its answer.
 */
public class Subject implements Serializable {
    private final String name;
    private CardList cardList = new CardList();
    private ScoreList scoreList = new ScoreList();

    public Subject(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.name;
    }

    public CardList getCardList() {
        return cardList;
    }

    public ScoreList getScoreList() {
        return this.scoreList;
    }

}
