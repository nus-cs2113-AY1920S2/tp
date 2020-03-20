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

    public double getGradeValue() {
        double gradeValue;
        switch (this.grade) {
        case "A+":
        case "A":
            gradeValue = 5.0;
            break;
        case "A-":
            gradeValue = 4.5;
            break;
        case "B+":
            gradeValue = 4.0;
            break;
        case "B":
            gradeValue = 3.5;
            break;
        case "B-":
            gradeValue = 3.0;
            break;
        case "C+":
            gradeValue = 2.5;
            break;
        case "C":
            gradeValue = 2.0;
            break;
        case "D+":
            gradeValue = 1.5;
            break;
        case "D":
            gradeValue = 1.0;
            break;
        case "F":
            gradeValue = 0.0;
            break;
        case "CS":
            gradeValue = 0.0;
            break;
        case "CU":
            gradeValue = 0.0;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + grade);
        }
        return gradeValue;
    }

}
