package seedu.command.event;

import seedu.command.Command;
import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.PacException;

public class AddEvent extends Command {
    private Event newEvent;
    private EventList eventList;

    public AddEvent(Event newEvent, EventList eventList) {
        this.newEvent = newEvent;
        this.eventList = eventList;
    }

    @Override
    public void execute() throws PacException {
        eventList.add(newEvent);
    }


}
