package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.exception.EscException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeleteCommandTest {

    private DeleteCommand deleteCommand;

    private CardList resultCards;

    private Card card;

    private EscException expectedException;

    @BeforeEach
    void setUp() {
        card = new Card("What does HTTP stand for?","HyperText Transfer Protocol");
        resultCards = new CardList();
    }

    @Test
    void deleteCommandCommand_emptyCardList_exceptionThrown() {
        deleteCommand = new DeleteCommand(0);
        expectedException = new EscException("The card list is empty.");
        try {
            deleteCommand.execute(resultCards);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void deleteCommandCommand_indexOutOfRange_exceptionThrown() {
        int[] deleteIndexes = {-1,1};
        resultCards.addCard(card);
        expectedException = new EscException("The card item does not exist.");
        for (int i : deleteIndexes) {
            try {
                deleteCommand = new DeleteCommand(i);
                deleteCommand.execute(resultCards);
                fail("Out of range index should have thrown an exception");
            } catch (EscException e) {
                assertEquals(e.getMessage(),expectedException.getMessage());
            }
        }
    }

    @Test
    void deleteCommandCommand_validCard_CorrectlyConstructed() {
        deleteCommand = new DeleteCommand(0);
        assertEquals(0,deleteCommand.getIndex());
    }

    @Test
    void execute_validCard_SuccessfullyDeleted() throws EscException {
        deleteCommand = new DeleteCommand(0);
        resultCards.addCard(card);

        deleteCommand.execute(resultCards);

        assertFalse(resultCards.getCards().contains(card));
        assertEquals(0,resultCards.getCards().size());
    }
}