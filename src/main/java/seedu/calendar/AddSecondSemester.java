package seedu.calendar;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class AddSecondSemester extends Command {
    private EventList eventList;
    private Integer year;

    public AddSecondSemester(EventList eventList, Integer year) {
        this.eventList = eventList;
        this.year = year;
    }

    @Override
    public void execute() throws DukeException {
        CalendarList.showSecondSemester(eventList, year);
    }
}
