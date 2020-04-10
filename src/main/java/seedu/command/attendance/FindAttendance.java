package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.UI;

/**
 * Class representing an attendance related command to List an attendanceList of a specific event.
 * Find the matches base on the keyword given by the user.
 */
public class FindAttendance extends Command {

    protected AttendanceList attendanceList;
    protected UI ui;

    public FindAttendance(AttendanceList attendances) {
        this.attendanceList = attendances;
        this.ui = new UI();
    }

    /**
     * Method To find matches.
     * If the attendanceList is empty, it will display a message to inform that the attendanceList is empty
     * and will not attempt to find.
     */
    private void find() {
        if (!attendanceList.isEmpty()) {
            attendanceList.findAttendance();
        } else {
            UI.display("The attendance list is currently empty. Please add attendance instead.");
        }
    }

    @Override
    public void execute() throws PacException {
        find();
    }
}

