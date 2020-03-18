package seedu.performance;

public class Performance {
    public String studentName;
    public String result;

    /**
     * Save the result of student.
     * @param studentName A string input by user, the name of student who scored
     *                      the result.
     * @param result      A string input by user, sets the result of the student.
     */
    public Performance(String studentName, String result) {
        this.result = result;
        this.studentName = studentName;
    }

    public String getStudent() {
        return studentName;
    }

    public String getResult() {
        return result;
    }
}
