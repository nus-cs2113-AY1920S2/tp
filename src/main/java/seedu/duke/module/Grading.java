package seedu.duke.module;

public enum Grading {
    APLUS("A+"),
    A("A"),
    AMINUS("A-"),
    BPLUS("B+"),
    B("B"),
    BMINUS("B-"),
    CPLUS("C+"),
    C("C"),
    DPLUS("D+"),
    D("D"),
    F("F"),
    CS("PASS"),
    CU("FAIL");

    private String grade;

    public String getGrade() {
        return this.grade;
    }

    Grading(String grade) {
        this.grade = grade;
    }
}
