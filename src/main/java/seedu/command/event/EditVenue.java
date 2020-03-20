package seedu.command.event;

import seedu.command.Command;
import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class EditVenue extends Command {
    private Integer index;
    private EventList eventList;
    private String venue;

    public EditVenue(Integer index, String venue, EventList eventList) {
        this.index = index - 1;
        this.venue = venue;
        this.eventList = eventList;
    }

    @Override
    public void execute() throws DukeException {
        eventList.editVenue(index, venue);
        System.out.println(eventList.list.get(index));
    }
}
