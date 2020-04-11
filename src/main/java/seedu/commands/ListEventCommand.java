package seedu.commands;

import seedu.events.Event;
import seedu.events.EventList;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

import java.util.ArrayList;

/**
 * Command Class for the ListEvent command.
 */
public class ListEventCommand extends ListCommand {

    public static final String COMMAND_WORD = "listevent";

    public static final String MESSAGE_USAGE = "To list cards, type command: listevents";

    /**
     * Lists all events currently stored in the application.
     */
    @Override
    public void execute(SubjectList subjectList) {
        EventList events = subjectList.getEventList();
        events.listUpcoming();
    }

}
