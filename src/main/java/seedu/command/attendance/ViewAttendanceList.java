package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.UI;

/**
 * Class representing an attendance related command to List an attendanceList of a specific event.
 */
public class ViewAttendanceList extends Command {

    protected AttendanceList attendanceList;
    protected UI ui;

    public ViewAttendanceList(AttendanceList attendances) {
        this.attendanceList = attendances;
        this.ui = new UI();
    }

    /**
     * To view the existing attendanceList.
     * If the attendanceList is empty, it will display a message to inform that the attendanceList is empty.
     * @throws PacException If AttendanceList is empty or AttendanceList fail to view.
     */
    private void view() throws PacException {
        if (!attendanceList.isEmpty()) {
            attendanceList.displayAttendanceList();
        } else {
            UI.display("Attendance List is empty");
        }
    }

    @Override
    public void execute() throws PacException {
        view();
    }
}
