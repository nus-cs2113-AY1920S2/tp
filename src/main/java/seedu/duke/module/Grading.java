package seedu.duke.module;

public enum Grading {
    APLUS("A+", 5.00),
    A("A", 5.00),
    AMINUS("A-", 4.50),
    BPLUS("B+", 4.00),
    B("B", 3.50),
    BMINUS("B-", 3.00),
    CPLUS("C+", 2.50),
    C("C", 2.00),
    DPLUS("D+", 1.50),
    D("D", 1.00),
    F("F", 0.00),
    CS("PASS", 0.00),
    CU("FAIL", 0.00);

    Grading(String grade, double gradePoint) {
        this.grade = grade;
        this.gradePoint = gradePoint;
    }

    private String grade;

    private double gradePoint;

    public String getGrade() {
        return this.grade;
    }

    public double getPoint() {
        return this.gradePoint;
    }
}
