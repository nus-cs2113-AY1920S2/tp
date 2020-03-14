package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;

public class ListEvent extends Command {
    private EventList eventList;

    public ListEvent(EventList eventList) {
        this.eventList = eventList;
    }

    @Override
    public void execute() {
        for (int i = 0; i < eventList.getSize(); i++) {
            eventList.find(i);
        }
    }
}
