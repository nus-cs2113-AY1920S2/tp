package seedu.command;

import seedu.command.event.AddEvent;
import seedu.command.event.DeleteEvent;
import seedu.exception.DukeException;
import seedu.event.Event;
import seedu.event.EventList;

public class EventCommandInterpreter extends CommandInterpreter {
    public EventCommandInterpreter(EventList eventList) {
        super(eventList);
    }

    @Override
    public Command decideCommand(String commandDescription) throws DukeException {
        Command command = null;
        Event event;
        int index;

        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        switch (commandType) {
        case "add":
            event = parser.parseEvent(commandParameters);
            command = new AddEvent(event, this.eventList);
            break;
        case "edit":
            //TODO edit
            break;
        case "delete":
            index = parser.parseIndex(commandParameters);
            command = new DeleteEvent(index, this.eventList);
            break;
        case "list":
            //TODO list
            break;
        default:
            throw new DukeException("Event: Unknown command");
        }

        if (command == null) {
            throw new DukeException("Event: Command is null");
        }

        return command;
    }
}
