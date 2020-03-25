package seedu.command.interpreter;

import seedu.StudentList;
import seedu.command.Command;
import seedu.command.student.AddStudent;
import seedu.command.student.DeleteStudent;
import seedu.command.student.ListStudent;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.ui.UI;

public class StudentCommandInterpreter extends CommandInterpreter {
    StudentList studentList;
    UI ui;

    public StudentCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    public Command decideCommand(String commandDescription) throws DukeException {
        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        switch (commandType) {
        case "add":
            return new AddStudent(studentList);
        case "list":
            return new ListStudent();
        case "delete":
            try {
                return new DeleteStudent(Integer.parseInt(commandParameters));
            } catch (Exception e) {
                throw new DukeException(e.getMessage());
            }
        default:
            throw new DukeException("Performance: Unknown command.");
        }
    }


}
