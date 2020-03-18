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
    public Command decideCommand(String commandDescription) throws DukeException {
        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        switch (commandType) {
        case "add":
            return new AddPerformance(commandParameters);
        case "delete":
            return new DeletePerformance(commandParameters);
        case "list:student":
            return new ViewStudentResult(commandParameters);
        case "list:assignment":
            return new ViewAssignmentResult(commandParameters);
        default:
            throw new DukeException("Performance: Unknown command.");
        }
    }
}
