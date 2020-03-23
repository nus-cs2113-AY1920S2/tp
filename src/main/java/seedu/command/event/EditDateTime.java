package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class EditDateTime extends Command {
    private Integer index;
    private EventList eventList;
    private String datetime;

    public EditDateTime(Integer index, String datetime, EventList eventList) {
        this.index = index - 1;
        this.datetime = datetime;
        this.eventList = eventList;
    }


    @Override
    public void execute() throws DukeException {
        eventList.editDatetime(index, datetime);
        System.out.println(eventList.list.get(index));
    }
}
