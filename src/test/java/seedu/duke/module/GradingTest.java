package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradingTest {

    @Test
    void getGradeValue() {
        double gradeValue = 5.0;
        assertEquals(gradeValue, Grading.APLUS.getPoint());
        assertEquals(gradeValue, Grading.A.getPoint());
        gradeValue = 4.5;
        assertEquals(gradeValue, Grading.AMINUS.getPoint());
        gradeValue = 4.0;
        assertEquals(gradeValue, Grading.BPLUS.getPoint());
        gradeValue = 3.5;
        assertEquals(gradeValue, Grading.B.getPoint());
        gradeValue = 3.0;
        assertEquals(gradeValue, Grading.BMINUS.getPoint());
        gradeValue = 2.5;
        assertEquals(gradeValue, Grading.CPLUS.getPoint());
        gradeValue = 2.0;
        assertEquals(gradeValue, Grading.C.getPoint());
        gradeValue = 0.0;
        assertEquals(gradeValue, Grading.CS.getPoint());
        gradeValue = 0.0;
        assertEquals(gradeValue, Grading.CU.getPoint());
        gradeValue = 0.0;
        assertEquals(gradeValue, Grading.F.getPoint());
    }

    @Test
    void getGradeString() {
        String gradeString = "A+";
        assertEquals(gradeString, Grading.APLUS.getGrade());
        gradeString = "A";
        assertEquals(gradeString, Grading.A.getGrade());
        gradeString = "B+";
        assertEquals(gradeString, Grading.BPLUS.getGrade());
        gradeString = "B";
        assertEquals(gradeString, Grading.B.getGrade());
        gradeString = "B-";
        assertEquals(gradeString, Grading.BMINUS.getGrade());
        gradeString = "C+";
        assertEquals(gradeString, Grading.CPLUS.getGrade());
        gradeString = "C";
        assertEquals(gradeString, Grading.C.getGrade());
        gradeString = "D+";
        assertEquals(gradeString, Grading.DPLUS.getGrade());
        gradeString = "D";
        assertEquals(gradeString, Grading.D.getGrade());
        gradeString = "CS";
        assertEquals(gradeString, Grading.CS.getGrade());
        gradeString = "CU";
        assertEquals(gradeString, Grading.CU.getGrade());
        gradeString = "F";
        assertEquals(gradeString, Grading.F.getGrade());
    }
}