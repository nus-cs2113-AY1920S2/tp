package seedu.command.interpreter;

import seedu.command.Command;
import seedu.command.event.EditDateTime;
import seedu.command.event.EditName;
import seedu.command.event.ListSeminar;
import seedu.command.event.AddEvent;
import seedu.command.event.EditVenue;
import seedu.command.event.EditEvent;
import seedu.command.event.DeleteEvent;
import seedu.event.Seminar;
import seedu.exception.PacException;
import seedu.event.EventList;
import seedu.parser.EventParser;

public class SeminarCommandInterpreter extends CommandInterpreter {
    protected EventParser eventParser;
    private static final String[] COMMANDS_THAT_NEED_ARGUMENT = {"add",
        "editname", "editdatetime", "editvenue", "editevent", "delete"};

    public SeminarCommandInterpreter(EventList eventList) {
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
        Command command = null;
        Seminar seminar;
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
            seminar = eventParser.getSeminar();
            command = new AddEvent(seminar, this.eventList);
            break;
        case "editname":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException("Please use i/ flag to indicate which event to edit.");
            }
            index = eventParser.getIndex();
            name = eventParser.getName();
            command = new EditName(index, name, this.eventList);
            break;
        case "editdatetime":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException("Please use i/ flag to indicate which event to edit.");
            }
            index = eventParser.getIndex();
            datetime = eventParser.getDateTime();
            command = new EditDateTime(index, datetime, this.eventList);
            break;
        case "editvenue":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException("Please use i/ flag to indicate which event to edit.");
            }
            index = eventParser.getIndex();
            venue = eventParser.getVenue();
            command = new EditVenue(index, venue, this.eventList);
            break;
        case "editevent":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException("Please use i/ flag to indicate which event to edit.");
            }
            seminar = eventParser.getSeminar();
            index = eventParser.getIndex();
            command = new EditEvent(index, seminar, this.eventList);
            break;
        case "delete":
            if (flagDoesNotExist(commandParameters, "i/")) {
                throw new PacException("Please use i/ flag to indicate which event to delete.");
            }
            index = eventParser.getIndex();
            command = new DeleteEvent(index, this.eventList);
            break;
        case "list":
            command = new ListSeminar(this.eventList);
            break;
        default:
            throw new PacException("Please provide a valid command type. Refer to 'help' for more info.");
        }
        return command;
    }
}
