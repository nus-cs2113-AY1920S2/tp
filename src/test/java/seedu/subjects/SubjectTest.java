package seedu.subjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubjectTest {
    private Subject subject1;
    private Subject subject2;

    @BeforeEach
    public void setUp() {
        subject1 = new Subject("Test 1");
        subject2 = new Subject("Test 2");
    }

    @Test
    public void getSubject() {
        assertTrue(subject1.getSubject().equals("Test 1"));
        assertTrue(subject2.getSubject().equals("Test 2"));
    }

}
