package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.cards.Card;
import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeleteCardCommandTest {

    private DeleteCardCommand deleteCardCommand;
    private SubjectList subjectList;
    private Card card;
    private String subjectName;
    private EscException expectedException;

    @BeforeEach
    void setUp() {
        card = new Card("What does HTTP stand for?","HyperText Transfer Protocol");
        subjectList = new SubjectList();
        subjectName = "Biology";
    }

    @Test
    void execute_emptySubjectList_exceptionThrown() {
        deleteCardCommand = new DeleteCardCommand(1,1);
        expectedException = new EscException("The subject list is empty.");
        try {
            deleteCardCommand.execute(subjectList);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void execute_emptyCardList_exceptionThrown() {
        deleteCardCommand = new DeleteCardCommand(1,1);
        AddSubjectCommand addSubjectCommand = new AddSubjectCommand(subjectName);
        addSubjectCommand.execute(subjectList);
        expectedException = new EscException("The card list is empty.");
        try {
            deleteCardCommand.execute(subjectList);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void execute_indexOutOfRange_exceptionThrown() throws EscException {
        int subjectIndex = 1;
        AddSubjectCommand addSubjectCommand = new AddSubjectCommand(subjectName);
        addSubjectCommand.execute(subjectList);
        AddCardCommand addCardCommand = new AddCardCommand(subjectIndex, card);
        addCardCommand.execute(subjectList);
        expectedException = new EscException("The card item does not exist.");
        int[] deleteIndexes = {0,2};
        for (int i : deleteIndexes) {
            try {
                deleteCardCommand = new DeleteCardCommand(1,i);
                deleteCardCommand.execute(subjectList);
                fail("Out of range index should have thrown an exception");
            } catch (EscException e) {
                assertEquals(e.getMessage(),expectedException.getMessage());
            }
        }
    }

    @Test
    void deleteCardCommand_validCard_CorrectlyConstructed() {
        int subjectIndex = 1;
        int cardIndex = 1;
        deleteCardCommand = new DeleteCardCommand(subjectIndex,cardIndex);
        assertEquals(subjectIndex - 1,deleteCardCommand.getSubjectIndex());
        assertEquals(cardIndex - 1, deleteCardCommand.getCardIndex());
    }

    @Test
    void execute_validCard_SuccessfullyDeleted() throws EscException {
        int subjectIndex = 1;
        deleteCardCommand = new DeleteCardCommand(subjectIndex,1);
        AddSubjectCommand addSubjectCommand = new AddSubjectCommand(subjectName);
        addSubjectCommand.execute(subjectList);
        AddCardCommand addCardCommand = new AddCardCommand(subjectIndex, card);
        addCardCommand.execute(subjectList);
        deleteCardCommand.execute(subjectList);

        assertFalse(subjectList.getSubject(subjectIndex - 1).getCardList().getCards().contains(card));
        assertEquals(0,subjectList.getSubject(subjectIndex - 1).getCardList().getCards().size());
    }
}