package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.cards.Card;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCardCommandTest {

    private AddCardCommand addCardCommand;

    @Test
    void addCardCommand_noSubject_exceptionThrown() {
        Card card = new Card("What does HTTP stand for?","HyperText Transfer Protocol");
        EscException expectedException = new EscException("The subject list is empty.");
        int subjectIndex = 1;
        addCardCommand = new AddCardCommand(subjectIndex, card);
        SubjectList subjectList = new SubjectList();
        try {
            addCardCommand.execute(subjectList);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void addCardCommand_validCard_correctlyConstructed() {
        Card card = new Card("What does HTTP stand for?","HyperText Transfer Protocol");
        int subjectIndex = 1;
        addCardCommand = new AddCardCommand(subjectIndex,card);
        assertEquals(card.getQuestion(),addCardCommand.getCard().getQuestion());
        assertEquals(card.getAnswer(),addCardCommand.getCard().getAnswer());
        //System registered index = user inputted index - 1
        assertEquals(subjectIndex - 1,addCardCommand.getSubjectIndex());
    }

    @Test
    void execute_validCard_successfullyAdded() throws EscException {
        Card card = new Card("What does HTTP stand for?","HyperText Transfer Protocol");
        int subjectIndex = 1;
        addCardCommand = new AddCardCommand(subjectIndex, card);

        SubjectList subjectList = new SubjectList();
        AddSubjectCommand addSubjectCommand = new AddSubjectCommand("Biology");
        addSubjectCommand.execute(subjectList);
        addCardCommand.execute(subjectList);

        assertTrue(subjectList.getSubject(subjectIndex - 1).getCardList().getCards().contains(card));
        assertEquals(subjectIndex,subjectList.getSubject(subjectIndex - 1).getCardList().getCards().size());
    }
}