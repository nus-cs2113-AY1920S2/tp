package seedu.command.event;

import seedu.command.Command;
import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class EditEvent extends Command {
    private Integer index;
    private Event newEvent;
    private EventList eventList;

    public EditEvent(Integer index, Event newEvent, EventList eventList) {
        this.index = index - 1;
        this.newEvent = newEvent;
        this.eventList = eventList;
    }

    @Override
    public void execute() throws DukeException {
        eventList.editEvent(index, newEvent);
    }
}
