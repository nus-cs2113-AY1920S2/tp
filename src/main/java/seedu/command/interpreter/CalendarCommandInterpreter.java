package seedu.command.interpreter;

import seedu.command.Command;
import seedu.command.calendar.AddSemester;
import seedu.event.EventList;
import seedu.exception.PACException;
import seedu.parser.CalendarParser;

public class CalendarCommandInterpreter extends CommandInterpreter {
    public CalendarCommandInterpreter(EventList eventList) {
        super(eventList);
    }

    @Override
    public Command decideCommand(String commandDescription) throws PACException {
        Command command;

        int semester = CalendarParser.getSemester(commandDescription);
        int year = CalendarParser.getYear(commandDescription, semester);

        switch (semester) {
        case 1:
        case 2:
            command = new AddSemester(eventList, year, semester);
            break;
        default:
            throw new PACException("Please give a valid semester number: s/1, s/2");
        }
        return command;
    }

}
