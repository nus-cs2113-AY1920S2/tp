package seedu.command.interpreter;

import seedu.command.Command;
import seedu.command.event.AddEvent;
import seedu.command.event.EditDateTime;
import seedu.command.event.EditName;
import seedu.command.event.EditVenue;
import seedu.command.event.EditEvent;
import seedu.command.event.DeleteEvent;
import seedu.command.event.ListEvent;
import seedu.exception.PacException;
import seedu.event.Event;
import seedu.event.EventList;
import seedu.parser.EventParser;

public class EventCommandInterpreter extends CommandInterpreter {
    protected EventParser eventParser;
    private static final String[] COMMANDS_THAT_NEED_ARGUMENT = {"add",
        "editname", "editdatetime", "editvenue", "editevent", "delete"};
    private static final String INDEX_FLAG_EDIT_ERROR_MESSAGE = "Please use i/ flag to indicate which event to edit.";
    private static final String INDEX_FLAG_DELETE_ERROR_MESSAGE = "Please use i/ flag to indicate which event to "
            + "delete.";
    private static final String INVALID_COMMAND_TYPE_MESSAGE = "Please provide a valid command type. "
            + "Type 'help' for more info.";

    public EventCommandInterpreter(EventList eventList) {
        super(eventList);
        this.eventParser = new EventParser();
    }

    /**
     * Check if the input is a command that requires any argument. It checks
     * from COMMANDS_THAT_NEED_ARGUMENT, so that array must be set up properly first.
     * @param commandType the command to be checked
     * @return (@code true} if command type requires an argument
     */
    @Override
    protected boolean needArgument(String commandType) {
        for (String command : COMMANDS_THAT_NEED_ARGUMENT) {
            if (commandType.equals(command)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Command decideCommand(String commandDescription) throws PacException {
        Command command;
        Event event;
        int index;
        String name;
        String datetime;
        String venue;

        String commandType = getFirstWord(commandDescription);
        String commandParameters = "";
        // only look for 2nd to last words if commandCategory requires.
        if (needArgument(commandType)) {
            commandParameters = getSubsequentWords(commandDescription);
            eventParser.parse(commandParameters);
        }

        switch (commandType) {
        case "add":
            event = eventParser.getEvent();
            command = new AddEvent(event, this.eventList);
            break;
        case "editname":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException(INDEX_FLAG_EDIT_ERROR_MESSAGE);
            }
            index = eventParser.getIndex();
            name = eventParser.getName();
            command = new EditName(index, name, this.eventList);
            break;
        case "editdatetime":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException(INDEX_FLAG_EDIT_ERROR_MESSAGE);
            }
            index = eventParser.getIndex();
            datetime = eventParser.getDateTime();
            command = new EditDateTime(index, datetime, this.eventList);
            break;
        case "editvenue":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException(INDEX_FLAG_EDIT_ERROR_MESSAGE);
            }
            index = eventParser.getIndex();
            venue = eventParser.getVenue();
            command = new EditVenue(index, venue, this.eventList);
            break;
        case "editevent":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException(INDEX_FLAG_EDIT_ERROR_MESSAGE);
            }
            event = eventParser.getEvent();
            index = eventParser.getIndex();
            command = new EditEvent(index, event, this.eventList);
            break;
        case "delete":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException(INDEX_FLAG_DELETE_ERROR_MESSAGE);
            }
            index = eventParser.getIndex();
            command = new DeleteEvent(index, this.eventList);
            break;
        case "list":
            command = new ListEvent(this.eventList);
            break;
        default:
            throw new PacException(INVALID_COMMAND_TYPE_MESSAGE);
        }

        return command;
    }
}
