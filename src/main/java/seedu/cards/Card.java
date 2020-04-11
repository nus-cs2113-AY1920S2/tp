package seedu.cards;

import java.io.Serializable;

/**
 * Flashcard object used to store a question and its answer.
 */
public class Card implements Serializable {
    private final String question;
    private final String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }
}
