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
    protected int studentNumber = 1;

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
        UI.display("Would you like to import an existing student list? "
                + "If yes, input 'yes'. Else, input anything.");
        if (isByNameList()) {
            createWithExistingList();
        } else {
            createNewList();
        }
    }

    /**
     * Method to create with existing student list.
     * @throws PacException If there is no existing student list.
     */
    private void createWithExistingList() throws PacException {
        if (studentListCollection.isEmpty()) {
            throw new PacException("There is no existing student list to import");
        } else {
            ui.printStudentListCollection();
            ArrayList<String> studentNameList = fetchAttendanceList();
            appendWithExistingList(studentNameList);
        }
    }

    /**
     * Method to create new attendanceList.
     * @throws PacException If parameter provided is invalid.
     */
    private void createNewList() throws PacException {

        UI.display("Do you wish to have line by line prompt? If so, please enter 'yes'");
        if (isNewUser()) {
            withFlag();
        } else {
            lineByLine();
        }
    }

    /**
     * Method to create new attendanceList using flags within the user input.
     * @throws PacException If parameter provided is invalid.
     */
    private void withFlag() throws PacException {
        while (!input.toLowerCase().equals("done")) {
            UI.display("Please key following format:\n"
                    + "n/Name p/Status[Y/N]");
            ui.readUserInput();
            input = ui.getUserInput();
            if (input.equals("done")) {
                break;
            }
            attendanceList.addToList(attendanceParser.parseAttendance(input), eventName);
            studentName = attendanceParser.parseAttendance(input).getStudentName();
            newStudentList.addToList(studentName);
            studentNumber++;
        }
        studentListCollection.push(newStudentList);
        UI.display("You have successfully added "
                + studentNumber + " to the attendance list.\n");
    }

    /**
     * Method to create new attendanceList using a line-by-line input from the user.
     * @throws PacException If parameter provided is invalid.
     */
    private void lineByLine() throws PacException {
        while (!studentName.equals("done")) {
            UI.display("Please key in student name.");
            ui.readUserInput();
            studentName = ui.getUserInput();
            if (studentName.equals("done")) {
                break;
            }
            if (attendanceList.isDuplicate(studentName)) {
                throw new PacException("Duplicated name found, student name : [ " + studentName + " ] not added");
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
        studentListCollection.push(newStudentList);
        UI.display("You have successfully added "
                + studentNumber + " to the attendance list.\n");
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
     * Check the user input.
     * @return true if the user wants to import an existing list.
     * @return false if the user wants to create a new list.
     */
    private boolean isByNameList() {
        String reply = ui.getStringInput();
        if (reply.toLowerCase().contains("yes")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check the user input.
     * @return true if the user is a new user.
     * @return false if the user is an experience user.
     */
    private boolean isNewUser() {
        String reply = ui.getStringInput();
        if (reply.toLowerCase().contains("yes")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to fetch studentList from studentListCollection.
     * User can select base on the index given.
     * @return A studentList selected from the studentListCollection.
     * @throws PacException If a string is given instead of an integer.
     */
    private ArrayList<String> fetchAttendanceList() throws PacException {
        UI.display("Please state the index of the studentList that you wish to import");
        ui.readUserInput();
        try {
            int index = Integer.parseInt(ui.getUserInput());
            return studentListCollection.get(index - 1).getStudentList();
        } catch (Exception e) {
            throw new PacException("Invalid Format");
        }
    }

    @Override
    public void execute() throws PacException {
        try {
            addToList();
        } catch (Exception e) {
            throw new PacException("Attendance List fail to add.\n"
                    + "If you wish to add attendance again,\n"
                    + "please type the command 'attendance add' again");
        }

    }
}