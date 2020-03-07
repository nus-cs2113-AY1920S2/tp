package seedu.duke;

import java.io.Serializable;

/*
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
    private static boolean isMark = false;
    private static boolean isGrade = false;

    /**
     * Save the result of student base on a set of module, student and assignment,
     * the result can be saved as either mark in integer or grade in string.
     * @param nameOfModule A string input by user, that is the name of the module
     *                     of the result saved.
     * @param nameOfStudent A string input by user, the name of student who scored
     *                      the result.
     * @param assignment A string input by user, the name of the assignment of the
     *                   result.
     */
    public Performance(String nameOfModule, String nameOfStudent, String assignment) {
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

    /**
     * The string to be saved to the performance list, in the format
     * of [name of module][name of student] result of student.
     *
     * @return A string that shows the result of a student base on name.
     */
    public String formatForAssignmentList() {
        //store in the format [module][student]result
        return "[" + nameOfModule + "] "
                + "[" + nameOfStudent + "] " + getResult();
    }

    /**
     * The string to be saved to the student list, in the format
     * of [name of module][name of assignment] result of student.
     *
     * @return A string that shows the result of a student base on assignment.
     */
    public String formatForStudentList() {
        return "[" + nameOfModule + "] "
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
