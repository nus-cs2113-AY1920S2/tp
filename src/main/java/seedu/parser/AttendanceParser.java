package seedu.parser;

import seedu.attendance.Attendance;
import seedu.exception.PacException;

public class AttendanceParser {

    /**
     * This is the parser for Attendance. It gets the parameters from the user
     * and parse them to studentName and status, and create a new Attendance
     * with the two data. It then returns the Attendance created.
     *
     * @param commandParameters A String contains information of the Attendance, to be parsed.
     * @return                  A Attendance containing information parsed from commandParameters.
     * @throws PacException     Throws PacException when the commandParameters contains wrong
     *                          tokens or insufficient parameter.
     */
    public Attendance parseAttendance(String commandParameters) throws PacException {
        String[] dataToRead = commandParameters.split(" ", 5);
        String studentName = "";
        String status = "";
        for (String token : dataToRead) {
            if (token != null) {
                String[] data = token.split("/");
                if (data.length < 2) {
                    throw new PacException("Insufficient parameter or wrong command.");
                }
                switch (data[0]) {
                case "n":
                    studentName = data[1];
                    break;
                case "p":
                    status = data[1];
                    break;
                default:
                    throw new PacException("Wrong type of Attendance data token.");
                }
            }
        }
        if (studentName.equals("") || status.equals("")) {
            throw new PacException("Insufficient variables to be saved as Attendance");
        }
        return new Attendance(studentName, status);
    }

    public String getName(String commandParameters) throws PacException {
        try {
            String[] dataToRead = commandParameters.split(" ", 5);
            String[] tokens = dataToRead[0].split("/");
            String studentName = tokens[1];
            return studentName;
        } catch (Exception e) {
            throw new PacException("Fail to add");
        }
    }
}
