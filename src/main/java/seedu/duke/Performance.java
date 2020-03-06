package seedu.duke;

import java.io.Serializable;

/**
 * if Performance.student = student
 * studentlist.add(performance.viewbystudent)
 *
 * list -> list of student -> list of student result for all assignment
 *      -> list of assignment -> list of assignment result for all its students
 */
public class Performance extends Module {
    public String nameOfStudent;
    public String assignment;
    public String grade;
    public int mark;
    private static boolean isMark;
    private static boolean isGrade;

    public Performance (String nameOfModule, String nameOfStudent, String assignment) {
        super(nameOfModule);
        this.nameOfStudent = nameOfStudent;
        this.assignment = assignment;
    }

    public void recordMark(int mark) {
        this.mark = mark;
        isMark = true;
    }
    public void recordGrade(String grade) {
        this.grade = grade;
        isGrade = true;
    }

    public String storeToPerformanceList() {
        //store in the format [module][student]result
        return "[" + nameOfModule + "] " +
                "[" + nameOfStudent + "] " + getResult();
    }

    public String storeToStudentList() {
        return "[" + nameOfModule + "] " +
                "[" + assignment + "] " + getResult();
    }

    public Serializable getResult() {
        if(isMark) {
            return mark;
        } else if (isGrade) {
            return grade;
        }
        return "No result updated";
    }
}
