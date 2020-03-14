package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;

public class DeleteEvent extends Command {
    private Integer index;
    private EventList eventList;

    public DeleteEvent(Integer index, EventList eventList) {
        this.index = index;
        this.eventList = eventList;
    }

    @Override
    public void execute() {
        eventList.delete(index);
    }
}
