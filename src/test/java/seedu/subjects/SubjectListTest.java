package seedu.subjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.exception.EscException;

public class SubjectListTest {

    private SubjectList subjects;
    private Subject subject1;
    private Subject subject2;
    private Subject subject3;
    private Subject subject4;
    private EscException expectedException;

    @BeforeEach
    void setUp() {
        subjects = new SubjectList();
        subject1 = new Subject("TEST 1");
        subject2 = new Subject("TEST 2");
        subject3 = new Subject("TEST1");
        subject4 = new Subject("    tEsT 1");
    }

    @Test
    void getSubjects() throws EscException {
        subjects.addSubject(subject1);
        subjects.addSubject(subject2);
        subjects.addSubject(subject3);
        subjects.addSubject(subject4);
        assertTrue(subjects.getSubjects().contains(subject1));
        assertTrue(subjects.getSubjects().contains(subject2));
        assertFalse(subjects.getSubjects().contains(subject3));
        assertFalse(subjects.getSubjects().contains(subject4));
    }

    @Test
    void getSubject_emptySubjectList_exceptionThrown() {
        expectedException = new EscException("The subject list is empty.");
        try {
            subjects.getSubject(1);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void getSubject_indexOutOfRange_exceptionThrown() throws EscException {
        subjects.addSubject(subject1);
        expectedException = new EscException("The subject item does not exist.");
        int[] getIndexes = {-1,2};
        for (int i : getIndexes) {
            try {
                subjects.getSubject(i);
                fail("Out of range index should have thrown an exception");
            } catch (EscException e) {
                assertEquals(e.getMessage(),expectedException.getMessage());
            }
        }
    }

    @Test
    void getSubject_SuccessfullyExecuted() throws EscException {
        subjects.addSubject(subject1);
        subjects.addSubject(subject2);
        assertEquals(subjects.getSubject(0), subject1);
        assertEquals(subjects.getSubject(1), subject2);
    }


    @Test
    void addSubject_SubjectSuccessfullyAdded() throws EscException {
        subjects.addSubject(subject1);
        assertTrue(subjects.size() == 1);
        assertTrue(subjects.getSubjects().contains(subject1));
    }


    @Test
    void addSubject_UnSubjectSuccessfullyAdded() throws EscException {
        subjects.addSubject(subject1);
        subjects.addSubject(subject4);
        assertTrue(subjects.size() == 1);
        assertTrue(subjects.getSubjects().contains(subject1));
    }


    @Test
    void removeSubject_emptySubjectList_exceptionThrown() {
        expectedException = new EscException("The subject list is empty.");
        try {
            subjects.removeSubject(1);
        } catch (EscException e) {
            assertEquals(e.getMessage(),expectedException.getMessage());
        }
    }

    @Test
    void removeSubject_indexOutOfRange_exceptionThrown() throws EscException {
        subjects.addSubject(subject1);
        expectedException = new EscException("The subject item does not exist.");
        int[] deleteIndexes = {-1,1};
        for (int i : deleteIndexes) {
            try {
                subjects.removeSubject(i);
                fail("Out of range index should have thrown an exception");
            } catch (EscException e) {
                assertEquals(e.getMessage(),expectedException.getMessage());
            }
        }
    }

    @Test
    void removeSubject_SubjectSuccessfullyDeleted() throws EscException {
        subjects.addSubject(subject1);
        subjects.addSubject(subject2);

        subjects.removeSubject(0);

        assertFalse(subjects.getSubjects().contains(subject1));
        assertTrue(subjects.getSubjects().contains(subject2));
        assertEquals(1,subjects.getSubjects().size());

        subjects.removeSubject(0);

        assertFalse(subjects.getSubjects().contains(subject2));
        assertEquals(0,subjects.getSubjects().size());
    }
}
