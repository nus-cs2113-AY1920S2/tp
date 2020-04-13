package seedu.command.event;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.PacException;

public class EditName extends Command {
    private Integer index;
    private EventList eventList;
    private String name;

    public EditName(Integer index, String name, EventList eventList) {
        this.index = index - 1;
        this.name = name;
        this.eventList = eventList;
    }


    @Override
    public void execute() throws PacException {
        eventList.editName(index, name);
    }
}
