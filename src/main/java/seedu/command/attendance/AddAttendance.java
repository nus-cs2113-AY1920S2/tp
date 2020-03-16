package seedu.command.attendance;

import seedu.attendance.Attendance;
import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.parser.AttendanceParser;
import seedu.parser.PerformanceParser;
import seedu.performance.PerformanceList;

public class AddAttendance extends Command {

    Attendance attendance;
    public String commandParameters;

    public AddAttendance(String commandParameters) {
        this.commandParameters = commandParameters;
        attendance = new AttendanceParser().parseAttendance(commandParameters);
    }

    /**
     * Executes this command on the given task list and user interface.
     */

    public void addToList() {
        String eventName = attendance.getEvent();
        AttendanceList attendanceList = new AttendanceList();
        attendanceList.addToList(attendance, eventName);
    }

    @Override
    public void execute() {
        addToList();
    }
}