package seedu.command.interpreter;

import seedu.calendar.AddFirstSemester;
import seedu.calendar.AddSecondSemester;
import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.parser.CalendarParser;

public class CalendarCommandInterpreter extends CommandInterpreter {
    public CalendarCommandInterpreter(EventList eventList) {
        super(eventList);
    }

    @Override
    public Command decideCommand(String commandDescription) throws DukeException {
        Command command = null;

        int semester = CalendarParser.getSemester(commandDescription);
        int year = CalendarParser.getYear(commandDescription, semester);

        switch (semester) {
        case 1:
            command = new AddFirstSemester(eventList, year);
            break;
        case 2:
            command = new AddSecondSemester(eventList, year);
            break;
        default:
            throw new DukeException("Please give a valid semester number: s/1, s/2");
        }
        return command;
    }

}
