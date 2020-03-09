package seedu.command.attendance;

import seedu.command.Command;
import seedu.module.attendance.Attendance;
import seedu.module.attendance.AttendanceList;

import java.util.ArrayList;

public class FindAttendanceCommand extends Command {

    ArrayList<Attendance> searchResults = new ArrayList<>();
    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to find.
     */

    public FindAttendanceCommand(String keyword, AttendanceList list) {
        for (Object student: list) {
            if (list.contains(keyword)) {
                searchResults.add((Attendance) student);
            }
        }
        System.out.println(searchResults);
    }


}
