package seedu.command.event;

import seedu.command.Command;
import seedu.event.Event;
import seedu.event.EventList;

public class Add extends Command {
    private Event newEvent;
    private EventList eventList;

    public Add(Event newEvent, EventList eventList) {
        this.newEvent = newEvent;
        this.eventList = eventList;
    }

    @Override
    public void execute() {
        eventList.add(newEvent);
    }
}
