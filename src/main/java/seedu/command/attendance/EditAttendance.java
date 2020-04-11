package seedu.command.attendance;

import seedu.attendance.AttendanceList;
import seedu.command.Command;
import seedu.exception.PacException;
import seedu.ui.DisplayTable;
import seedu.ui.UI;

public class EditAttendance extends Command {

    protected DisplayTable displayTable;
    protected UI ui;
    protected AttendanceList attendanceList;
    protected String eventName;
    protected int index;


    public EditAttendance(AttendanceList attendances) {
        this.attendanceList = attendances;
        this.ui = new UI();
        this.displayTable = new DisplayTable();
    }

    /**
     * To determine the index of the attendance the user wishes to edit.
     */
    private void getIndex() throws PacException {
        try {
            UI.display("Please state the index of the student you wish to edit");
            ui.readUserInput();
            this.index = Integer.parseInt(ui.getUserInput()) - 1;
        } catch (Exception e) {
            throw new PacException("Wrong format entered.");
        }
    }

    /**
     * To display the selected attendance.
     */
    private void displayAttendance() {
        displayTable.printHeaderOfThree("index", "Name of Student", "Status");
        displayTable.printBodyOfThree(1, attendanceList.getAttendanceList().get(index).getStudentName(),
                attendanceList.getAttendanceList().get(index).getStatus());
    }

    /**
     * Method to set new Name.
     */
    private void editName() {
        UI.display("What do you want to change the name to?");
        ui.readUserInput();
        String studentName = ui.getUserInput().trim();
        if (attendanceList.isDuplicate(studentName) || studentName.equals("")) {
            UI.display("Duplicate name found. Please try again.");
            editName();
        } else {
            attendanceList.getAttendanceList().get(index).setName(studentName);
        }
    }

    /**
     * Method to set new Status.
     */
    private void editStatus() {
        UI.display("What do you want to change the status to? [Y/N]");
        ui.readUserInput();
        String studentStatus = ui.getUserInput();
        attendanceList.getAttendanceList().get(index).setStatus(studentStatus);
    }

    /**
     * Method to edit the existing attendance.
     */
    private void edit() throws PacException {
        if (attendanceList.isEmpty()) {
            UI.display("The attendance list is currently empty. Please add attendance instead.");
        } else {
            attendanceList.displayList();
            getIndex();
            if (this.index >= attendanceList.getAttendanceList().size() || this.index < 0) {
                throw new PacException("The index you have chosen is out of bound.");
            } else {
                displayAttendance();
                decideEdit();
                attendanceList.displayList();
            }
        }
    }

    /**
     * To determine if the user want to set new name or status.
     */
    private void decideEdit() {
        UI.display("Do you wish to change the `name` or change the `status`");
        String input = ui.getStringInput();
        switch (input.toLowerCase().trim()) {
        case "name":
            editName();
            break;
        case "status":
            editStatus();
            break;
        default:
            UI.display("I do not understand your message. Please try again");
            break;
        }
    }

    @Override
    public void execute() throws PacException {
        edit();
    }
}
