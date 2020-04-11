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
     * Method To view the existing attendanceList.
     */
    private void view() {
        attendanceList.displayList();
    }

    @Override
    public void execute() throws PacException {
        view();
    }
}
