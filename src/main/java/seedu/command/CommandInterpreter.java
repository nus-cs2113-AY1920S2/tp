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
     * Returns the first word in lower cases.
     * @param userInput raw user input
     * @return the first word in lower cases
     */
    protected String getFirstWord(String userInput) {
        String commandType = userInput.split(" ")[0];
        commandType = commandType.trim();
        commandType = commandType.toLowerCase();
        return commandType;
    }

    /**
     * Returns the 2nd to last words.
     * @param userInput raw user input
     * @return the 2nd to last words
     * @throws DukeException if there is only 1 word from the input
     */
    protected String getSubsequentWords(String userInput) throws DukeException {
        int startIndexOfSpace = userInput.trim().indexOf(" ");

        if (startIndexOfSpace == -1) {
            throw new DukeException("No parameters provided");
        }

        int startIndexOfParameter = startIndexOfSpace + 1;
        return userInput.substring(startIndexOfParameter);
    }

    /**
     * Execute the command from userInput.
     *
     * @param userInput The userInput from the Ui.
     * @throws DukeException If the command is undefined.
     */
    public Command decideCommand(String userInput) throws DukeException {
        Command command = null;

        String commandCategory = getFirstWord(userInput);
        String commandDescription = getSubsequentWords(userInput);
        switch (commandCategory) {
        case "event":
            EventCommandInterpreter eci = new EventCommandInterpreter(eventList);
            command = eci.decideCommand(commandDescription);
            break;
        case "attendance":
            //TODO AttendanceCommandInterpreter
            break;
        case "performance":
            PerformanceCommandInterpreter pci = new PerformanceCommandInterpreter(eventList);
            command = pci.decideCommand(commandDescription);
            break;
        default:
            throw new DukeException("Unknown command type.");
        }

        if (command == null) {
            throw new DukeException("Duke is null.");
        }
        return command;
    }
}

