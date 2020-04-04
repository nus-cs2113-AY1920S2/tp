package seedu.performance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PerformanceTest {
    Performance gradeTestUnit = new Performance("John", "A");
    Performance markTestUnit = new Performance("Li Ming", "100");
    Performance numericNameTestUnit = new Performance("123", "No result");
    Performance negativeResultTestUnit = new Performance("Negative result", "-1");

    @Test
    void getStudent() {
        assertEquals("John", gradeTestUnit.getStudent());
        assertEquals("Li Ming", markTestUnit.getStudent());
        assertEquals("123", numericNameTestUnit.getStudent());
        assertEquals("Negative result", negativeResultTestUnit.getStudent());
    }

    @Test
    void getResult() {
        assertEquals("A", gradeTestUnit.getResult());
        assertEquals("100", markTestUnit.getResult());
        assertEquals("No result", numericNameTestUnit.getResult());
        assertEquals("-1", negativeResultTestUnit.getResult());
    }

    @Test
    void testToString() {
        assertEquals("John: A", gradeTestUnit.toString());
        assertEquals("Li Ming: 100", markTestUnit.toString());
        assertEquals("123: No result", numericNameTestUnit.toString());
        assertEquals("Negative result: -1", negativeResultTestUnit.toString());
    }
}