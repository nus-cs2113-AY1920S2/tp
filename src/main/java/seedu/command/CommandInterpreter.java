package seedu.command;

import seedu.command.performance.*;
import seedu.exception.DukeException;
import seedu.exception.PerformanceAssignmentListException;
import seedu.exception.PerformanceStudentListException;

import static seedu.parser.Parser.getCommandWord;

public class CommandInterpreter {

    /**
     * Execute the command from userInput.
     *
     * @param userInput The userInput from the Ui.
     * @return The command object.
     * @throws DukeException If the command is undefined.
     */
    public static Command executeCommand(String userInput) throws DukeException {
        String commandWord = getCommandWord(userInput);
        switch (commandWord) {
        //case "addEvent":
        //  create new EventObject
        case "addPerformance":
            return new AddPerformanceCommand(userInput);
        case "deletePerformance":
            return new DeletePerformanceCommand(userInput);
        case "view_student_result":
            return new ViewStudentResultCommand(userInput);
        case "view_assignment_result":
            return new ViewAssignmentResultCommand(userInput);
        default:
            throw new DukeException("UNKNOWN COMMAND");
        }
    }
}

