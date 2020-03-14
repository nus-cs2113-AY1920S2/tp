package seedu.command.attendance;

import seedu.attendance.Attendance;
import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.parser.AttendanceParser;
import seedu.performance.PerformanceList;

public class DeleteAttendance extends Command {
    Attendance attendance;
    public String userInput;

    public DeleteAttendance(String commandParameters) {
        this.userInput = commandParameters;
        attendance = new AttendanceParser().parseAttendance(commandParameters);
    }

    @Override
    public void execute() {
        AttendanceList.deleteAttendance(attendance);
    }
}
