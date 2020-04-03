package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.PacException;

public class ListEvent extends Command {
    private EventList eventList;

    public ListEvent(EventList eventList) {
        this.eventList = eventList;
    }

    @Override
    public void execute() throws PacException {
        eventList.listEvent();
    }
}
