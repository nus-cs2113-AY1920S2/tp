package seedu.command.interpreter;

import seedu.command.Command;
import seedu.command.calendar.EventsSeparator;
import seedu.event.EventList;
import seedu.exception.PacException;
import seedu.parser.CalendarParser;

public class CalendarCommandInterpreter extends CommandInterpreter {
    private static final String INVALID_SEMESTER_ERROR_MESSAGE = "Please give a valid semester number: s/1, s/2";
    protected CalendarParser calendarParser;

    public CalendarCommandInterpreter(EventList eventList) {
        super(eventList);
        this.calendarParser = new CalendarParser();
    }

    @Override
    public Command decideCommand(String commandDescription) throws PacException {
        Command command;
        String description = commandDescription.toLowerCase();
        int semester = calendarParser.getSemester(description);
        int year = calendarParser.getYear(description, semester);

        switch (semester) {
        case 1:
        case 2:
            command = new EventsSeparator(eventList, year, semester);
            break;
        default:
            throw new PacException(INVALID_SEMESTER_ERROR_MESSAGE);
        }
        return command;
    }

}
