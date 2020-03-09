package seedu.student.attendance;

import seedu.student.Student;


/**
 * Class representing attendance of a student.
 */
public class Attendance extends Student {

    public String description;
    public boolean hasAttended;

    /**
     * The class that handles the attendance.
     * Contains hasAttended input.
     *
     * @param studentName The name of the student.
     * @param description The description of the lesson.
     * @param hasAttended The status of the attendance.
     */
    public Attendance(String studentName, String description, boolean hasAttended) {
        super(studentName);
        this.description = description;
        this.hasAttended = hasAttended;
    }

    /**
     * The class that handles the attendance.
     * Does not contain hasAttended input.
     *
     * @param studentName The name of the student.
     * @param description The description of the lesson.
     */
    public Attendance(String studentName, String description) {
        super(studentName);
        this.description = description;
        this.hasAttended = false;
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
    public boolean getAttendance() {
        return hasAttended;
    }

    @Override
    public String toString() {
        return super.toString() + " " + description + " " + hasAttended;
    }

}
