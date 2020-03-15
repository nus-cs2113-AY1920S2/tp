package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class DeleteEvent extends Command {
    private Integer index;
    private EventList eventList;

    public DeleteEvent(Integer index, EventList eventList) {
        this.index = index;
        this.eventList = eventList;
    }

    @Override
    public void execute() throws DukeException {
        if (eventList.getSize() == 0) {
            throw new DukeException("List is empty, unable to delete any items.");
        }
        eventList.delete(index);
    }
}
