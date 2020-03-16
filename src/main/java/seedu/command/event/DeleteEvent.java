package seedu.command.event;

import seedu.command.Command;
import seedu.duke.Duke;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class DeleteEvent extends Command {
    private Integer index;
    private EventList eventList;

    public DeleteEvent(Integer index, EventList eventList) {
        this.index = index - 1;
        this.eventList = eventList;
    }

    @Override
    public void execute() throws DukeException {
        if (eventList.getSize() == 0) {
            throw new DukeException("List is empty, unable to delete any items.");
        } else if (index > eventList.getSize()) {
            throw new DukeException("Index not found");
        }
        eventList.delete(index);
    }
}
