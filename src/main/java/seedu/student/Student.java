package seedu.student;

/**
 * Class representing a Student.
 */
public class Student {

    public static String studentName;

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public static String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return studentName;
    }
}
