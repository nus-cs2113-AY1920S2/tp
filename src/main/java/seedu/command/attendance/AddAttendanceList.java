package seedu.command.attendance;

import seedu.attendance.Attendance;
import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.student.StudentList;
import seedu.parser.AttendanceParser;
import seedu.ui.UI;

import java.util.ArrayList;

import static seedu.pac.Pac.studentListCollection;

/**
 * Class representing an attendance related command to add an attendanceList for a specific event.
 * attendanceList by default will create a new list.
 * However, attendanceList can be added using an existing studentList in the studentListCollection.
 * If there is an existing attendanceList, this command will append to the end of the list.
 */
public class AddAttendanceList extends Command {

    protected AttendanceParser attendanceParser = new AttendanceParser();
    protected AttendanceList attendanceList;
    protected String eventName;
    protected StudentList newStudentList;
    private UI ui = new UI();
    protected String studentName = "";
    protected String status = "";
    protected String input = "";
    protected int studentNumber = 0;

    public AddAttendanceList(AttendanceList attendances, String eventName) {
        this.attendanceList = attendances;
        this.eventName = eventName;
        this.newStudentList = new StudentList(eventName);
    }

    /**
     * Method to decide whether user wants to create a new list or use an existing student list.
     * @throws PacException If studentNameList is empty.
     */
    public void addToList() throws PacException {
        if (!attendanceList.isEmpty()) {
            UI.display("You cannot add to an existing list. Please clear first. ");
        } else if (isByNameList()) {
            createWithExistingList();
        } else {
            createNewList();
        }
    }

    /**
     * Check the user input.
     * @return true if the user wants to import an existing list.
     * @return false if the user wants to create a new list.
     */
    private boolean isByNameList() {
        UI.display("Would you like to import an existing student list? "
                + "If yes, input 'yes'. Else, input anything.");
        String reply = ui.getStringInput();
        if (reply.toLowerCase().contains("yes")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to create with existing student list.
     */
    private void createWithExistingList() throws PacException {
        if (studentListCollection.isEmpty()) {
            UI.display("There is no existing student list to import");
        } else {
            ui.printStudentListCollection();
            appendWithExistingList(fetchStudentList());
        }
    }

    /**
     * Method to fetch studentList from studentListCollection.
     * User can select base on the index given.
     * @return A studentList selected from the studentListCollection.
     */
    private ArrayList<String> fetchStudentList() throws PacException {
        UI.display("Please state the index of the studentList that you wish to import");
        ui.readUserInput();
        try {
            int index = Integer.parseInt(ui.getUserInput());
            return studentListCollection.get(index - 1).getStudentList();
        } catch (Exception e) {
            throw new PacException("Wrong format");
        }
    }

    /**
     * Append attendanceList with an existing student list.
     * @param studentNameList the existing student list selected.
     */
    private void appendWithExistingList(ArrayList<String> studentNameList) {
        for (String studentName: studentNameList) {
            attendanceList.addToList(new Attendance(studentName,
                    ui.getAttendanceStatusOfStudent(studentName)), eventName);
        }
        UI.display("AttendanceList added");
    }

    /**
     * Method to create new attendanceList.
     * @throws PacException If parameter provided is invalid.
     */
    private void createNewList() throws PacException {
        if (isNewUser()) {
            addManually();
        } else {
            addByLine();
        }
    }

    /**
     * Check the user input.
     * @return true if the user is a new user.
     * @return false if the user is an experience user.
     */
    private boolean isNewUser() {
        UI.display("Are you a new user? If so, please type 'yes' ");
        String reply = ui.getStringInput();
        if (reply.toLowerCase().contains("yes")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to create new attendanceList using flags within the user input.
     * @throws PacException If parameter provided is invalid.
     */
    private void addByLine() throws PacException {
        while (!input.toLowerCase().equals("done")) {
            UI.display("Please key following format:\n"
                    + "n/Name p/Status [Y/N]\n"
                    + "Status will be take as absent if the format above is not followed.");
            ui.readUserInput();
            input = ui.getUserInput();
            if (input.equals("done")) {
                break;
            }
            if (!attendanceList.isDuplicate(attendanceParser.getName(input))) {
                attendanceList.addToList(attendanceParser.parseAttendance(input), eventName);
                studentName = attendanceParser.parseAttendance(input).getStudentName();
                newStudentList.addToList(studentName);
                studentNumber++;
            } else {
                UI.display("There is an entry with the same name.");
            }
        }
        appendNewStudentList();
        UI.display("You have successfully added "
                + studentNumber + " to the attendance list.\n");
    }

    /**
     * Method to create new attendanceList using a line-by-line input from the user.
     */
    private void addManually() {
        while (!studentName.equals("done")) {
            UI.display("Please key in student name.");
            ui.readUserInput();
            studentName = ui.getUserInput();
            if (studentName.equals("done")) {
                break;
            }
            if (attendanceList.isDuplicate(studentName)) {
                UI.display("Duplicated name found, student name : [ " + studentName + " ] not added");
            } else {
                UI.display("To mark the student as present, please use 'y' or 'Y'.\n"
                        + "By default the student will be marked as absent,"
                        + " if any other input is given.");
                ui.readUserInput();
                status = ui.getUserInput();
                attendanceList.addToList(new Attendance(studentName, status), eventName);
                newStudentList.addToList(studentName);
                studentNumber++;
            }
        }
        appendNewStudentList();
        UI.display("You have successfully added "
                + studentNumber + " to the attendance list.\n");
    }

    public void appendNewStudentList() {
        if (!newStudentList.isEmpty()) {
            studentListCollection.push(newStudentList);
        }
    }


    @Override
    public void execute() throws PacException {
        try {
            addToList();
        } catch (PacException e) {
            throw new PacException("Attendance List fail to add.\n"
                    + "If you wish to add attendance again,\n"
                    + "please type the command 'attendance add' again");
        }

    }
}