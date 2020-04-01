package seedu.attendance;

import seedu.exception.PacException;
import seedu.ui.DisplayTable;
import seedu.ui.UI;

import java.util.ArrayList;
import java.util.Collections;

import static seedu.attendance.Attendance.attendanceListNameComparator;
import static seedu.attendance.Attendance.attendanceStatusComparator;

/**
 * Class representing attendance list of student's attendance status.
 */
public class AttendanceList {

    protected ArrayList<Attendance> attendanceList;
    protected UI ui;
    private DisplayTable displayTable;

    public AttendanceList() {
        this.ui = new UI();
        this.displayTable = new DisplayTable();
        attendanceList = new ArrayList<>();
    }

    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void addToList(Attendance attendance, String eventName) {
        attendanceList.add(attendance);
        ui.addAttendanceMessage(attendance.studentName, attendance.getAttendanceStatus(), eventName);
    }

    public void printList() throws PacException {
        if (isEmpty()) {
            throw new PacException("No attendance list under this event");
        }
        int i = 1;
        displayTable.printHeaderOfThree("index", "Name of Student", "Status");
        for (Attendance attendance : attendanceList) {
            displayTable.printBodyOfThree(i, attendance.getStudentName(), attendance.getAttendanceStatus());
            i++;
        }
    }

    /**
     * Check whether the attendanceList is empty.
     * @return the status of attendanceList.
     */
    public boolean isEmpty() {
        return attendanceList.isEmpty();
    }


    /**
     * Clear the attendanceList.
     */
    public void clearList() {
        attendanceList.clear();
    }

    public void sort() {
        Collections.sort(attendanceList,attendanceListNameComparator);
    }

    public void sortByStatus() {
        Collections.sort(attendanceList,attendanceStatusComparator);
    }
}
