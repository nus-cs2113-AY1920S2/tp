package seedu.command;

import seedu.exception.DukeException;
import seedu.event.Event;
import seedu.event.EventList;

public class EventCommandInterpreter extends CommandInterpreter {
    protected String getCommandType(String input) {
        return getCommandCategory(input);
    }

    public Command executeCommand(String commandDescription) throws DukeException {
        Command command;
        Event event;

        String commandType = getCommandType(commandDescription);
        String commandParameters = getCommandParameters(commandDescription);
        switch (commandType) {
        case "add":
            event = parser.parseEvent(commandParameters);
            
            break;
        case "edit":
            //TODO edit
            break;
        case "delete":
            //TODO delete
            break;
        case "list":
            //TODO list
            break;
        default:
            throw new DukeException("Unknown command");
        }
    }
}
