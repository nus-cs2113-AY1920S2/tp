package seedu.attendance;

import seedu.exception.DukeException;
import seedu.ui.DisplayTable;
import seedu.ui.UI;

import java.util.ArrayList;

public class AttendanceList {

    public static ArrayList<Attendance> attendanceList;
    UI ui;
    DisplayTable displayTable;

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
        ui.addAttendanceMessage(attendance.studentName, eventName);
        System.out.println("Please insert the next student");
    }

    public void printList() throws DukeException {
        if (isEmpty()) {
            throw new DukeException("No attendance list under this event");
        }
        int i = 1;
        displayTable.printHeaderOfThree("index", "Name of Student", "Status");
        for (Attendance attendance : attendanceList) {
            displayTable.printBodyOfThree(i, attendance.getStudentName(), attendance.getAttendanceStatus());
            i++;
        }
    }

    public boolean isEmpty() {
        return attendanceList.isEmpty();
    }



}
