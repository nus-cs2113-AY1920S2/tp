package seedu.command.attendance;

import seedu.module.attendance.Attendance;
import seedu.module.attendance.AttendanceList;
import seedu.command.Command;

import java.util.ArrayList;

public class AttendanceCommand extends Command {
    /**
     * Creates a new AddCommand with the given task.
     *
     * @param studentAttendance The student attendance to add.
     */
    public static void add(Attendance studentAttendance, AttendanceList list) {
        list.add(studentAttendance);
    }

    /**
     * Creates a new DeleteCommand with the given task.
     *
     * @param index The index of the task to be deleted.
     */
    public static void delete(int index, AttendanceList list) {
        list.remove(index);
    }

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to find.
     */
    public static void find(String keyword, AttendanceList list) {
        ArrayList<Attendance> searchResults = new ArrayList<>();

        for (Object student: list) {
            if (list.contains(keyword)) {
                searchResults.add((Attendance) student);
            }
        }
        System.out.println(searchResults);
    }

    public static void list(AttendanceList list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(list.get(i));
            }
        } else {
            System.out.println("\tEMPTY!!");
        }
    }
}
