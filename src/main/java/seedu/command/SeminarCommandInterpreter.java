package seedu.command;


import seedu.command.event.EditDateTime;
import seedu.command.event.EditName;
import seedu.command.event.ListSeminar;
import seedu.command.event.AddEvent;
import seedu.command.event.EditVenue;
import seedu.command.event.EditEvent;
import seedu.command.event.DeleteEvent;
import seedu.event.Seminar;
import seedu.exception.DukeException;
import seedu.event.EventList;
import seedu.parser.EventParser;

public class SeminarCommandInterpreter extends CommandInterpreter {
    protected EventParser eventParser;

    public SeminarCommandInterpreter(EventList eventList) {
        super(eventList);
        this.eventParser = new EventParser();
    }

    @Override
    public Command decideCommand(String commandDescription) throws DukeException {
        Command command = null;
        Seminar seminar;
        int index;
        String name;
        String datetime;
        String venue;

        String commandType = getFirstWord(commandDescription);
        String commandParameters = getSubsequentWords(commandDescription);
        assert commandType.isBlank() : "Seminar: Unknown command";

        switch (commandType) {
        case "add":
            seminar = eventParser.parseSeminar(commandParameters);
            command = new AddEvent(seminar, this.eventList);
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
            seminar = eventParser.parseSeminar(commandParameters);
            command = new EditEvent(index, seminar, this.eventList);
            break;
        case "delete":
            index = eventParser.parseIndex(commandParameters);
            command = new DeleteEvent(index, this.eventList);
            break;
        case "list":
            command = new ListSeminar(this.eventList);
            break;
        default:
            throw new DukeException("Seminar: Unknown command");
        }

        if (command == null) {
            throw new DukeException("Seminar: Command is null");
        }
        return command;
    }
}
