package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.PacException;
import seedu.ui.UI;

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
    public void execute() throws PacException {
        eventList.editVenue(index, venue);
        UI.display(eventList.list.get(index).toString());
    }
}
