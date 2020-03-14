package seedu.attendance;

import seedu.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class AttendanceList {

    public static List<Attendance> attendanceList;
    public static int numberOfAttendee;

    public AttendanceList() {
        attendanceList = new ArrayList<>();
        numberOfAttendee = attendanceList.size();
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public int getSize() {
        return attendanceList.size();
    }

    public void addToList(Attendance attendance, String eventName) {
        attendanceList.add(attendance);
        UI.addAttendanceMessage(attendance.studentName, eventName);
    }

    public static void deleteAttendance(Attendance attendance) {
        boolean hasDeleted = false;
        if (numberOfAttendee > 0) {
            for (Attendance a : attendanceList) {
                if (a != null
                        && attendance.getDescription().equals(a.getDescription())
                        && attendance.getEvent().equals(a.getEvent())
                        && attendance.getStudent().equals(a.getStudent())) {
                    attendanceList.remove(a);
                    hasDeleted = true;
                }
            }
        }
        UI.deleteAttendanceMessage(attendance, hasDeleted);
    }

}
