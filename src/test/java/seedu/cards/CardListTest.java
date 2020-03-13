package seedu.cards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import seedu.exception.EscException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CardListTest {
    private CardList cards;
    private Card flashcard1;
    private Card flashcard2;
    private EscException expectedException;

    @BeforeEach
    void setUp() {
        cards = new CardList();
        flashcard1 = new Card("What does HTTP stand for?", "HyperText Transfer Protocol");
        flashcard2 = new Card("What does HTML stand for?", "Hypertext Markup Language");
    }

    @Test
    void getCards() {
        assertFalse(cards.getCards().contains(flashcard1));
        assertFalse(cards.getCards().contains(flashcard2));
        cards.addCard(flashcard1);
        cards.addCard(flashcard2);
        assertTrue(cards.getCards().contains(flashcard1));
        assertTrue(cards.getCards().contains(flashcard2));
    }

    @Test
    void getCard_emptyCardList_exceptionThrown() {
        expectedException = new EscException("The card list is empty.");
        try {
            cards.getCard(1);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void getCard_indexOutOfRange_exceptionThrown() {
        cards.addCard(flashcard1);
        expectedException = new EscException("The card item does not exist.");
        int[] getIndexes = {-1,2};
        for (int i : getIndexes) {
            try {
                cards.getCard(i);
                fail("Out of range index should have thrown an exception");
            } catch (EscException e) {
                assertEquals(e.getMessage(),expectedException.getMessage());
            }
        }
    }

    @Test
    void getCard_SuccessfullyExecuted() throws EscException {
        cards.addCard(flashcard1);
        cards.addCard(flashcard2);
        assertEquals(cards.getCard(1), flashcard1);
        assertEquals(cards.getCard(2), flashcard2);
    }

    @Test
    void addCard_CardSuccessfullyAdded() {
        cards.addCard(flashcard1);
        assertTrue(cards.size() == 1);
        assertTrue(cards.getCards().contains(flashcard1));
    }

    @Test
    void removeCard_emptyCardList_exceptionThrown() {
        expectedException = new EscException("The card list is empty.");
        try {
            cards.removeCard(1);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void removeCard_indexOutOfRange_exceptionThrown() {
        cards.addCard(flashcard1);
        expectedException = new EscException("The card item does not exist.");
        int[] deleteIndexes = {-1,1};
        for (int i : deleteIndexes) {
            try {
                cards.removeCard(i);
                fail("Out of range index should have thrown an exception");
            } catch (EscException e) {
                assertEquals(e.getMessage(),expectedException.getMessage());
            }
        }
    }

    @Test
    void removeCard_CardSuccessfullyDeleted() throws EscException {
        cards.addCard(flashcard1);
        cards.addCard(flashcard2);

        cards.removeCard(0);

        assertFalse(cards.getCards().contains(flashcard1));
        assertTrue(cards.getCards().contains(flashcard2));
        assertEquals(1,cards.getCards().size());

        cards.removeCard(0);

        assertFalse(cards.getCards().contains(flashcard2));
        assertEquals(0,cards.getCards().size());
    }

}
