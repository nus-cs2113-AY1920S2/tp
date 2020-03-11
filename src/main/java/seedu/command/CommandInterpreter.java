package seedu.command;

import seedu.event.Event;
import seedu.parser.Parser;
import seedu.command.performance.AddPerformanceCommand;
import seedu.command.performance.DeletePerformanceCommand;
import seedu.command.performance.ViewAssignmentResultCommand;
import seedu.command.performance.ViewStudentResultCommand;
import seedu.exception.DukeException;

public class CommandInterpreter {
    protected Parser parser;

    public CommandInterpreter() {
        this.parser = new Parser();
    }

    /**
     * Returns the command category from userInput in lower cases.
     * @param userInput raw user input
     * @return the command category from userInput in lower cases
     */
    protected String getCommandCategory(String userInput) {
        String commandType = userInput.split(" ")[0];
        commandType = commandType.trim();
        commandType = commandType.toLowerCase();
        return commandType;
    }

    /**
     * Returns the parameters associated with command category from userInput.
     * @param userInput raw user input
     * @return the parameters associated with command category
     * @throws DukeException if parameters are not found
     */
    protected String getCommandParameters(String userInput) throws DukeException {
        int startIndexOfParameter = userInput.indexOf(" ");

        if (startIndexOfParameter == -1) {
            throw new DukeException("No parameters provided");
        }

        return userInput.substring(startIndexOfParameter + 1);
    }

    /**
     * Execute the command from userInput.
     *
     * @param userInput The userInput from the Ui.
     * @return The command object.
     * @throws DukeException If the command is undefined.
     */
    public Command executeCommand(String userInput) throws DukeException {
        String commandCategory = getCommandCategory(userInput);
        String commandDescription = getCommandParameters(userInput);

        switch (commandCategory) {
        case "event":
            EventCommandInterpreter eci = new EventCommandInterpreter();
            eci.executeCommand(commandDescription);
        case "attendance":
            break;
        case "performance":
            //TODO
            break;
        default:
            throw new DukeException("Unknown command");
        }
    }
}

