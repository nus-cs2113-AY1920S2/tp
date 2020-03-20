package seedu.command.event;

import seedu.command.Command;
import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class ListEvent extends Command {
    private EventList eventList;

    public ListEvent(EventList eventList) {
        this.eventList = eventList;
    }

    @Override
    public void execute() throws DukeException {
        eventList.listEvent();
    }
}
