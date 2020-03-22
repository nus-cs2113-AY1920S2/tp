package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteSubjectCommandTest {

    private DeleteSubjectCommand deleteSubjectCommand;
    private SubjectList subjectList;
    private String subjectName;
    private EscException expectedException;

    @BeforeEach
    void setUp() {
        subjectList = new SubjectList();
        subjectName = "Biology";
    }

    @Test
    void execute_emptySubjectList_exceptionThrown() {
        int subjectIndex = 1;
        deleteSubjectCommand = new DeleteSubjectCommand(subjectIndex);
        expectedException = new EscException("The subject list is empty.");
        try {
            deleteSubjectCommand.execute(subjectList);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void execute_indexOutOfRange_exceptionThrown() {
        AddSubjectCommand addSubjectCommand = new AddSubjectCommand(subjectName);
        addSubjectCommand.execute(subjectList);
        expectedException = new EscException("The subject item does not exist.");
        int[] deleteIndexes = {0,2};
        for (int i : deleteIndexes) {
            try {
                deleteSubjectCommand = new DeleteSubjectCommand(i);
                deleteSubjectCommand.execute(subjectList);
                fail("Out of range index should have thrown an exception");
            } catch (EscException e) {
                assertEquals(e.getMessage(),expectedException.getMessage());
            }
        }
    }

    @Test
    void deleteCardCommand_validSubjectIndex_CorrectlyConstructed() {
        int subjectIndex = 1;
        deleteSubjectCommand = new DeleteSubjectCommand(subjectIndex);
        assertEquals(subjectIndex - 1,deleteSubjectCommand.getSubjectIndex());
    }

    @Test
    void execute_validCard_SuccessfullyDeleted() throws EscException {
        int subjectIndex = 1;
        AddSubjectCommand addSubjectCommand = new AddSubjectCommand(subjectName);
        addSubjectCommand.execute(subjectList);
        deleteSubjectCommand = new DeleteSubjectCommand(subjectIndex);
        deleteSubjectCommand.execute(subjectList);
        assertEquals(0,subjectList.getSubjects().size());
    }
}