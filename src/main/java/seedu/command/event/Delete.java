package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;

public class Delete extends Command {
    private Integer index;
    private EventList eventList;

    public Delete(Integer index, EventList eventList) {
        this.index = index;
        this.eventList = eventList;
    }

    @Override
    public void execute() {
        eventList.delete(index);
    }
}
