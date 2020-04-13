package seedu.attendance;

import java.util.Comparator;

/**
 * Class representing attendance of a student.
 */
public class Attendance {

    protected String studentName;
    protected String isPresent;

    /**
     * Constructor for Attendance.
     * @param studentName A string input by user, the name of student whose attendance is to be set.
     * @param isPresent  A string input by user, sets the attendance status of the student.
     */
    public Attendance(String studentName, String isPresent) {
        setName(studentName);
        setStatus(isPresent);
    }

    /**
     * Set the name of the student base on the input.
     * @param input input provided by the user.
     */
    public void setName(String input) {
        this.studentName = input.trim();
    }

    /**
     * Set the status of the student base on the input.
     * If the input equals to 'Y' pr "Y",
     * the status will be set to Present.
     * Else it is set to Absent by default.
     * @param input input provided by the user.
     */
    public void setStatus(String input) {
        if (input.toLowerCase().trim().equals("y")) {
            this.isPresent = "Present";
        } else {
            this.isPresent = "Absent";
        }
    }

    /**
     * Retrieves the name of the student.
     * @return studentName the name of the student.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Retrieves the attendance status of the student.
     * @return isPresent the attendance status of the student.
     */
    public String getStatus() {
        return isPresent;
    }

    @Override
    public String toString() {
        return studentName + ": " + isPresent;
    }

    /**
     * A comparator to sort the attendance list by student attendance status.
     * The attendance status will be either 'Absent' or 'Present'.
     * Students who are 'Absent' will be push to the top of the list.
     * Students who are 'Present' will be push to the bottom of the list.
     * This is to allow the Professor to quickly determine who is Absent in class.
     */
    public static Comparator<Attendance> attendanceStatusComparator = new Comparator<Attendance>() {
        public int compare(Attendance s1, Attendance s2) {
            String listName1 = s1.getStatus().toUpperCase();
            String listName2 = s2.getStatus().toUpperCase();
            return listName1.compareTo(listName2);
        }
    };

    /**
     * A comparator to sort the attendance list by student name, in
     * alphabetical order.
     */
    public static Comparator<Attendance> attendanceListNameComparator = new Comparator<Attendance>() {
        public int compare(Attendance s1, Attendance s2) {
            String listName1 = s1.getStudentName().toUpperCase();
            String listName2 = s2.getStudentName().toUpperCase();
            return listName1.compareTo(listName2);
        }
    };

    /**
     * Converts "Present" or "Absent" to "Y" and "N" respectively.
     * @return "Y" if Present, "N" if absent or otherwise
     */
    public static String getSimpleAttendanceStatus(String input) {
        if (input.equals("Present")) {
            return "Y";
        } else if (input.equals("Absent")) {
            return "N";
        } else {
            return "N";
        }
    }
}
