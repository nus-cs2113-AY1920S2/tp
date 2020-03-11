package seedu.performance;

import java.io.Serializable;

/*
 * if Performance.student = student
 * studentlist.add(performance.viewbystudent)
 *
 * list -> list of student -> list of student result for all assignment
 *      -> list of assignment -> list of assignment result for all its students
 */
public class Performance {
    public String moduleName;
    public String studentName;
    public String assignment;
    public String grade;
    public int mark;
    private static boolean isMark = false;
    private static boolean isGrade = false;

    /**
     * Save the result of student base on a set of module, student and assignment,
     * the result can be saved as either mark in integer or grade in string.
     * @param moduleName A string input by user, that is the name of the module
     *                     of the result saved.
     * @param studentName A string input by user, the name of student who scored
     *                      the result.
     * @param assignment A string input by user, the name of the assignment of the
     *                   result.
     */
    public Performance(String moduleName, String studentName, String assignment) {
        this.moduleName = moduleName;
        this.studentName = studentName;
        this.assignment = assignment;
    }

    public String getModule() {
        return moduleName;
    }

    public String getStudent() {
        return studentName;
    }

    public String getAssignment() {
        return assignment;
    }

    public void recordMark(int mark) {
        this.mark = mark;
        isMark = true;
    }

    public void recordGrade(String grade) {
        this.grade = grade;
        isGrade = true;
    }

    /**
     * The string to be saved to the student list, in the format
     * of [name of module][name of assignment] result of student.
     *
     * @return A string that shows the result of a student base on assignment.
     */
    public String formatForStudentList() {
        return "[" + moduleName + "] "
                + "[" + assignment + "] " + getResult();
    }

    /**
     * To determine whether a string grade or an integer mark
     * result is used as the result of student.
     *
     * @return A serializable result, either grade in string or
     *         mark in integer.
     */
    public Serializable getResult() {
        if (isMark) {
            return mark;
        } else if (isGrade) {
            return grade;
        }
        return "No result updated";
    }
}
