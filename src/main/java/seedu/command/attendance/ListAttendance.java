package seedu.command.attendance;

import seedu.attendance.Attendance;
import seedu.attendance.AttendanceList;
import seedu.command.Command;

import java.util.List;

public class ListAttendance extends Command {

    public static List<Attendance> attendanceList;
    private String studentName;

    public ListAttendance(String userInput) {
        String[] instructions = userInput.split(" ", 2);
        studentName = instructions[1];
        attendanceList = new AttendanceList().getAttendanceList();
    }

    public void printStudentAttendance() {
        int size = attendanceList.size();
        if (size == 0) {
            System.out.println("empty");
        } else {
            int i = 1;
            for (Attendance attendance: attendanceList) {
                System.out.println(i + attendance.formatForStudentList());
                i++;
            }
        }
    }

    @Override
    public void execute() {
        printStudentAttendance();
    }
}
