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
import seedu.exception.PacException;
import seedu.ui.UI;

import java.util.logging.Logger;


public class StudentCommandInterpreter extends CommandInterpreter {

    protected UI ui;
    private static final Logger logger = Logger.getLogger(StudentCommandInterpreter.class.getName());

    public StudentCommandInterpreter(EventList eventList) {
        super(eventList);
        this.ui = new UI();
    }

    /**
     * Method to decide the type of command to execute.
     * @param commandDescription the following parameter used.
     *                           Currently only used for delete command.
     * @return The student related command that the user calls.
     * @throws PacException If an invalid command Description is provided.
     */
    public Command decideCommand(String commandDescription) throws PacException {

        String commandType = getFirstWord(commandDescription);

        assert commandType.isBlank() : "studentlist: Unknown command";

        AttendanceCommandInterpreter.setupLogger();
        logger.info("StudentList Log");
        logger.finest(commandType);

        switch (commandType) {
        case "add":
            return new AddStudentList();
        case "view":
            return new ViewStudentList();
        case "delete":
            return new DeleteStudentList();
        case "sort":
            return sortCommand();
        case "find":
            return new FindStudentList();
        case "clear":
            return new ClearStudentList();
        default:
            throw new PacException("Unknown Student List Command.");
        }
    }

    private Command sortCommand() throws PacException {
        UI.display("Please Key in either 'name' or 'list'.");
        ui.readUserInput();
        String sortType = ui.getUserInput();
        switch (sortType) {
        case "name":
            return new SortStudentListByName();
        case "list":
            return new SortStudentListByList();
        default:
            throw new PacException("Unknown Student List Sort Command");
        }
    }
}
