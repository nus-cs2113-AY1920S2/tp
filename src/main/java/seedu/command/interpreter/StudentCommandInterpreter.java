package seedu.command.interpreter;

import seedu.command.Command;
import seedu.command.student.SortStudentListByList;
import seedu.command.student.SortStudentListByName;
import seedu.command.student.ClearStudentList;
import seedu.command.student.FindStudentList;
import seedu.command.student.AddStudentList;
import seedu.command.student.DeleteStudentList;
import seedu.command.student.ViewStudentList;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.ui.UI;

public class StudentCommandInterpreter extends CommandInterpreter {

    protected UI ui;

    public StudentCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    /**
     * Method to decide the type of command to execute.
     * @param commandDescription the following parameter used.
     *                           Currently only used for delete command.
     * @return The student related command that the user calls.
     * @throws DukeException If an invalid command Description is provided.
     */
    public Command decideCommand(String commandDescription) throws DukeException {

        String commandType = getFirstWord(commandDescription);
        switch (commandType) {
        case "add":
            try {
                return new AddStudentList();
            } catch (Exception e) {
                ui.displayMessage("Student Command Add failed.");
                throw new DukeException("Student Command Add failed.");
            }

        case "list":
            try {
                return new ViewStudentList();
            } catch (Exception e) {
                ui.displayMessage("Student Command List failed.");
                throw new DukeException("Student Command List failed.");
            }
        case "delete":
            try {
                return new DeleteStudentList();
            } catch (Exception e) {
                ui.displayMessage("Student Command Delete failed.");
                throw new DukeException("Student Command Delete failed.");
            }
        case "sort":
            try {
                ui.displayMessage("Please Key in either 'name' or 'list'.");
                ui.readUserInput();
                String sortType = ui.getUserInput();
                switch (sortType) {
                case "name":
                    try {
                        return new SortStudentListByName();
                    } catch (Exception e) {
                        ui.displayMessage("Student Command Sort By Name failed.");
                        throw new DukeException("Student Command Sort By Name failed.");
                    }
                case "list":
                    try {
                        return new SortStudentListByList();
                    } catch (Exception e) {
                        ui.displayMessage("Student Command Sort By List failed.");
                        throw new DukeException("Student Command Sort By List failed.");
                    }
                default:
                    ui.displayMessage("Unknown Student Sort Command");
                    throw new DukeException("Unknown Student Sort Command");
                }
            } catch (Exception e) {
                ui.displayMessage("Student Command Sort failed.");
                throw new DukeException("Student Command Sort failed.");
            }
        case "find":
            try {
                return new FindStudentList();
            } catch (Exception e) {
                ui.displayMessage("Student Command Find failed.");
                throw new DukeException("Student Command Find failed.");
            }
        case "clear":
            try {
                return new ClearStudentList();
            } catch (Exception e) {
                ui.displayMessage("Student Command Clear failed.");
                throw new DukeException("Student Command Clear failed.");
            }
        default:
            ui.displayMessage("Unknown Student Command.");
            throw new DukeException("Unknown Student Command.");
        }
    }
}
