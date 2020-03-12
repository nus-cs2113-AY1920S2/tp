package seedu.command;

import seedu.command.performance.AddPerformance;
import seedu.command.performance.DeletePerformance;
import seedu.command.performance.ViewAssignmentResult;
import seedu.command.performance.ViewStudentResult;
import seedu.event.EventList;
import seedu.exception.DukeException;


public class PerformanceCommandInterpreter extends CommandInterpreter {
    public PerformanceCommandInterpreter(EventList eventList) {
        super(eventList);
    }

    /**
     * Execute the command from userInput.
     *
     * @param commandDescription The userInput from the Ui.
     * @throws DukeException If the command is undefined.
     */
    public void executeCommand(String commandDescription) throws DukeException {
        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        switch (commandType) {
        case "add":
            new AddPerformance(commandParameters).execute();
            break;
        case "delete":
            new DeletePerformance(commandParameters).execute();
            break;
        case "view_student_result":
            new ViewStudentResult(commandParameters).execute();
            break;
        case "view_assignment_result":
            new ViewAssignmentResult(commandParameters).execute();
            break;
        default:
            throw new DukeException("UNKNOWN COMMAND");
        }
    }
}
