package seedu.command.calendar;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.PacException;

public class EventsSeparator extends Command {
    private EventList eventList;
    private int year;
    private int semester;
    private Calendar calendar;

    public EventsSeparator(EventList eventList, Integer year, int semester) {
        this.eventList = eventList;
        this.year = year;
        this.semester = semester;
        this.calendar = new Calendar();
    }

    @Override
    public void execute() throws PacException {
        calendar.separateEvents(eventList, year, semester);
    }
}
