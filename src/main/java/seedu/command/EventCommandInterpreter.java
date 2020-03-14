package seedu.command;

//import seedu.command.event.*;
import seedu.command.event.AddEvent;
import seedu.command.event.EditDateTime;
import seedu.command.event.EditName;
import seedu.command.event.EditVenue;
import seedu.command.event.EditEvent;
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
        String name;
        String datetime;
        String venue;

        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        switch (commandType) {
        case "add":
            event = parser.parseEvent(commandParameters);
            command = new AddEvent(event, this.eventList);
            break;
        case "editname":
            index = parser.parseIndex(commandParameters);
            name = parser.parseEventName(commandParameters);
            command = new EditName(index, name, this.eventList);
            break;
        case "editdatetime":
            index = parser.parseIndex(commandParameters);
            datetime = parser.parseEventDateTime(commandParameters);
            command = new EditDateTime(index, datetime, this.eventList);
            break;
        case "editvenue":
            index = parser.parseIndex(commandParameters);
            venue = parser.parseVenue(commandParameters);
            command = new EditVenue(index, venue, this.eventList);
            break;
        case "editevent":
            index = parser.parseIndex(commandParameters);
            event = parser.parseEvent(commandParameters);
            command = new EditEvent(index, event, this.eventList);
            break;
        case "delete":
            index = parser.parseIndex(commandParameters);
            command = new DeleteEvent(index, this.eventList);
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
