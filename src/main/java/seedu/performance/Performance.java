package seedu.performance;

import seedu.ui.UI;

public class Performance {
    public String eventName;
    public String studentName;
    public String assignment;
    public String result;
    public Boolean hasResult;

    /**
     * Save the result of student base on a set of module, student and assignment,
     * the result can be saved as either mark in integer or grade in string.
     * @param eventName A string input by user, that is the name of the module
     *                     of the result saved.
     * @param studentName A string input by user, the name of student who scored
     *                      the result.
     * @param assignment A string input by user, the name of the assignment of the
     *                   result.
     */
    public Performance(String eventName, String studentName, String assignment) {
        this.eventName = eventName;
        this.studentName = studentName;
        this.assignment = assignment;
    }

    public String getEvent() {
        return eventName;
    }

    public String getStudent() {
        return studentName;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setResult(String result) {
        this.result = result;
        hasResult = true;
    }

    public void setResultByList(String studentName) {
        String result = new UI().getPerformanceParameter(studentName);
        setResult(result);
    }

    /**
     * The string to be saved to the student list, in the format
     * of [name of module][name of assignment] result of student.
     *
     * @return A string that shows the result of a student base on assignment.
     */
    public String formatForStudentList() {
        return "[" + eventName + "] "
                + "[" + assignment + "] " + getResult();
    }

    /**
     * To determine whether a string grade or an integer mark
     * result is used as the result of student.
     *
     * @return A serializable result, either grade in string or
     *         mark in integer.
     */
    public String getResult() {
        if (hasResult) {
            return result;
        }
        return "No result";
    }
}
