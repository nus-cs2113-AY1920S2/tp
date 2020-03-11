package seedu.attendance;

/**
 * Class representing attendance of a student.
 */
public class Attendance {
    public String moduleName;
    public String studentName;
    public String description;
    public String hasAttended;

    /**
     * The class that handles the attendance.
     * Contains hasAttended input.
     *
     * @param moduleName The name of the module.
     * @param studentName The name of the student.
     * @param description The description of the lesson.
     * @param hasAttended The status of the attendance.
     */
    public Attendance(String moduleName, String studentName, String description, String hasAttended) {
        this.moduleName = moduleName;
        this.studentName = studentName;
        this.description = description;
        this.hasAttended = hasAttended;
    }

    /**
     * The class that handles the attendance.
     * Does not contain hasAttended input.
     *
     * @param studentName The name of the student.
     * @param description The description of the lesson.
     *
     */
    public Attendance(String moduleName, String studentName, String description) {
        this.moduleName = moduleName;
        this.studentName = studentName;
        this.description = description;
        this.hasAttended = "false";
    }

    /**
     * To retrieve the name of the module.
     *
     * @return The name of the module.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * To retrieve the name of student.
     *
     * @return The name of student.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * To retrieve the description of the lesson.
     *
     * @return The description of the lesson.
     */
    public String getDescription() {
        return description;
    }

    /**
     * To retrieve the status of the attendance.
     *
     * @return The status of the attendance.
     */
    public String getAttendance() {
        return hasAttended;
    }

    @Override
    public String toString() {
        return moduleName + " " + studentName + " " + description + " " + hasAttended;
    }

}
