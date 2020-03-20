package seedu.command;

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
            System.out.println("jan - may");
            break;
        case 2:
            System.out.println("aug - dec");
            break;
        default:
            System.out.println("Wrong fk");
            break;
        }
        return command;
    }


}
