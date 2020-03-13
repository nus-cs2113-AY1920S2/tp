package seedu.duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.exception.EscException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static seedu.duke.Quiz.generateRandomInt;
import static seedu.duke.Quiz.retrieveCard;
import static seedu.duke.Quiz.quizQuestion;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizTest {
    private CardList emptyCardList;
    private CardList filledCardList;
    private Card flashcard1;
    private Card flashcard2;
    private Card flashcard3;
    private EscException expectedException;

    @BeforeEach
    void setUp() {
        emptyCardList = new CardList();
        filledCardList = new CardList();
        flashcard1 = new Card("What does HTTP stand for?", "HyperText Transfer Protocol");
        flashcard2 = new Card("What does HTML stand for?", "HyperText Markup Language");
        flashcard3 = new Card("What does CSS stand for?", "Cascading Style Sheets");
        filledCardList.addCard(flashcard1);
        filledCardList.addCard(flashcard2);
        filledCardList.addCard(flashcard3);
    }

    @Test
    void generateRandomInt_emptyCardList_exceptionThrown() {
        expectedException = new EscException("The card list is empty.");
        try {
            generateRandomInt(0);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void retrieveCard_emptyCardList_exceptionThrown() {
        expectedException = new EscException("The card list is empty.");
        try {
            retrieveCard(emptyCardList);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void retrieveCard_SuccessfullyExecuted() throws EscException {
        Card card = retrieveCard(filledCardList);
        assertTrue(card.equals(flashcard1) || card.equals(flashcard2) || card.equals(flashcard3));
    }

}
