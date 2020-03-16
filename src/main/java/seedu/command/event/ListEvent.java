package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class ListEvent extends Command {
    private EventList eventList;

    public ListEvent(EventList eventList) {
        this.eventList = eventList;
    }

    @Override
    public void execute() throws DukeException {
        if (eventList.getSize() == 0) {
            throw new DukeException("List is empty");
        }
        for (int i = 0; i < eventList.getSize(); i++) {
            eventList.find(i);
        }
    }
}
