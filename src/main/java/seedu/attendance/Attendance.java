package seedu.attendance;

/**
 * Class representing attendance of a student.
 */
public class Attendance {

    public String studentName;
    public String isPresent;

    public Attendance(String studentName, String isPresent) {
        this.studentName = studentName;
        this.isPresent = isPresent;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getAttendanceStatus() {
        return isPresent;
    }

    @Override
    public String toString() {
        return studentName + ": " + isPresent;
    }

}
