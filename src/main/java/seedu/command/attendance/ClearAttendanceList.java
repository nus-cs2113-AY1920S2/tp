package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.UI;

/**
 * Class representing an attendance related command to clear an existing attendanceList of a specific event.
 */
public class ClearAttendanceList extends Command {

    protected UI ui;
    protected AttendanceList attendanceList;
    protected String eventName;

    public ClearAttendanceList(AttendanceList attendances, String eventName) {
        this.attendanceList = attendances;
        this.ui = new UI();
        this.eventName = eventName;
    }

    /**
     * Method to clear an existing attendanceList in a specific event.
     */
    private void clear() {
        if (!attendanceList.isEmpty()) {
            attendanceList.clearList();
            ui.clearAttendanceMessage(eventName);
        } else {
            UI.display("Attendance List is already empty");
        }
    }

    @Override
    public void execute() throws PacException {
        clear();
    }
}
