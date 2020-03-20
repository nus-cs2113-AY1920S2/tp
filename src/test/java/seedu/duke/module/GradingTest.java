package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradingTest {

    @Test
    void getGradeValue() {
        double gradeValue = 5.0;
        assertEquals(gradeValue, Grading.A.getGradeValue());
    }
}