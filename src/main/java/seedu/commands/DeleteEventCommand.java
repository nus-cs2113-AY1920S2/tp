package seedu.commands;

import seedu.events.EventList;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

/**
 * Command class for the DeleteEventCommand.
 */
public class DeleteEventCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "deleteevent";

    public static final String MESSAGE_USAGE = "To delete event, type command: deleteevent e/[EVENT INDEX]";

    private int eventIndex;

    public DeleteEventCommand(int eventIndex) {
        this.eventIndex = eventIndex - 1;
    }

    /**
     * Returns the index of the event to be deleted.
     */
    public int getEventIndex() {
        return eventIndex;
    }

    /** Removes an event from the application. */
    public void execute(SubjectList subjectList) throws EscException {
        EventList events = subjectList.getEventList();
        events.removeEvent(this.eventIndex);
    }
}
