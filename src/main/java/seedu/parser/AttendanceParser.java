package seedu.parser;

import seedu.attendance.Attendance;
import seedu.exception.DukeException;
import seedu.ui.UI;

import java.util.Arrays;

public class AttendanceParser {

    public Attendance parseAttendance(String commandParameters) throws DukeException {
        String[] dataToRead = commandParameters.split(" ", 5);
        String studentName = "";
        String isPresent = "";
        int r = 0;
        for (String s : dataToRead) {
            if (s != null) {
                String[] data = s.split("/");
                switch (data[0]) {
                case "n":
                    studentName = data[1];
                    break;
                case "p":
                    isPresent = data[1];
                    break;
                default:
                    UI.printWrongInput(s);
                }
            }
        }
        if (studentName.equals("") || isPresent.equals("")) {
            throw new DukeException("Insufficient variables to be saved as attendance");
        }
        return new Attendance(studentName, isPresent);
    }
}
