package seedu.parser;

import seedu.attendance.Attendance;
import seedu.ui.UI;

import java.util.Arrays;

public class AttendanceParser {

    public static String[] attendanceDataToParse(String userInput) {
        String[] instructions = userInput.split(" ",20);
        return Arrays.copyOfRange(instructions, 1, instructions.length);
    }

    public Attendance parseAttendance(String commandParameters) {
        String[] dataToRead = commandParameters.split(" ", 5);
        String eventName = "";
        String studentName = "";
        String description = "";
        String hasAttended = "";
        int r = 0;
        for (String s : dataToRead) {
            if (s != null) {
                String[] data = s.split("/");
                switch (data[0]) {
                case "e":
                    eventName = data[1];
                    break;
                case "n":
                    studentName = data[1];
                    break;
                case "c":
                    description = data[1];
                    break;
                case "p":
                    hasAttended = data[1];
                    break;
                default:
                    UI.printWrongInput(s);
                }
            }
        }
        Attendance attendance = new Attendance(eventName, studentName, description, hasAttended);
        return attendance;
    }
}
