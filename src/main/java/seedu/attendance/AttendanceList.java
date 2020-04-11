package seedu.attendance;

import seedu.ui.DisplayTable;
import seedu.ui.UI;

import java.util.ArrayList;
import java.util.Collections;

import static seedu.attendance.Attendance.attendanceListNameComparator;
import static seedu.attendance.Attendance.attendanceStatusComparator;

/**
 * Class representing attendance list of student's attendance status.
 */
public class AttendanceList {

    protected ArrayList<Attendance> attendanceList = new ArrayList<>();
    protected UI ui = new UI();
    private DisplayTable displayTable = new DisplayTable();

    /**
    * To fetch the existing attendanceList.
    * @return attendanceList.
    */
    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    /**
     * To check if there is an existing name in the attendanceList.
     * @param name the name of the student to be added into the attendanceList.
     * @return  If there is an existing name in the attendanceList, return true.
     *          ELse, return false.
     */
    public boolean isDuplicate(String name) {
        String existingStudentName;
        String newStudentName = name.toLowerCase();
        for (int i = 0; i < attendanceList.size(); i++) {
            existingStudentName = attendanceList.get(i).getStudentName().toLowerCase();
            if (existingStudentName.equals(newStudentName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To add to the existing attendanceList in the selected event.
     * @param attendance the existing attendanceList in the event.
     * @param eventName the name of the event.
     */
    public void addToList(Attendance attendance, String eventName) {
        attendanceList.add(attendance);
        ui.addAttendanceMessage(attendance.studentName, attendance.getStatus(), eventName);
    }

    /**
     * Add attendance to attendanceList.
     * @param attendance the attendance to be added
     */
    public void add(Attendance attendance) {
        attendanceList.add(attendance);
    }

    public void displayAttendanceList(ArrayList<Attendance> attendanceList) {
        int index = 1;
        displayTable.printHeaderOfThree("index", "Name of Student", "Status");
        for (Attendance attendance : attendanceList) {
            displayTable.printBodyOfThree(index, attendance.getStudentName(), attendance.getStatus());
            index++;
        }
    }

    /**
     * To display the attendanceList in table form.
     */
    public void displayList() {
        if (attendanceList.isEmpty()) {
            UI.display("Attendance List is empty");
        } else {
            displayAttendanceList(attendanceList);
        }
    }

    /**
     * Check whether the attendanceList is empty.
     * @return the status of attendanceList.
     */
    public boolean isEmpty() {
        return attendanceList.isEmpty();
    }

    /**
     * Clear the attendanceList.
     */
    public void clearList() {
        attendanceList.clear();
    }

    /**
     * To sort the attendanceList by the name of the student alphabetically.
     */
    public void sortByName() {
        Collections.sort(attendanceList,attendanceListNameComparator);
    }


    /**
     * To sort the attendanceList by the status of the student.
     */
    public void sortByStatus() {
        Collections.sort(attendanceList,attendanceStatusComparator);
    }


    public void findAttendance() {
        UI.display("Please type the name of the student you are looking for.");
        ui.readUserInput();
        String keyword = ui.getUserInput().trim().toLowerCase();
        if (isMatch(keyword).isEmpty()) {
            UI.display("There is no student named: " + keyword);
        } else {
            UI.display("Search Result(s): ");
            displayAttendanceList(isMatch(keyword));
        }
    }

    public ArrayList<Attendance> isMatch(String keyword) {
        ArrayList<Attendance> searchResults = new ArrayList<>();
        for (Attendance attendance: this.attendanceList) {
            if (attendance.getStudentName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(attendance);
            }
        }
        return searchResults;
    }
}
