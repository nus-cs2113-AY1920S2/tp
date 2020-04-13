package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.PacException;

public class DeleteEvent extends Command {
    private Integer index;
    private EventList eventList;

    public DeleteEvent(Integer index, EventList eventList) {
        this.index = index - 1;
        this.eventList = eventList;
    }

    @Override
    public void execute() throws PacException {
        if (eventList.getSize() == 0) {
            throw new PacException("List is empty, unable to delete any items.");
        } else if (index >= eventList.getSize()) {
            throw new PacException("Index cannot be found. Check your index from the list.");
        }
        eventList.delete(index);
    }
}
