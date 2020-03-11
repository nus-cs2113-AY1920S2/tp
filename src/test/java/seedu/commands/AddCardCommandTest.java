package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.cards.Card;
import seedu.cards.CardList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCardCommandTest {

    private AddCardCommand addCardCommand;

    @Test
    void addCardCommand_validCard_CorrectlyConstructed() {
        Card card = new Card("What does HTTP stand for?","HyperText Transfer Protocol");
        addCardCommand = new AddCardCommand(card);
        assertEquals(card.getQuestion(),addCardCommand.getCard().getQuestion());
        assertEquals(card.getAnswer(),addCardCommand.getCard().getAnswer());
    }

    @Test
    void execute_validCard_SuccessfullyAdded() {
        Card card = new Card("What does HTTP stand for?","HyperText Transfer Protocol");
        addCardCommand = new AddCardCommand(card);

        CardList resultCards = new CardList();

        addCardCommand.execute(resultCards);

        assertTrue(resultCards.getCards().contains(card));
        assertEquals(1,resultCards.getCards().size());
    }
}