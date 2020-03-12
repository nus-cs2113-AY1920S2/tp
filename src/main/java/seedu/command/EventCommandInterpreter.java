package seedu.command;

import seedu.command.event.Add;
import seedu.command.event.Delete;
import seedu.exception.DukeException;
import seedu.event.Event;
import seedu.event.EventList;

public class EventCommandInterpreter extends CommandInterpreter {
    public EventCommandInterpreter(EventList eventList) {
        super(eventList);
    }

    protected String getCommandType(String input) {
        return getCommandCategory(input);
    }

    @Override
    public Command decideCommand(String commandDescription) throws DukeException {
        Command command = null;
        Event event;
        int index;

        String commandType = getCommandType(commandDescription);
        String commandParameters = getCommandParameters(commandDescription);
        switch (commandType) {
        case "add":
            event = parser.parseEvent(commandParameters);
            command = new Add(event, this.eventList);
            break;
        case "edit":
            //TODO edit
            break;
        case "delete":
            //TODO delete
            index = parser.parseIndex(commandParameters);
            command = new Delete(index, this.eventList);
            break;
        case "list":
            //TODO list
            break;
        default:
            throw new DukeException("Unknown command");
        }

        if (command == null) {
            throw new DukeException("command is null");
        }

        return command;
    }
}
