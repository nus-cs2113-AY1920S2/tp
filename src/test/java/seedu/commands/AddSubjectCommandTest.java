package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddSubjectCommandTest {

    private AddSubjectCommand addSubjectCommand;
    private String subjectName = "Test";

    @Test
    void addSubjectCommand_validSubject_correctlyConstructed() {
        addSubjectCommand = new AddSubjectCommand(subjectName);
        assertEquals(subjectName,addSubjectCommand.getSubjectName());
    }

    @Test
    void execute_validSubject_successfullyAdded() throws EscException {
        addSubjectCommand = new AddSubjectCommand(subjectName);
        int subjectIndex = 1;

        SubjectList subjectList = new SubjectList();
        Subject subject = new Subject(subjectName);
        subjectList.addSubject(subject);

        assertEquals(subjectList.getSubject(subjectIndex - 1).getSubject(),subjectName);
    }
}