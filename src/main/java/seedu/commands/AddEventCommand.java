package seedu.commands;

import seedu.events.Event;
import seedu.events.EventList;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

import java.util.ArrayList;

/**
 * Command class for the AddEventCommand.
 */
public class AddEventCommand extends AddCommand {

    public static final String COMMAND_WORD = "addevent";

    public static final String MESSAGE_USAGE = "To add an upcoming event, "
            + "type command: addevent e/[DESCRIPTION] d/[DATE]";

    private Event event;

    /**
     * Initialises the parameters for the AddEvent command.
     */
    public AddEventCommand(Event event) {
        this.event = event;
    }

    /**
     * Adds an event to the events list.
     */
    @Override
    public void execute(SubjectList subjectList) throws EscException {
        EventList events = subjectList.getEventList();
        events.addEvent(this.event);
    }


}
