package seedu.duke.module;

public enum Grading {
    APLUS("A+", 5.0),
    A("A", 5.0),
    AMINUS("A-", 4.5),
    BPLUS("B+", 4.0),
    B("B", 3.5),
    BMINUS("B-", 3.0),
    CPLUS("C+", 2.5),
    C("C", 2.0),
    DPLUS("D+", 1.5),
    D("D", 1.0),
    F("F", 0.0),
    CS("PASS", 0.0),
    CU("FAIL", 0.0);

    private String grade;
    private float gradePoint;

    public String getGrade() {
        return this.grade;
    }

    public String getPoint() {
        return this.gradePoint;
    }

    Grading(String grade, float gradePoint) {
        this.grade = grade;
        this.gradePoint = gradePoint;
    }
}
