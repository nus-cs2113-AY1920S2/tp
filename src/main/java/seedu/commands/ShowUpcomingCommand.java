package seedu.commands;

import seedu.events.Event;
import seedu.events.EventList;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Command class for the ShowUpcomingCommand.
 */
public class ShowUpcomingCommand extends ListCommand {

    public static final String COMMAND_WORD = "showupcoming";

    public static final String MESSAGE_USAGE = "To show upcoming events, "
            + "type command: showupcoming d/[NUMBER OF DAYS UPCOMING]";

    private int dateRange;

    /**
     * Initialises the parameters for showupcoming command.
     */
    public ShowUpcomingCommand(int dateRange) {
        this.dateRange = dateRange;
    }

    /**
     * Show all upcoming events.
     */
    @Override
    public void execute(SubjectList subjectList) {
        EventList events = subjectList.getEventList();
        events.listUpcoming(dateRange);
    }

}
