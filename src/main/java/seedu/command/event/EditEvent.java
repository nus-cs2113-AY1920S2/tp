package seedu.command.event;

import seedu.command.Command;
import seedu.event.Event;
import seedu.event.EventList;

public class EditEvent extends Command {
    private Integer index;
    private Event newEvent;
    private EventList eventList;

    public EditEvent(Integer index, Event newEvent, EventList eventList) {
        this.index = index;
        this.newEvent = newEvent;
        this.eventList = eventList;
    }

    @Override
    public void execute() {
        eventList.editEvent(index, newEvent);
        System.out.println(newEvent);
    }
}
