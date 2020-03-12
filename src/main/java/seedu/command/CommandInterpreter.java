package seedu.command;

import seedu.parser.Parser;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class CommandInterpreter {
    protected Parser parser;
    protected EventList eventList;

    public CommandInterpreter(EventList eventList) {
        this.parser = new Parser();
        this.eventList = eventList;
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
     * @throws DukeException If the command is undefined.
     */
    public Command decideCommand(String userInput) throws DukeException {
        Command command = null;

        String commandCategory = getCommandCategory(userInput);         // first word
        String commandDescription = getCommandParameters(userInput);    // subsequent
        switch (commandCategory) {
        case "event":
            EventCommandInterpreter eci = new EventCommandInterpreter(eventList);
            command = eci.decideCommand(commandDescription);
            break;
        case "attendance":
            //TODO AttendanceCommandInterpreter
            break;
        case "performance":
            //TODO PerformanceCommandInterpreter
            break;
        default:
            throw new DukeException("Unknown command");
        }

        if (command == null) {
            throw new DukeException("duke is null");
        }
        return command;
    }
}

