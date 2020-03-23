package seedu.command.interpreter;

import seedu.command.Command;
import seedu.command.event.AddEvent;
import seedu.command.event.EditDateTime;
import seedu.command.event.EditName;
import seedu.command.event.EditVenue;
import seedu.command.event.EditEvent;
import seedu.command.event.DeleteEvent;
import seedu.command.event.ListEvent;
import seedu.exception.DukeException;
import seedu.event.Event;
import seedu.event.EventList;
import seedu.parser.EventParser;

public class EventCommandInterpreter extends CommandInterpreter {
    protected EventParser eventParser;

    public EventCommandInterpreter(EventList eventList) {
        super(eventList);
        this.eventParser = new EventParser();
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
        assert commandType == " " : "Event: Unknown command";
        assert commandType == " " : "Event: Command is null";
        switch (commandType) {
        case "add":
            event = eventParser.parseEvent(commandParameters);
            command = new AddEvent(event, this.eventList);
            break;
        case "editname":
            index = eventParser.parseIndex(commandParameters);
            name = eventParser.parseEventName(commandParameters);
            command = new EditName(index, name, this.eventList);
            break;
        case "editdatetime":
            index = eventParser.parseIndex(commandParameters);
            datetime = eventParser.parseEventDateTime(commandParameters);
            command = new EditDateTime(index, datetime, this.eventList);
            break;
        case "editvenue":
            index = eventParser.parseIndex(commandParameters);
            venue = eventParser.parseVenue(commandParameters);
            command = new EditVenue(index, venue, this.eventList);
            break;
        case "editevent":
            index = eventParser.parseIndex(commandParameters);
            event = eventParser.parseEvent(commandParameters);
            command = new EditEvent(index, event, this.eventList);
            break;
        case "delete":
            index = eventParser.parseIndex(commandParameters);
            command = new DeleteEvent(index, this.eventList);
            break;
        case "list":
            command = new ListEvent(this.eventList);
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
